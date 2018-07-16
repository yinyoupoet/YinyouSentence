package action;

import bean.*;
import com.opensymphony.xwork2.ActionSupport;
import dao.CollectionDao;
import dao.PeopleDao;
import dao.SentenceDao;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import pageEntity.OutLookCollectionEntity;
import pageEntity.PeopleEntity;
import pageEntity.SentenceEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ToSearchAction
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-14 10:26
 * Version 1.0
 */
public class ToSearchAction extends ActionSupport  implements SessionAware {

    @Autowired
    private SentenceDao sentenceDao;
    @Autowired
    private CollectionDao collectionDao;
    @Autowired
    private PeopleDao peopleDao;

    private Map session;


    private String searchContent;
    private String searchCategory;
    private String searchOption;

    // 搜索类别：0：为搜索句子; 1：为搜索句子集; 2：为搜索用户
    private long category = 0;
    // 搜索分类，目前只有搜索分类为句子时需要。0：搜索全部; 1：仅句子部分; 2：仅作者部分; 3：仅出处部分; 4：仅标签部分
    private long option = 0;

    private List<SentenceEntity> sentenceEntities = new ArrayList<SentenceEntity>();
    private List<OutLookCollectionEntity> collectionEntities = new ArrayList<OutLookCollectionEntity>();
    private List<PeopleEntity> peopleEntities = new ArrayList<PeopleEntity>();

    @Override
    public String execute() throws Exception {

        System.out.println(searchContent + "  " + searchCategory + "  " + searchOption);


        // 初始化分类和选项
        initCategoryAndOption();

        if(category == 0){
            // 是句子
            initSentnceEntities();
            session.put("sentenceEntities",sentenceEntities);
        }else if(category == 1){
            // 是句子集
            initSentenceCollection();
            session.put("collectionEntities",collectionEntities);
        } else if (category == 2) {
            // 是用户
            initUser();
            session.put("peopleEntities",peopleEntities);
        }
        session.put("category",category);
        session.put("option",option);
        session.put("searchContent",searchContent);
        return SUCCESS;
    }

    // 初始化句子
    // option  0：搜索全部; 1：仅句子部分; 2：仅作者部分; 3：仅出处部分; 4：仅标签部分
    public void initSentnceEntities(){
        List<Sentence> list = new ArrayList<Sentence>();

        // 全部
        if(option == 0){
            // 句子部分
            List<Sentence> sentencesParts = sentenceDao.getSentencesBySentenceContent(searchContent);

            // 用于判断是否会搜到重复句子
            HashMap<Long, Sentence> sentenceHashMap = new HashMap<Long, Sentence>();
            // 作者部分
            List<GiantInfo> giantInfos = sentenceDao.getGiantInfosByGiantName(searchContent);
            List<Sentence> giantParts = new ArrayList<Sentence>();
            for(GiantInfo giantInfo : giantInfos){
                List<Sentence> sentences = sentenceDao.getSentenceByAuthorId(giantInfo.getId());
                for(Sentence sentence : sentences){
                    if(sentenceHashMap.containsKey(sentence.getId())){
                        continue;
                    }else{
                        sentenceHashMap.put(sentence.getId(),sentence);
                        giantParts.add(sentence);
                    }
                }
            }

            // 出处部分
            List<OriginInfo> originInfos = sentenceDao.getOriginInfosByOriginName(searchContent);
            List<Sentence> originParts = new ArrayList<Sentence>();
            for(OriginInfo originInfo : originInfos){
                List<Sentence> sentences = sentenceDao.getSentencesByOriginId(originInfo.getId());
                for(Sentence sentence : sentences){
                    if(sentenceHashMap.containsKey(sentence.getId())){
                        continue;
                    }else{
                        sentenceHashMap.put(sentence.getId(),sentence);
                        originParts.add(sentence);
                    }
                }
            }

            // 标签部分
            List<Tag> tags = sentenceDao.getTagsByTagName(searchContent);
            List<Sentence> tagParts = new ArrayList<Sentence>();
            for(Tag tag : tags){
                List<TagQuote> tagQuotes = sentenceDao.getTagQuotesByTagId(tag.getId());
                for(TagQuote tagQuote : tagQuotes){
                    Sentence sentence = sentenceDao.getSentenceById(tagQuote.getSentenceId());
                    if(sentenceHashMap.containsKey(sentence.getId())){
                        continue;
                    }else{
                        sentenceHashMap.put(sentence.getId(),sentence);
                        tagParts.add(sentence);
                    }
                }
            }
            // 对于添加句子部分要额外判断下是否重复
            for(Sentence sentence : sentencesParts){
                if(sentenceHashMap.containsKey(sentence.getId())){
                    continue;
                }else{
                    sentenceHashMap.put(sentence.getId(),sentence);
                    list.add(sentence);
                }
            }
            list.addAll(giantParts);
            list.addAll(originParts);
            list.addAll(tagParts);
        }else if(option == 1){
            // 只有句子
            List<Sentence> sentencesParts = sentenceDao.getSentencesBySentenceContent(searchContent);
            list.addAll(sentencesParts);
        }else if (option == 2){
            // 作者部分
            List<GiantInfo> giantInfos = sentenceDao.getGiantInfosByGiantName(searchContent);
            List<Sentence> giantParts = new ArrayList<Sentence>();
            for(GiantInfo giantInfo : giantInfos){
                List<Sentence> sentences = sentenceDao.getSentenceByAuthorId(giantInfo.getId());
                giantParts.addAll(sentences);
            }
            list.addAll(giantParts);
        }else if(option == 3){
            // 出处部分
            List<OriginInfo> originInfos = sentenceDao.getOriginInfosByOriginName(searchContent);
            List<Sentence> originParts = new ArrayList<Sentence>();
            for(OriginInfo originInfo : originInfos){
                List<Sentence> sentences = sentenceDao.getSentencesByOriginId(originInfo.getId());
                originParts.addAll(sentences);
            }
            list.addAll(originParts);
        }else if(option == 4){
            // 标签部分
            List<Tag> tags = sentenceDao.getTagsByTagName(searchContent);
            List<Sentence> tagParts = new ArrayList<Sentence>();
            for(Tag tag : tags){
                List<TagQuote> tagQuotes = sentenceDao.getTagQuotesByTagId(tag.getId());
                for(TagQuote tagQuote : tagQuotes){
                    Sentence sentence = sentenceDao.getSentenceById(tagQuote.getSentenceId());
                    tagParts.add(sentence);
                }
            }
            list.addAll(tagParts);
        }

        long myId = 0;
        UserInfo userInfo = (UserInfo) session.get("userInfo");
        if(userInfo != null){
            myId = userInfo.getId();
        }
        // 初始化sentenceEntities
        for(Sentence sentence : list){
            SentenceEntity sentenceEntity = new SentenceEntity();
            sentenceEntity.setSentenceDao(sentenceDao);
            sentenceEntity.init(sentence.getId(),myId);
            sentenceEntities.add(sentenceEntity);
        }

    }


    // 初始化句子集
    public void initSentenceCollection(){
        long myId = 0;
        UserInfo userInfo = (UserInfo) session.get("userInfo");
        if(userInfo != null){
            myId = userInfo.getId();
        }
        List<SentenceCollection> sentenceCollections = collectionDao.getSentenceCollectionByCollectionName(searchContent);
        for(SentenceCollection sc : sentenceCollections){
            OutLookCollectionEntity outLookCollectionEntity = new OutLookCollectionEntity();
            outLookCollectionEntity.setSentenceCollection(sc);
            outLookCollectionEntity.setPublisherInfo(peopleDao.getUserInfoById(sc.getPublisherId()));
            outLookCollectionEntity.setLoveOrNot(collectionDao.checkUserLoveCollection(myId,sc.getId()));
            collectionEntities.add(outLookCollectionEntity);
        }
    }

    // 初始化用户
    public void initUser(){
        long myId = 0;
        UserInfo userInfo = (UserInfo) session.get("userInfo");
        if(userInfo != null){
            myId = userInfo.getId();
        }
        List<UserInfo> userInfos = peopleDao.getUserInfoByuserName(searchContent);
        for(UserInfo userInfo1 : userInfos){
            PeopleEntity peopleEntity = new PeopleEntity();
            peopleEntity.setPeopleDao(peopleDao);
            peopleEntity.init(myId, userInfo1.getId());
            peopleEntities.add(peopleEntity);
        }
    }



    /**
    * @author hasee
    * @Description 初始化分类和选项
    * @Date 10:59 2018-07-14
    * @Param []
    * @return void
    **/
    public void initCategoryAndOption(){
        if(searchCategory == null || "".equals(searchCategory) || "0".equals(searchCategory)){
            category = 0;
            System.out.println("category0:" + category);
        }else if("1".equals(searchCategory)){
            category = 1;
            System.out.println("category1:" + category);
        }else if("2".equals(searchCategory)){
            category = 2;
            System.out.println("category2:" + category);
        }

        if(searchOption == null || "".equals(searchOption) || "0".equals(searchOption)){
            option = 0;
        }else if("1".equals(searchOption)){
            option = 1;
        }else if("2".equals(searchOption)){
            option = 2;
        }else if("3".equals(searchOption)){
            option = 3;
        }else if("4".equals(searchOption)){
            option = 4;
        }

    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    public String getSearchCategory() {
        return searchCategory;
    }

    public void setSearchCategory(String searchCategory) {
        this.searchCategory = searchCategory;
    }

    public String getSearchOption() {
        return searchOption;
    }

    public void setSearchOption(String searchOption) {
        this.searchOption = searchOption;
    }

    public List<SentenceEntity> getSentenceEntities() {
        return sentenceEntities;
    }

    public void setSentenceEntities(List<SentenceEntity> sentenceEntities) {
        this.sentenceEntities = sentenceEntities;
    }


    public List<PeopleEntity> getPeopleEntities() {
        return peopleEntities;
    }

    public void setPeopleEntities(List<PeopleEntity> peopleEntities) {
        this.peopleEntities = peopleEntities;
    }

    public long getCategory() {
        return category;
    }

    public void setCategory(long category) {
        this.category = category;
    }

    public long getOption() {
        return option;
    }

    public void setOption(long option) {
        this.option = option;
    }

    public CollectionDao getCollectionDao() {
        return collectionDao;
    }

    public void setCollectionDao(CollectionDao collectionDao) {
        this.collectionDao = collectionDao;
    }

    public PeopleDao getPeopleDao() {
        return peopleDao;
    }

    public void setPeopleDao(PeopleDao peopleDao) {
        this.peopleDao = peopleDao;
    }

    public SentenceDao getSentenceDao() {
        return sentenceDao;
    }

    public void setSentenceDao(SentenceDao sentenceDao) {
        this.sentenceDao = sentenceDao;
    }


    public List<OutLookCollectionEntity> getCollectionEntities() {
        return collectionEntities;
    }

    public void setCollectionEntities(List<OutLookCollectionEntity> collectionEntities) {
        this.collectionEntities = collectionEntities;
    }
}
