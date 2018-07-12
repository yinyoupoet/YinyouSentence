package pageEntity;

import bean.*;
import dao.CollectionDao;
import dao.GiantDao;
import dao.InformationDao;
import dao.SentenceDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName IndexEntity
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-11 15:16
 * Version 1.0
 */
public class IndexEntity {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private CollectionDao collectionDao;

    @Autowired
    private SentenceDao sentenceDao;

    @Autowired
    private GiantDao giantDao;

    @Autowired
    private InformationDao informationDao;


    private SentenceEntity randomSentenceEntity;
    private List<SentenceEntity> recommendSentenceEntities = new ArrayList<SentenceEntity>();
    private List<SentenceEntity> hotSentenceEntities = new ArrayList<SentenceEntity>();
    private List<SentenceEntity> hotOriginSentenceEntities = new ArrayList<SentenceEntity>();
    private List<SentenceEntity> newPublishSentenceEntities = new ArrayList<SentenceEntity>();
    private List<GiantInfo> hotGiants = new ArrayList<GiantInfo>();
    private List<Category> categories = new ArrayList<Category>();
    private List<Tag> hotTags = new ArrayList<Tag>();
    private List<SentenceCollection> hotCollections = new ArrayList<SentenceCollection>();

    /**
     * @author hasee
     * @Description 数据的初始化
     * @Date 15:28 2018-07-11
     * @Param [userId] 如果还没登陆，那么就是0
     * @return void
     **/
    public void init(long userId){

        // 需要特别注意！这里的SentenceEntity属于自己创建的，里面需要注入的数据并未注入，这就需要手动创建

        initRandomSentenceEntity(userId);
        initRecommendSentenceEntities(userId);
        initHotSentenceEntities(userId);
        initHotOriginSentenceEntities(userId);
        initNewPublishSentenceEntities(userId);
        hotGiants = giantDao.getHotGiants(4);
        categories = informationDao.getAllCategories();
        hotTags = informationDao.getHotTags(30);
        hotCollections = collectionDao.getHotCollections(5);
    }

    /**
    * @author hasee
    * @Description 初始化随机句子
    * @Date 15:38 2018-07-11
    * @Param [userId]
    * @return void
    **/
    public void initRandomSentenceEntity(long userId){
        randomSentenceEntity = new SentenceEntity();
        randomSentenceEntity.setSentenceDao(sentenceDao);
        Sentence randomSentence = sentenceDao.getRandomSentence(0);
        randomSentenceEntity.init(randomSentence.getId(),userId);
        System.out.println(randomSentenceEntity.getSentence().getContent());
    }

    /**
    * @author hasee
    * @Description 初始化推荐句子
    * @Date 15:39 2018-07-11
    * @Param [userId]
    * @return void
    **/
    public void initRecommendSentenceEntities(long userId){
        if(recommendSentenceEntities != null){
            recommendSentenceEntities.clear();
        }else {
            recommendSentenceEntities = new ArrayList<SentenceEntity>();
        }
        List<Sentence> recommendSentences = sentenceDao.getRecommendSentences(5);
        for(Sentence sentence : recommendSentences){
            SentenceEntity sentenceEntity = new SentenceEntity();
            sentenceEntity.setSentenceDao(sentenceDao);
            sentenceEntity.init(sentence.getId(),userId);
            recommendSentenceEntities.add(sentenceEntity);
        }
    }

    /**
    * @author hasee
    * @Description 初始化热门句子
    * @Date 16:28 2018-07-11
    * @Param [userId]
    * @return void
    **/
    public void initHotSentenceEntities(long userId){
        if(hotSentenceEntities != null){
            hotSentenceEntities.clear();
        }else {
            hotSentenceEntities = new ArrayList<SentenceEntity>();
        }
        List<Sentence> hotSentences = sentenceDao.getHotSentences(5);
        for(Sentence sentence : hotSentences){
            SentenceEntity sentenceEntity = new SentenceEntity();
            sentenceEntity.setSentenceDao(sentenceDao);
            sentenceEntity.init(sentence.getId(),userId);
            hotSentenceEntities.add(sentenceEntity);
        }
    }

    /**
    * @author hasee
    * @Description 初始化热门原创句子
    * @Date 16:27 2018-07-11
    * @Param [userId]
    * @return void
    **/
    public void initHotOriginSentenceEntities(long userId){
        if(hotOriginSentenceEntities != null){
            hotOriginSentenceEntities.clear();
        }else {
            hotOriginSentenceEntities = new ArrayList<SentenceEntity>();
        }
        List<Sentence> hotOriginSentences = sentenceDao.getHotOriginSentences(5);
        for(Sentence sentence : hotOriginSentences){
            SentenceEntity sentenceEntity = new SentenceEntity();
            sentenceEntity.setSentenceDao(sentenceDao);
            sentenceEntity.init(sentence.getId(),userId);
            hotOriginSentenceEntities.add(sentenceEntity);
        }
    }

    /**
    * @author hasee
    * @Description 初始化新发布句子
    * @Date 16:27 2018-07-11
    * @Param [userId]
    * @return void
    **/
    public void initNewPublishSentenceEntities(long userId){
        if(newPublishSentenceEntities != null){
            newPublishSentenceEntities.clear();
        }else {
            newPublishSentenceEntities = new ArrayList<SentenceEntity>();
        }
        List<Sentence> newPublishSentence = sentenceDao.getNewPublishSentence(5);
        for(Sentence sentence : newPublishSentence){
            SentenceEntity sentenceEntity = new SentenceEntity();
            sentenceEntity.setSentenceDao(sentenceDao);
            sentenceEntity.init(sentence.getId(),userId);
            newPublishSentenceEntities.add(sentenceEntity);
        }
    }

    public SentenceEntity getRandomSentenceEntity() {
        return randomSentenceEntity;
    }

    public void setRandomSentenceEntity(SentenceEntity randomSentenceEntity) {
        this.randomSentenceEntity = randomSentenceEntity;
    }

    public List<SentenceEntity> getRecommendSentenceEntities() {
        return recommendSentenceEntities;
    }

    public void setRecommendSentenceEntities(List<SentenceEntity> recommendSentenceEntities) {
        this.recommendSentenceEntities = recommendSentenceEntities;
    }

    public List<SentenceEntity> getHotSentenceEntities() {
        return hotSentenceEntities;
    }

    public void setHotSentenceEntities(List<SentenceEntity> hotSentenceEntities) {
        this.hotSentenceEntities = hotSentenceEntities;
    }

    public List<SentenceEntity> getHotOriginSentenceEntities() {
        return hotOriginSentenceEntities;
    }

    public void setHotOriginSentenceEntities(List<SentenceEntity> hotOriginSentenceEntities) {
        this.hotOriginSentenceEntities = hotOriginSentenceEntities;
    }

    public List<SentenceEntity> getNewPublishSentenceEntities() {
        return newPublishSentenceEntities;
    }

    public void setNewPublishSentenceEntities(List<SentenceEntity> newPublishSentenceEntities) {
        this.newPublishSentenceEntities = newPublishSentenceEntities;
    }

    public List<GiantInfo> getHotGiants() {
        return hotGiants;
    }

    public void setHotGiants(List<GiantInfo> hotGiants) {
        this.hotGiants = hotGiants;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Tag> getHotTags() {
        return hotTags;
    }

    public void setHotTags(List<Tag> hotTags) {
        this.hotTags = hotTags;
    }

    public List<SentenceCollection> getHotCollections() {
        return hotCollections;
    }

    public void setHotCollections(List<SentenceCollection> hotCollections) {
        this.hotCollections = hotCollections;
    }


    public GiantDao getGiantDao() {
        return giantDao;
    }

    public void setGiantDao(GiantDao giantDao) {
        this.giantDao = giantDao;
    }

    public SentenceDao getSentenceDao() {
        return sentenceDao;
    }

    public void setSentenceDao(SentenceDao sentenceDao) {
        this.sentenceDao = sentenceDao;
    }

    public InformationDao getInformationDao() {
        return informationDao;
    }

    public void setInformationDao(InformationDao informationDao) {
        this.informationDao = informationDao;
    }

    public CollectionDao getCollectionDao() {
        return collectionDao;
    }

    public void setCollectionDao(CollectionDao collectionDao) {
        this.collectionDao = collectionDao;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
