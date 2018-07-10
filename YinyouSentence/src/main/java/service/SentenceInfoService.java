package service;

import bean.SentenceLove;
import bean.UserInfo;
import dao.SentenceDao;
import dwrPOJO.LoveSentencePOJO;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

/**
 * @ClassName SentenceInfoService
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-09 10:34
 * Version 1.0
 */
public class SentenceInfoService {
    @Autowired
    private SentenceDao sentenceDao;


    /**
    * @author hasee
    * @Description 点击喜欢一个句子
    * @Date 10:46 2018-07-09
    * @Param []
    * @return java.lang.Boolean
    **/
    public LoveSentencePOJO loveSentence(long sentenceId){
        LoveSentencePOJO loveSentencePOJO = new LoveSentencePOJO();
        HttpSession session = WebContextFactory.get().getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if(userInfo == null){
            loveSentencePOJO.setSuccess(false);
            loveSentencePOJO.setReason("请先登录");
            return loveSentencePOJO;
        }
        if(! sentenceDao.isSentenceExist(sentenceId)){
            // 如果句子不存在，那么返回false
            loveSentencePOJO.setSuccess(false);
            loveSentencePOJO.setReason("当前句子不存在，请刷新页面");
            return loveSentencePOJO;
        }

        // 如果已经登录
        long loveId = sentenceDao.checkUserLoveSentence(userInfo.getId(),sentenceId);
        if(loveId == 0){
            // 表示以前没有喜欢，那么这次就进行喜欢
            SentenceLove sentenceLove = new SentenceLove();
            sentenceLove.setUserId(userInfo.getId());
            sentenceLove.setSentenceId(sentenceId);
            // 进行喜欢
            sentenceDao.userLoveSentence(sentenceLove);
            loveSentencePOJO.setSuccess(true);
            loveSentencePOJO.setFollow(true);
        }else{
            // 如果之前已经喜欢过了，那么取消喜欢
            sentenceDao.userNotLoveSentence(loveId,sentenceId);
            loveSentencePOJO.setSuccess(true);
            loveSentencePOJO.setFollow(false);
        }
        loveSentencePOJO.setLoveNum(sentenceDao.getSentenceLoveNum(sentenceId));


        return loveSentencePOJO;
    }




    public SentenceDao getSentenceDao() {
        return sentenceDao;
    }

    public void setSentenceDao(SentenceDao sentenceDao) {
        this.sentenceDao = sentenceDao;
    }
}
