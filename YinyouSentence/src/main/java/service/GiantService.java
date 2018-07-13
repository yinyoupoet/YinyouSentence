package service;

import bean.CollectionLove;
import bean.GiantLove;
import bean.OriginLove;
import bean.UserInfo;
import dao.CollectionDao;
import dao.GiantDao;
import dao.OriginDao;
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
    private OriginDao originDao;

    @Autowired
    private PublishSentenceDao publishSentenceDao;

    @Autowired
    private CollectionDao collectionDao;

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


    /**
    * @author hasee
    * @Description 喜欢出处
    * @Date 14:28 2018-07-13
    * @Param [oId]
    * @return dwrPOJO.LoveSentencePOJO
    **/
    public LoveSentencePOJO loveOrigin(String oId){
        LoveSentencePOJO loveSentencePOJO = new LoveSentencePOJO();
        HttpSession session = WebContextFactory.get().getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if(userInfo == null){
            loveSentencePOJO.setSuccess(false);
            loveSentencePOJO.setReason("请先登录");
            return loveSentencePOJO;
        }
        long originId = 0;
        try{
            originId = Long.valueOf(oId);
        }catch (Exception e){
            loveSentencePOJO.setSuccess(false);
            loveSentencePOJO.setReason("参数错误，请刷新页面后重试");
            return loveSentencePOJO;
        }
        if(! originDao.checkIfOriginExist(originId)){
            loveSentencePOJO.setSuccess(false);
            loveSentencePOJO.setReason("参数错误，请刷新页面后重试");
            return loveSentencePOJO;
        }

        // 下面开始喜欢了
        if(originDao.checkIfUserLoveOrigin(userInfo.getId(),originId)){
            // 如果已喜欢，则取消喜欢
            originDao.cancelUserLoveOrigin(userInfo.getId(),originId);
            loveSentencePOJO.setFollow(false);
        }else{
            OriginLove originLove = new OriginLove();
            originLove.setOriginId(originId);
            originLove.setUserId(userInfo.getId());
            originDao.addUserLoveOrigin(originLove);
            loveSentencePOJO.setFollow(true);
        }
        // 设置喜欢数量
        loveSentencePOJO.setLoveNum(originDao.getOriginInfoById(originId).getLoveNum());
        loveSentencePOJO.setSuccess(true);
        return loveSentencePOJO;

    }


    // 喜欢句子集事件
    public LoveSentencePOJO loveCollection(String cId){
        LoveSentencePOJO loveSentencePOJO = new LoveSentencePOJO();
        HttpSession session = WebContextFactory.get().getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if(userInfo == null){
            loveSentencePOJO.setSuccess(false);
            loveSentencePOJO.setReason("请先登录");
            return loveSentencePOJO;
        }
        long collectionId = 0;
        try{
            collectionId = Long.valueOf(cId);
        }catch (Exception e){
            loveSentencePOJO.setSuccess(false);
            loveSentencePOJO.setReason("参数错误，请刷新页面后重试");
            return loveSentencePOJO;
        }
        if(! collectionDao.isCollectionExist(collectionId)){
            loveSentencePOJO.setSuccess(false);
            loveSentencePOJO.setReason("参数错误，请刷新页面后重试");
            return loveSentencePOJO;
        }

        // 下面开始正式喜欢句子集
        if(collectionDao.checkUserLoveCollection(userInfo.getId(),collectionId)){
            // 如果已经喜欢，那么就不喜欢
            collectionDao.cancelUserLoveCollection(userInfo.getId(),collectionId);
            loveSentencePOJO.setFollow(false);
        }else{
            CollectionLove collectionLove = new CollectionLove();
            collectionLove.setCollectionId(collectionId);
            collectionLove.setUserId(userInfo.getId());
            collectionDao.addUserLoveCollection(collectionLove);
            loveSentencePOJO.setFollow(true);
        }

        // 获取喜欢人数
        long loveNum = collectionDao.getSentenceCollectionByCollectionId(collectionId).getLoveNum();
        loveSentencePOJO.setLoveNum(loveNum);
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

    public OriginDao getOriginDao() {
        return originDao;
    }

    public void setOriginDao(OriginDao originDao) {
        this.originDao = originDao;
    }

    public CollectionDao getCollectionDao() {
        return collectionDao;
    }

    public void setCollectionDao(CollectionDao collectionDao) {
        this.collectionDao = collectionDao;
    }
}
