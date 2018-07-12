package pageEntity;

import bean.*;
import dao.SentenceDao;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SentenceEntity
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-08 15:53
 * Version 1.0
 */
public class SentenceEntity {
    @Autowired
    private SentenceDao sentenceDao;

    private Boolean original;


    private Sentence sentence;
    private GiantInfo giantInfo;
    private UserInfo userInfo;
    private List<Sentence> giantSentences = new ArrayList<Sentence>();
    private List<UserInfo> loveUsers = new ArrayList<UserInfo>();
    private List<Sentence> originSentences = new ArrayList<Sentence>();
    private List<Tag> tags = new ArrayList<Tag>();

    private OriginInfo originInfo;
    private Boolean userLove = false;
    private long commentNum = 0;


    // 专门给people页面使用的一个属性
    private Boolean peopleLove;

    // 专门给people页面使用的初始化方式
    public void init(long sentenceId, long myId, long uId){
        init(sentenceId,myId);
        long loveReord = sentenceDao.checkUserLoveSentence(uId,sentenceId);
        if(loveReord == 0){
            peopleLove = false;
        }else{
            peopleLove = true;
        }
    }


    // 初始化数据，这个id必须判断一定存在才会到这
    public void init(long sentenceId,long myId){
        if(giantSentences != null){
            giantSentences.clear();
        }
        if(loveUsers != null){
            loveUsers.clear();
        }

        sentence = sentenceDao.getSentenceById(sentenceId);

        int org = sentence.getIsOriginal();
        System.out.println("是否原创：" + sentence.getIsOriginal() + " " + org);
        if(org == 0){
            original = false;
        }else{
            original = true;
        }


        if(sentence.getAuthorId() != 0){
            giantInfo = sentenceDao.getGiantInfoById(sentence.getAuthorId());
            giantSentences = sentenceDao.getSentencesByAuthorId(sentence.getAuthorId());
            originSentences = sentenceDao.getSentencesByOriginId(sentence.getOriginId());
            if(giantSentences != null && giantSentences.size() > 3){
                giantSentences.subList(0,3);
            }
            if(originSentences != null && originSentences.size() > 3){
                originSentences.subList(0,3);
            }

        }else{
            giantInfo = null;
            giantSentences = null;
            originSentences = null;
        }

        if(sentence.getOriginId() != 0){
            originInfo = sentenceDao.getOriginInfoById(sentence.getOriginId());
        }else{
            originInfo = null;
        }

        userInfo = sentenceDao.getUserInfoById(sentence.getPublisherId());
        List<Long> userIds = sentenceDao.getLoveSentenceUserIdBySentenceId(sentence.getId());

        for(Long id : userIds){
            if(id.equals(myId)){
                System.out.println("屏蔽了一个:" + myId);
                continue;
            }
            System.out.println("里面至少有一个: " + id);
            UserInfo userInfo2 = new UserInfo();
            userInfo2 = sentenceDao.getUserInfoById(id);
            loveUsers.add(userInfo2);
        }
        if(loveUsers != null && loveUsers.size() > 3){
            loveUsers = loveUsers.subList(0,3);
        }



        initTags();

        // 判断用户是否喜欢该句子
        initLoveCheck(sentenceId, myId);

        commentNum = sentenceDao.getCommentNumBySentenceId(sentenceId);
    }

    // 判断用户是否喜欢该句子
    public void initLoveCheck(long sentenceId,long userId){
        if(userId == 0){
            return;
        }
        long love = sentenceDao.checkUserLoveSentence(userId,sentenceId);
        if(love != 0){
            userLove = true;
        }else{
            userLove = false;
        }
    }

    // 初始化标签列表
    public void initTags(){
        if(tags != null){
            tags.clear();
        }
        String s_tag = sentence.getTag();
        String[] array_tags = s_tag.split("\\s+");
        for(String s : array_tags){
            Tag tag = sentenceDao.getTagByName(s);
            if(tag != null){
                tags.add(tag);
            }
        }
    }

    public SentenceDao getSentenceDao() {
        return sentenceDao;
    }

    public void setSentenceDao(SentenceDao sentenceDao) {
        this.sentenceDao = sentenceDao;
    }

    public Sentence getSentence() {
        return sentence;
    }

    public void setSentence(Sentence sentence) {
        this.sentence = sentence;
    }

    public GiantInfo getGiantInfo() {
        return giantInfo;
    }

    public void setGiantInfo(GiantInfo giantInfo) {
        this.giantInfo = giantInfo;
    }

    public List<Sentence> getGiantSentences() {
        return giantSentences;
    }

    public void setGiantSentences(List<Sentence> giantSentences) {
        this.giantSentences = giantSentences;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<UserInfo> getLoveUsers() {
        return loveUsers;
    }

    public void setLoveUsers(List<UserInfo> loveUsers) {
        this.loveUsers = loveUsers;
    }


    public OriginInfo getOriginInfo() {
        return originInfo;
    }

    public void setOriginInfo(OriginInfo originInfo) {
        this.originInfo = originInfo;
    }

    public Boolean getOriginal() {
        return original;
    }

    public void setOriginal(Boolean original) {
        this.original = original;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Sentence> getOriginSentences() {
        return originSentences;
    }

    public void setOriginSentences(List<Sentence> originSentences) {
        this.originSentences = originSentences;
    }


    public Boolean getUserLove() {
        return userLove;
    }

    public void setUserLove(Boolean userLove) {
        this.userLove = userLove;
    }

    public long getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(long commentNum) {
        this.commentNum = commentNum;
    }

    public Boolean getPeopleLove() {
        return peopleLove;
    }

    public void setPeopleLove(Boolean peopleLove) {
        this.peopleLove = peopleLove;
    }
}
