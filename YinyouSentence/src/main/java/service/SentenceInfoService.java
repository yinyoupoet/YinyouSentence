package service;

import bean.*;
import dao.CollectionDao;
import dao.PeopleDao;
import dao.SentenceDao;
import dwrPOJO.LoveSentencePOJO;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pageEntity.CollectionEntity;
import pageEntity.OutLookCollectionEntity;
import pageEntity.SentenceEntity;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SentenceInfoService
 * @Description DWR获取所有列表类数据的一个集合服务类
 * @Author hasee
 * @Date 2018-07-09 10:34
 * Version 1.0
 */
public class SentenceInfoService {
    @Autowired
    private SentenceDao sentenceDao;
    @Autowired
    private PeopleDao peopleDao;
    @Autowired
    private CollectionDao collectionDao;


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



    /**
    * @author hasee
    * @Description 获得当前用户所有喜欢的句子的列表
    * @Date 11:38 2018-07-12
    * @Param [s_userId]
    * @return java.util.List<pageEntity.SentenceEntity>
    **/
    public List<SentenceEntity> getLoveSentenceEntities(String s_userId){
        // System.out.println("s_userId: " + s_userId);
        // 用户ID校验
        long userId = checkUserId(s_userId);
        if(userId == 0){
            return null;
        }

        HttpSession session = WebContextFactory.get().getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        long myId = 0;
        if(userInfo != null){
            myId = userInfo.getId();
        }


        List<SentenceEntity> sentenceEntities = new ArrayList<SentenceEntity>();
        List<SentenceLove> sentenceLoves = sentenceDao.getLoveSentencesByUserId(userId);
        for (SentenceLove s : sentenceLoves){
            SentenceEntity sentenceEntity = new SentenceEntity();
            sentenceEntity.setSentenceDao(sentenceDao);
            sentenceEntity.init(s.getSentenceId(),userId, myId);
            sentenceEntities.add(sentenceEntity);
        }
        return sentenceEntities;
    }



    /**
    * @author hasee
    * @Description 获取用户发布的句子
    * @Date 16:10 2018-07-12
    * @Param [s_userId]
    * @return java.util.List<pageEntity.SentenceEntity>
    **/
    public List<SentenceEntity> getPubishSentenceEntities(String s_userId){
        // 用户ID校验
        long userId = checkUserId(s_userId);
        if(userId == 0){
            return null;
        }

        HttpSession session = WebContextFactory.get().getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        long myId = 0;
        if(userInfo != null){
            myId = userInfo.getId();
        }


        List<SentenceEntity> sentenceEntities = new ArrayList<SentenceEntity>();
        List<Sentence> sentencePublishs = sentenceDao.getPublishSentencesByUserId(userId);
        for (Sentence s : sentencePublishs){
            SentenceEntity sentenceEntity = new SentenceEntity();
            sentenceEntity.setSentenceDao(sentenceDao);
            sentenceEntity.init(s.getId(),userId, myId);
            sentenceEntities.add(sentenceEntity);
        }
        return sentenceEntities;
    }


    /**
    * @author hasee
    * @Description 获取用户原创的句子
    * @Date 16:38 2018-07-12
    * @Param [s_userId]
    * @return java.util.List<pageEntity.SentenceEntity>
    **/
    public List<SentenceEntity> getOriginalSentenceEntites(String s_userId){
        // 用户ID校验
        long userId = checkUserId(s_userId);
        if(userId == 0){
            return null;
        }

        HttpSession session = WebContextFactory.get().getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        long myId = 0;
        if(userInfo != null){
            myId = userInfo.getId();
        }


        List<SentenceEntity> sentenceEntities = new ArrayList<SentenceEntity>();
        List<Sentence> sentenceOriginals = sentenceDao.getOriginalSentencesByUserId(userId);
        for (Sentence s : sentenceOriginals){
            SentenceEntity sentenceEntity = new SentenceEntity();
            sentenceEntity.setSentenceDao(sentenceDao);
            sentenceEntity.init(s.getId(),userId, myId);
            sentenceEntities.add(sentenceEntity);
        }
        return sentenceEntities;
    }


    /**
    * @author hasee
    * @Description 获取用户发布的句子集
    * @Date 16:38 2018-07-12
    * @Param [s_userId]
    * @return java.util.List<pageEntity.CollectionEntity>
    **/
    public List<OutLookCollectionEntity> getPublishCollectionEntities(String s_userId){
        // 用户ID校验
        long userId = checkUserId(s_userId);
        if(userId == 0){
            return null;
        }

        HttpSession session = WebContextFactory.get().getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        long myId = 0;
        if(userInfo != null){
            myId = userInfo.getId();
        }

        List<OutLookCollectionEntity> outLookCollectionEntities = new ArrayList<OutLookCollectionEntity>();
        UserInfo publisherInfo = peopleDao.getUserInfoById(userId);
        List<SentenceCollection> sentenceCollections = collectionDao.getSentenceCollectionsByUserId(userId);
        for(SentenceCollection sc : sentenceCollections){
            OutLookCollectionEntity outLookCollectionEntity = new OutLookCollectionEntity();
            outLookCollectionEntity.setPublisherInfo(publisherInfo);
            outLookCollectionEntity.setSentenceCollection(sc);
            if(collectionDao.checkUserLoveCollection(myId,sc.getId())){
                outLookCollectionEntity.setLoveOrNot(true);
            }else{
                outLookCollectionEntity.setLoveOrNot(false);
            }
            outLookCollectionEntities.add(outLookCollectionEntity);
        }
        return outLookCollectionEntities;
    }


    /**
    * @author hasee
    * @Description
    * @Date 19:44 2018-07-12
    * @Param [s_userId]
    * @return java.util.List<pageEntity.OutLookCollectionEntity>
    **/
    public List<OutLookCollectionEntity> getLoveCollectionEntities(String s_userId){
        // 用户ID校验
        long userId = checkUserId(s_userId);
        if(userId == 0){
            return null;
        }

        HttpSession session = WebContextFactory.get().getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        long myId = 0;
        if(userInfo != null){
            myId = userInfo.getId();
        }

        List<OutLookCollectionEntity> outLookCollectionEntities = new ArrayList<OutLookCollectionEntity>();
        UserInfo publisherInfo = peopleDao.getUserInfoById(userId);
        List<CollectionLove> collectionLoves = collectionDao.getLoveSentenceCollectionsByUserId(userId);
        for(CollectionLove cl : collectionLoves){
            OutLookCollectionEntity outLookCollectionEntity = new OutLookCollectionEntity();
            outLookCollectionEntity.setPublisherInfo(publisherInfo);
            outLookCollectionEntity.setSentenceCollection(collectionDao.getSentenceCollectionByCollectionId(cl.getCollectionId()));
            if(collectionDao.checkUserLoveCollection(myId,cl.getCollectionId())){
                outLookCollectionEntity.setLoveOrNot(true);
            }else{
                outLookCollectionEntity.setLoveOrNot(false);
            }
            outLookCollectionEntities.add(outLookCollectionEntity);
        }
        return outLookCollectionEntities;
    }



    /**
    * @author hasee
    * @Description 喜欢句子集事件，因返回参数和LoveSentencePOJO相同，故直接用
    * @Date 20:22 2018-07-12
    * @Param [s_collectionId]
    * @return dwrPOJO.LoveSentencePOJO
    **/
    public LoveSentencePOJO loveCollection(String s_collectionId){
        LoveSentencePOJO loveSentencePOJO = new LoveSentencePOJO();
        long collectionId = checkUserId(s_collectionId);
        if(collectionId == 0){
            loveSentencePOJO.setSuccess(false);
            loveSentencePOJO.setReason("参数错误，请刷新页面重试");
            return loveSentencePOJO;
        }
        HttpSession session = WebContextFactory.get().getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if(userInfo == null){
            loveSentencePOJO.setSuccess(false);
            loveSentencePOJO.setReason("请先登录");
            return loveSentencePOJO;
        }
        if(! collectionDao.isCollectionExist(collectionId)){
            loveSentencePOJO.setSuccess(false);
            loveSentencePOJO.setReason("参数错误，请刷新页面后重试");
            return loveSentencePOJO;
        }
        // 判断用户本来是否喜欢该句子集
        if(collectionDao.checkUserLoveCollection(userInfo.getId(),collectionId)){
            // 用户本来就喜欢，那么就删除
            collectionDao.cancelUserLoveCollection(userInfo.getId(),collectionId);
            loveSentencePOJO.setFollow(false);
        }else{
            // 本来是不喜欢，现在变成喜欢
            CollectionLove collectionLove = new CollectionLove();
            collectionLove.setUserId(userInfo.getId());
            collectionLove.setCollectionId(collectionId);
            collectionDao.addUserLoveCollection(collectionLove);
            loveSentencePOJO.setFollow(true);
        }
        // 获得句子集喜欢人数
        loveSentencePOJO.setLoveNum(collectionDao.getSentenceCollectionByCollectionId(collectionId).getLoveNum());
        loveSentencePOJO.setSuccess(true);
        return loveSentencePOJO;
    }





    // 用户ID校验，如果正确，则返回Long型id，否则返回0
    public long checkUserId(String sid){
        long userId = 0;
        try{
            userId = Long.valueOf(sid);
        }catch (Exception e){
            return 0;
        }
        return userId;
    }

    public SentenceDao getSentenceDao() {
        return sentenceDao;
    }

    public void setSentenceDao(SentenceDao sentenceDao) {
        this.sentenceDao = sentenceDao;
    }

    public PeopleDao getPeopleDao() {
        return peopleDao;
    }

    public void setPeopleDao(PeopleDao peopleDao) {
        this.peopleDao = peopleDao;
    }

    public CollectionDao getCollectionDao() {
        return collectionDao;
    }

    public void setCollectionDao(CollectionDao collectionDao) {
        this.collectionDao = collectionDao;
    }
}
