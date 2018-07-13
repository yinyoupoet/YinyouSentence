package action;

import bean.CollectionSentence;
import bean.GiantInfo;
import bean.SentenceCollection;
import bean.UserInfo;
import com.opensymphony.xwork2.ActionSupport;
import dao.CollectionDao;
import dao.GiantDao;
import dao.PeopleDao;
import dao.SentenceDao;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import pageEntity.SentenceEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ToCollectiListAction
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-11 21:34
 * Version 1.0
 */
public class ToCollectiListAction extends ActionSupport implements SessionAware{

    @Autowired
    private SentenceDao sentenceDao;
    @Autowired
    private CollectionDao collectionDao;
    @Autowired
    private PeopleDao peopleDao;

    private String collectionId;

    private Map session;

    private SentenceCollection sentenceCollection;
    private UserInfo publisherInfo;
    private Boolean loveOrNot;
    private List<SentenceEntity> sentenceEntities;


    @Override
    public String execute() throws Exception {
        long cltId = 0;
        try {
            cltId = Long.valueOf(collectionId);
        }catch (Exception e){
            return ERROR;
        }
        sentenceCollection = collectionDao.getSentenceCollectionByCollectionId(cltId);
        if(sentenceCollection == null){
            return ERROR;
        }
        publisherInfo = peopleDao.getUserInfoById(sentenceCollection.getPublisherId());

        long myId = 0;
        UserInfo myInfo = (UserInfo) session.get("userInfo");
        if(myInfo != null){
            myId = myInfo.getId();
        }

        loveOrNot = collectionDao.checkUserLoveCollection(myId, cltId);

        // 初始化句子列表
        initSentenceEntities(myId, cltId);

        session.put("sentenceCollection",sentenceCollection);
        session.put("publisherInfo",publisherInfo);
        session.put("loveOrNot",loveOrNot);
        session.put("sentenceEntities",sentenceEntities);

        return SUCCESS;
    }


    // 初始化句子列表
    public void initSentenceEntities(long myId, long cltId){
        List<CollectionSentence> collectionSentences = collectionDao.getCollectionSentenceByCltId(cltId);
        if(sentenceEntities != null){
            sentenceEntities.clear();
        }else {
            sentenceEntities = new ArrayList<SentenceEntity>();
        }
        for(CollectionSentence collectionSentence : collectionSentences){
            SentenceEntity sentenceEntity = new SentenceEntity();
            sentenceEntity.setSentenceDao(sentenceDao);
            sentenceEntity.init(collectionSentence.getSentenceId(),myId);
            sentenceEntities.add(sentenceEntity);
        }
    }



    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public Map getSession() {
        return session;
    }

    public SentenceDao getSentenceDao() {
        return sentenceDao;
    }

    public void setSentenceDao(SentenceDao sentenceDao) {
        this.sentenceDao = sentenceDao;
    }

    public SentenceCollection getSentenceCollection() {
        return sentenceCollection;
    }

    public void setSentenceCollection(SentenceCollection sentenceCollection) {
        this.sentenceCollection = sentenceCollection;
    }


    public List<SentenceEntity> getSentenceEntities() {
        return sentenceEntities;
    }

    public void setSentenceEntities(List<SentenceEntity> sentenceEntities) {
        this.sentenceEntities = sentenceEntities;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public CollectionDao getCollectionDao() {
        return collectionDao;
    }

    public void setCollectionDao(CollectionDao collectionDao) {
        this.collectionDao = collectionDao;
    }


    public UserInfo getPublisherInfo() {
        return publisherInfo;
    }

    public void setPublisherInfo(UserInfo publisherInfo) {
        this.publisherInfo = publisherInfo;
    }

    public PeopleDao getPeopleDao() {
        return peopleDao;
    }

    public void setPeopleDao(PeopleDao peopleDao) {
        this.peopleDao = peopleDao;
    }
}
