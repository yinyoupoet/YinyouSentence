package pageEntity;

import bean.*;
import dao.SentenceDao;
import org.springframework.beans.factory.annotation.Autowired;

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
    private List<Tag> tags = new ArrayList<Tag>();

    private OriginInfo originInfo;



    // 初始化数据，这个id必须判断一定存在才会到这
    public void init(long sentenceId){
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
            if(giantSentences.size() > 3){
                giantSentences.subList(0,3);
            }

        }else{
            giantInfo = null;
            giantSentences = null;
        }

        if(sentence.getOriginId() != 0){
            originInfo = sentenceDao.getOriginInfoById(sentence.getOriginId());
        }else{
            originInfo = null;
        }

        userInfo = sentenceDao.getUserInfoById(sentence.getPublisherId());
        List<Long> userIds = sentenceDao.getLoveSentenceUserIdBySentenceId(sentence.getId());
        for(long id : userIds){
            UserInfo userInfo1 = new UserInfo();
            userInfo1 = sentenceDao.getUserInfoById(id);
            loveUsers.add(userInfo1);
        }

        initTags();
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
}
