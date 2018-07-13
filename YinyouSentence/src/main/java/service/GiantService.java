package service;

import bean.GiantLove;
import bean.UserInfo;
import dao.GiantDao;
import dao.PublishSentenceDao;
import dwrPOJO.LoveSentencePOJO;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

/**
 * @ClassName GiantService
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-13 9:30
 * Version 1.0
 */
public class GiantService {
    @Autowired
    private GiantDao giantDao;

    @Autowired
    private PublishSentenceDao publishSentenceDao;

    /**
    * @author Hasse
    * @Description 喜欢名家
    * @Date 9:37 2018-07-13
    * @Param [gId]
    * @return dwrPOJO.LoveSentencePOJO
    **/
    public LoveSentencePOJO loveGiant(String gId){
        LoveSentencePOJO loveSentencePOJO = new LoveSentencePOJO();
        HttpSession session = WebContextFactory.get().getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if(userInfo == null){
            loveSentencePOJO.setSuccess(false);
            loveSentencePOJO.setReason("请先登录");
            return loveSentencePOJO;
        }
        long giantId = 0;
        try{
            giantId = Long.valueOf(gId);
        }catch (Exception e){
            loveSentencePOJO.setSuccess(false);
            loveSentencePOJO.setReason("参数错误，请刷新页面后重试");
            return loveSentencePOJO;
        }
        if(! giantDao.checkIfGiantExist(giantId)){
            loveSentencePOJO.setSuccess(false);
            loveSentencePOJO.setReason("参数错误，请刷新页面后重试");
            return loveSentencePOJO;
        }
        // 下面开始喜欢了
        if(giantDao.checkIfUserLoveGiant(userInfo.getId(),giantId)){
            // 如果已喜欢，则取消喜欢
            giantDao.cancelUserLoveGiant(userInfo.getId(),giantId);
            loveSentencePOJO.setFollow(false);
        }else{
            GiantLove giantLove = new GiantLove();
            giantLove.setGiantId(giantId);
            giantLove.setUserId(userInfo.getId());
            giantDao.addUserLoveGiant(giantLove);
            loveSentencePOJO.setFollow(true);
        }
        // 设置喜欢数量
        loveSentencePOJO.setLoveNum(giantDao.getGiantInfoById(giantId).getLoveNum());
        loveSentencePOJO.setSuccess(true);
        return loveSentencePOJO;
    }


    public GiantDao getGiantDao() {
        return giantDao;
    }

    public void setGiantDao(GiantDao giantDao) {
        this.giantDao = giantDao;
    }

    public PublishSentenceDao getPublishSentenceDao() {
        return publishSentenceDao;
    }

    public void setPublishSentenceDao(PublishSentenceDao publishSentenceDao) {
        this.publishSentenceDao = publishSentenceDao;
    }
}
