package service;

import auxiliary.CollectionAuxiliary;
import bean.CollectionSentence;
import bean.SentenceCollection;
import bean.UserInfo;
import dao.CollectionDao;
import dwrPOJO.CollectionCollectPOJO;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CollectService
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-10 18:45
 * Version 1.0
 */
public class CollectService {

    @Autowired
    private CollectionDao collectionDao;
    /**
    * @author hasee
    * @Description 收藏或取消收藏
    * @Date 23:43 2018-07-10
    * @Param [sentenceId,collectionId]
    * @return auxiliary.CollectionAuxiliary
    **/
    public CollectionCollectPOJO doCollect(String sentenceId, String collectionId){
        CollectionCollectPOJO collectionCollectPOJO = new CollectionCollectPOJO();
        long stId = 0;
        long ctId = 0;
        try {
            stId = Long.valueOf(sentenceId);
            ctId = Long.valueOf(collectionId);
        }catch (Exception e){
            collectionCollectPOJO.setSuccess(false);
            collectionCollectPOJO.setReason("参数错误，请刷新页面");
            return collectionCollectPOJO;
        }
        // 判断该句子集是否为当前用户发布的，不是就不能添加
        HttpSession session = WebContextFactory.get().getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if(userInfo == null){
            collectionCollectPOJO.setSuccess(false);
            collectionCollectPOJO.setReason("请先登录");
            return collectionCollectPOJO;
        }
        if(! collectionDao.checkCollectionBelongToUser(userInfo.getId(),ctId)){
            collectionCollectPOJO.setSuccess(false);
            collectionCollectPOJO.setReason("参数错误，请刷新页面");
            return collectionCollectPOJO;
        }

        long csId = collectionDao.getCollectionSentenceId(stId,ctId);
        if(csId == 0){
            // 表示还没有收藏，那么就给他收藏
            CollectionSentence collectionSentence = new CollectionSentence();
            collectionSentence.setSentenceId(stId);
            collectionSentence.setCollectionId(ctId);
            collectionDao.addSentenceIntoCollection(collectionSentence);
            collectionCollectPOJO.setCollect(true);
        }else{
            // 已收藏
            collectionDao.removeSentenceFromCollection(csId,ctId);
            collectionCollectPOJO.setCollect(false);
        }

        collectionCollectPOJO.setSentenceNum(collectionDao.getSentenceNumInCollection(ctId));
        System.out.println(collectionCollectPOJO.getSentenceNum());
        collectionCollectPOJO.setSuccess(true);
        return collectionCollectPOJO;
    }

    public CollectionCollectPOJO addNewCollection(String collectionName){
        CollectionCollectPOJO collectionCollectPOJO = new CollectionCollectPOJO();

        HttpSession session = WebContextFactory.get().getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if(userInfo == null){
            collectionCollectPOJO.setSuccess(false);
            collectionCollectPOJO.setReason("请先登录");
            return collectionCollectPOJO;
        }

        // 判断当前用户是否已经有一个这个名字的句子集了
        if(collectionDao.isColletionNameExistForTheUser(userInfo.getId(),collectionName)){
            // 如果已经存在
            collectionCollectPOJO.setSuccess(false);
            collectionCollectPOJO.setReason("您已经有一个该名字的句子集啦，请更换名称后重试哦");
            return collectionCollectPOJO;
        }

        // 新增一个句子集
        SentenceCollection sentenceCollection = new SentenceCollection();
        sentenceCollection.setImgPath(userInfo.getHeadPath());
        sentenceCollection.setIntroduction("");
        sentenceCollection.setLoveNum(0);
        sentenceCollection.setName(collectionName);
        sentenceCollection.setPublishDate(new Timestamp(System.currentTimeMillis()));
        sentenceCollection.setPublisherId(userInfo.getId());
        sentenceCollection.setSentenceNum(0);
        collectionDao.addNewCollection(sentenceCollection);
        collectionCollectPOJO.setSuccess(true);
        return collectionCollectPOJO;
    }
    


    public List<CollectionAuxiliary> initCollectionList(long sentenceId){
        HttpSession session = WebContextFactory.get().getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        long userId = userInfo.getId();
        List<CollectionAuxiliary> collectionAuxiliaries = new ArrayList<CollectionAuxiliary>();
        List<SentenceCollection> sentenceCollections = collectionDao.getSentenceCollectionsByUserId(userId);
        for(SentenceCollection sc : sentenceCollections){
            CollectionAuxiliary collectionAuxiliary = new CollectionAuxiliary();
            collectionAuxiliary.setSentenceCollection(sc);
            collectionAuxiliary.setCollect(collectionDao.isSentenceCollectedInCollection(sentenceId,sc.getId()));
            collectionAuxiliaries.add(collectionAuxiliary);
        }
        return collectionAuxiliaries;
    }

    public CollectionDao getCollectionDao() {
        return collectionDao;
    }

    public void setCollectionDao(CollectionDao collectionDao) {
        this.collectionDao = collectionDao;
    }
}
