package action;

import bean.*;
import com.opensymphony.xwork2.ActionSupport;
import dao.*;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import pageEntity.PeopleEntity;
import pageEntity.SentenceEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ToUserLoveAction
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-08 23:22
 * Version 1.0
 */
public class ToUserLoveAction extends ActionSupport implements SessionAware{

    // 种类，0:句子 1： 用户
    private String type;

    // 种类内部Id:
    // 对句子：0：推荐句子，1：热门句子，2：热门原创，3：最新发布，4：分类句子，5：标签句子
    // 对用户：0：喜欢句子，1：喜欢名家，2：喜欢作品。3：关注某一用户，4：某用户的关注
    private String typeId;

    // 句子内部种类细分后，所需要的一些参数（并非所有typeId都会需要一个contentId）
    private String contentId;


    private Map session;

    @Autowired
    private SentenceDao sentenceDao;

    @Autowired
    private PeopleDao peopleDao;

    @Autowired
    private InformationDao informationDao;

    @Autowired
    private GiantDao giantDao;

    @Autowired
    private OriginDao originDao;


    // 显示的分类: 0：推荐句子，1：热门句子，2：热门原创，3：最新发布，4：分类句子，5：标签句子
    // 6：喜欢某句子用户，7：喜欢某名家用户，8：喜欢某出处用户，9：某用户关注的用户，10：某用户的粉丝
    private long showCategory;
    // 显示在页面上的标题（分为句子或用户）
    private String showContent;
    private String showUrl;
    // 显示的列表
    private List<SentenceEntity> sentenceEntities;
    private List<PeopleEntity> peopleEntities;




    @Override
    public String execute() throws Exception {

        System.out.println(type + "  " + typeId + "  " + contentId);
        long myType = 0;
        long myTypeId = 0;
        long myContentId = 0;
        try {
            myType = Long.valueOf(type);
            myTypeId = Long.valueOf(typeId);
            if(myTypeId >= 4 ){
                myContentId = Long.valueOf(contentId);
            }
        }catch (Exception e){
            return ERROR;
        }
        if(myType == 0){
            if(handleSentences()){

            }else{
                return ERROR;
            }
        }else if(myType == 1){
            if(handleUsers()){

            }else{
                return ERROR;
            }
        }else{
            return ERROR;
        }

        session.put("showCategory",showCategory);
        session.put("showContent",showContent);
        session.put("showUrl",showUrl);
        session.put("sentenceEntities",sentenceEntities);
        session.put("peopleEntities",peopleEntities);



        return SUCCESS;
    }

    /**
    * @author hasee
    * @Description 处理句子部分
    * @Date 10:17 2018-07-15
    * @Param []
    * @return void
    **/
    public boolean handleSentences(){
        long myTypeId = 0;
        try {
            myTypeId = Long.valueOf(typeId);
        }catch (Exception e){
            return false;
        }
        if(myTypeId == 0){
            showCategory = 0;
            initRecommendSentences();
        }else if(myTypeId == 1){
            showCategory = 1;
            initHotSentences();
        }else if(myTypeId == 2){
            showCategory = 2;
            initHotOriginals();
        }else if(myTypeId == 3){
            showCategory = 3;
            initNewPublishs();
        } else{
            long myContentId = 0;
            try{
                myContentId = Long.valueOf(contentId);
            }catch (Exception e){
                return false;
            }
            if(myTypeId == 4){
                showCategory = 4;
                Category category = informationDao.getCategoryByCategoryId(myContentId);
                if(category == null){
                    return false;
                }
                showContent = category.getCategoryName();
                showUrl = category.getId() + "";
                if(initCategorySentences(myContentId)){

                }else {
                    return false;
                }
            }else if(myTypeId == 5){
                showCategory = 5;
                Tag tag = informationDao.getTagByTagId(myContentId);
                if(tag == null){
                    return false;
                }
                showContent = tag.getName();
                showUrl = tag.getId() + "";
                if(initTagSentences(myContentId)){

                }else{
                    return false;
                }
            }
        }
        return true;
    }


    /**
    * @author hasee
    * @Description 处理用户部分
    * @Date 10:18 2018-07-15
    * @Param []
    * @return void
    **/
    public boolean handleUsers(){
        long myTypeId = 0;
        try {
            myTypeId = Long.valueOf(typeId);
        }catch (Exception e){
            return false;
        }
        long myContentId = 0;
        try{
            myContentId = Long.valueOf(contentId);
        }catch (Exception e){
            return false;
        }
        if(myTypeId == 0){
            showCategory = 6;

            Sentence sentence = sentenceDao.getSentenceById(myContentId);
            if(sentence == null){
                return false;
            }
            showContent = sentence.getContent();
            showUrl = sentence.getId() + "";

            initLoveSentenceUsers(myContentId);
        }else if(myTypeId == 1){
            showCategory = 7;

            GiantInfo giant = giantDao.getGiantInfoById(myContentId);
            if(giant == null){
                return false;
            }
            showContent = giant.getName();
            showUrl = giant.getId() + "";

            initLoveGiantUsers(myContentId);
        }else if(myTypeId == 2){
            showCategory = 8;

            OriginInfo originInfo = originDao.getOriginInfoById(myContentId);
            if(originInfo == null){
                return false;
            }
            showContent = originInfo.getName();
            showUrl = originInfo.getId() + "";

            initLoveOriginUsers(myContentId);
        }else if(myTypeId == 3){
            showCategory = 9;

            UserInfo userInfo = peopleDao.getUserInfoById(myContentId);
            if(userInfo == null){
                return false;
            }
            showContent = userInfo.getUserName();
            showUrl = userInfo.getId() + "";

            initFollowUsers(myContentId);
        }else if(myTypeId == 4){
            showCategory = 10;

            UserInfo userInfo = peopleDao.getUserInfoById(myContentId);
            if(userInfo == null){
                return false;
            }
            showContent = userInfo.getUserName();
            showUrl = userInfo.getId() + "";

            initFanUsers(myContentId);
        }else {
            return false;
        }
        return true;
    }




        /**
    * @author hasee
    * @Description 将句子列表转化为sentenceEntity并存入eneities中
    * @Date 12:45 2018-07-15
    * @Param [sentences]
    * @return
    **/
    public void putSentencesIntoEntities(List<Sentence> sentences){
        if(sentences == null){
            return;
        }
        long myId = 0;
        UserInfo myInfo = (UserInfo) session.get("userInfo");
        if(myInfo != null){
            myId = myInfo.getId();
        }
        if(sentenceEntities != null){
            sentenceEntities.clear();
        }else {
            sentenceEntities = new ArrayList<SentenceEntity>();
        }
        for(Sentence s: sentences){
            SentenceEntity sentenceEntity = new SentenceEntity();
            sentenceEntity.setSentenceDao(sentenceDao);
            sentenceEntity.init(s.getId(),myId);
            sentenceEntities.add(sentenceEntity);
        }
    }

    /**
    * @author hasee
    * @Description 重载putSentencesInfoEntities方法，这个是直接传id
    * @Date 12:54 2018-07-15
    * @Param [sentenceIds]
    * @return void
    **/
    public void putSentencesInfoEntities(List<Long> sentenceIds){
        if(sentenceIds == null){
            return;
        }
        long myId = 0;
        UserInfo myInfo = (UserInfo) session.get("userInfo");
        if(myInfo != null){
            myId = myInfo.getId();
        }
        if(sentenceEntities != null){
            sentenceEntities.clear();
        }else {
            sentenceEntities = new ArrayList<SentenceEntity>();
        }
        for(Long s: sentenceIds){
            SentenceEntity sentenceEntity = new SentenceEntity();
            sentenceEntity.setSentenceDao(sentenceDao);
            sentenceEntity.init(s,myId);
            sentenceEntities.add(sentenceEntity);
        }
    }



    /**
    * @author hasee
    * @Description 初始化推荐句子
    * @Date 12:42 2018-07-15
    * @Param []
    * @return void
    **/
    public void initRecommendSentences(){
        List<Sentence> sentences = sentenceDao.getRecommendSentences(30);
        putSentencesIntoEntities(sentences);
    }

    /**
    * @author hasee
    * @Description 初始化热门句子
    * @Date 12:50 2018-07-15
    * @Param []
    * @return void
    **/
    public void initHotSentences(){
        List<Sentence> sentences = sentenceDao.getHotSentences(30);
        putSentencesIntoEntities(sentences);
    }

    /**
    * @author hasee
    * @Description 初始化热门原创句子
    * @Date 12:51 2018-07-15
    * @Param []
    * @return void
    **/
    public void initHotOriginals(){
        List<Sentence> sentences = sentenceDao.getHotOriginSentences(30);
        putSentencesIntoEntities(sentences);
    }

    /**
    * @author hasee
    * @Description 初始化最新发布句子
    * @Date 12:51 2018-07-15
    * @Param []
    * @return void
    **/
    public void initNewPublishs(){
        List<Sentence> sentences = sentenceDao.getNewPublishSentence(30);
        putSentencesIntoEntities(sentences);
    }



    /**
    * @author hasee
    * @Description 初始化分类下句子
    * @Date 13:12 2018-07-15
    * @Param [category]
    * @return java.lang.Boolean
    **/
    public Boolean initCategorySentences(long categoryId){
        List<Long> sentences = sentenceDao.getSentenceIdsByCategoryId(categoryId,0,30);
        putSentencesInfoEntities(sentences);
        return true;
    }


    /**
    * @author hasee
    * @Description 初始化标签下句子
    * @Date 13:17 2018-07-15
    * @Param [tagId]
    * @return java.lang.Boolean
    **/
    public Boolean initTagSentences(long tagId){
        List<Long> sentences = sentenceDao.getSentenceIdsByTagId(tagId,0,30);
        putSentencesInfoEntities(sentences);
        return true;
    }



    public void putUserIntoEntities(List<Long> userIds){
        //System.out.println("putUSerIntoEntities");
        //System.out.println(userIds);
        if (userIds == null) {
            return;
        }
        if(peopleEntities != null){
            peopleEntities.clear();
        } else {
            peopleEntities = new ArrayList<PeopleEntity>();
        }
        long myId = 0;
        UserInfo myInfo = (UserInfo) session.get("userInfo");
        if(myInfo != null){
            myId = myInfo.getId();
        }
        for(Long userId : userIds){
            PeopleEntity peopleEntity = new PeopleEntity();
            peopleEntity.setPeopleDao(peopleDao);
            peopleEntity.init(myId, userId);
            peopleEntities.add(peopleEntity);
        }
       // System.out.println(peopleEntities);
    }



    /**
    * @author hasee
    * @Description 初始化喜欢某句子的用户列表
    * @Date 13:35 2018-07-15
    * @Param [sentenceId]
    * @return void
    **/
    public void initLoveSentenceUsers(long sentenceId){
        List<Long> userIds = peopleDao.getUserIdsBySentenceId(sentenceId,0,30);
        System.out.println(userIds);
        putUserIntoEntities(userIds);
    }

    /**
    * @author hasee
    * @Description 根据名家id获取喜欢该名家的用户的信息
    * @Date 14:09 2018-07-15
    * @Param [giantId]
    * @return void
    **/
    public void initLoveGiantUsers(long giantId){
        List<Long> userIds = peopleDao.getUserIdsByGiantId(giantId,0,30);
        putUserIntoEntities(userIds);
    }

    /**
    * @author hasee
    * @Description 根据出处id获取喜欢这个出处的用户的信息
    * @Date 14:09 2018-07-15
    * @Param [originId]
    * @return void
    **/
    public void initLoveOriginUsers(long originId){
        List<Long> userIds = peopleDao.getUserIdsByOriginId(originId,0,30);
        putUserIntoEntities(userIds);
    }

    /**
    * @author hasee
    * @Description 根据用户id获取其所关注的用户的id
    * @Date 14:09 2018-07-15
    * @Param [userId]
    * @return void
    **/
    public void initFollowUsers(long userId){
        List<Long> userIds = peopleDao.getUserIdsByFollowerId(userId,0,30);
        putUserIntoEntities(userIds);
    }

    /**
    * @author hasee
    * @Description 根据用户Id获取该用户的粉丝的id
    * @Date 14:08 2018-07-15
    * @Param [userId]
    * @return void
    **/
    public void initFanUsers(long userId){
        List<Long> userIds = peopleDao.getUserIdsByFollowingId(userId,0,30);
        putUserIntoEntities(userIds);
    }



    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public Map getSession() {
        return session;
    }


    public long getShowCategory() {
        return showCategory;
    }

    public void setShowCategory(long showCategory) {
        this.showCategory = showCategory;
    }

    public String getShowContent() {
        return showContent;
    }

    public void setShowContent(String showContent) {
        this.showContent = showContent;
    }

    public String getShowUrl() {
        return showUrl;
    }

    public void setShowUrl(String showUrl) {
        this.showUrl = showUrl;
    }

    public InformationDao getInformationDao() {
        return informationDao;
    }

    public void setInformationDao(InformationDao informationDao) {
        this.informationDao = informationDao;
    }

    public GiantDao getGiantDao() {
        return giantDao;
    }

    public void setGiantDao(GiantDao giantDao) {
        this.giantDao = giantDao;
    }

    public OriginDao getOriginDao() {
        return originDao;
    }

    public void setOriginDao(OriginDao originDao) {
        this.originDao = originDao;
    }


}
