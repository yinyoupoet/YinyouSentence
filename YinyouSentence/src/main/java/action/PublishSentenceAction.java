package action;

import bean.*;
import com.opensymphony.xwork2.ActionSupport;
import dao.PublishSentenceDao;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Map;

/**
 * @ClassName PublishSentenceAction
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-07 19:30
 * Version 1.0
 */
public class PublishSentenceAction extends ActionSupport implements SessionAware{
    @Autowired
    private PublishSentenceDao publishSentenceDao;

    private Map session;

    private String collectSelect;
    private String sentenceContent;
    private String original;
    private String authorName;
    private String bookName;
    private String categorySelect;
    private String tagInput;

    private long sentenceId;


    @Override
    public String execute() throws Exception {
        UserInfo userInfo = (UserInfo) session.get("userInfo");
        if(userInfo == null){
            return ERROR;
        }

        Sentence sentence = new Sentence();
        // 1、将该句子存入数据库
        sentence.setContent(sentenceContent);
        sentence.setPublisherId(userInfo.getId());
        if(original == null){
            sentence.setIsOriginal((byte) 0);
        }else{
            sentence.setIsOriginal((byte) 1);
        }
        long authorId = 0;
        if(!"".equals(authorName.trim())){
            authorId = getAuthorId(authorName);
        }
        long originId = 0;
        if(!"".equals(bookName.trim())){
            originId = getOriginId(authorId,bookName);
        }
        Timestamp publishTime = new Timestamp(System.currentTimeMillis());
        // 存储句子
        sentence.setAuthorId(authorId);
        sentence.setOriginId(originId);
        sentence.setPublishTime(publishTime);
        sentence.setLoveNum(0);
        sentence.setTag(tagInput);
        publishSentenceDao.addSentence(sentence);


        // 处理一下标签
        initTags(sentence.getId(),tagInput);

        // 如果选择中了句子集，将句子加入到句子集
        if(! "0".equals(collectSelect.trim())){
            addSentenceToSentenceCollection(sentence.getId(),collectSelect.trim());
        }

        // 添加句子到对应的类别
        if(original != null){
            // 是原创句子
            long categorySelectId = Long.valueOf(categorySelect);
            if(categorySelectId != (long) 15){
                // 如果是原创但是用户选择的分类不是原创，那么既要存到原创分类，也要存到对应的分类
                addSentenceIntoCategory(sentence.getId(),(long) 15);
            }
            addSentenceIntoCategory(sentence.getId(),categorySelectId);
        }else{
            // 非原创
            long categorySelectId = Long.valueOf(categorySelect);
            addSentenceIntoCategory(sentence.getId(),categorySelectId);
        }

        this.sentenceId = sentence.getId();

        return SUCCESS;
    }


    /**
    * @author hasee
    * @Description 获得作者ID
    * @Date 22:28 2018-07-07
    * @Param [userName]
    * @return long
    **/
    public long getAuthorId(String authorName){
        // 判断作者是否存在，不存在就新建一个
        if(! publishSentenceDao.isAuthorExist(authorName)){
            GiantInfo giantInfo = new GiantInfo();
            giantInfo.setImgPath("/imgs/sys/defaultGiant.jpg");
            giantInfo.setIntroduction("");
            giantInfo.setLoveNum(0);
            giantInfo.setName(authorName);
            publishSentenceDao.addGiant(giantInfo);
        }
        long id = publishSentenceDao.getAuthorId(authorName);
        return id;
    }

    // 获得出处ID
    public long getOriginId(long authorId, String originName){
        // 判断作者是否存在，不存在就新建一个
        if(! publishSentenceDao.isOriginExist(originName)){
            OriginInfo originInfo = new OriginInfo();
            originInfo.setAuthorId(authorId);
            originInfo.setImgPath("/imgs/sys/defaultOrigin.png");
            originInfo.setIntroduction("");
            originInfo.setLoveNum(0);
            if (!originName.startsWith("《")){
                originName = "《" + originName;
            }
            if(! originName.endsWith("》")){
                originName = originName + "》";
            }
            originInfo.setName(originName);

            publishSentenceDao.addOrigin(originInfo);
        }
        long id = publishSentenceDao.getOriginId(originName);
        return id;
    }

    // 处理下标签
    public void initTags(long sentenceId, String tagInput){
        tagInput = tagInput.trim();
        String[] tags = tagInput.split("\\s+");
        if(tags != null && tags.length > 0){
            for(String tag : tags){
                // 依次将标签存入数据库，如果该标签不存在，则创建一个
                long tagId = 0;
                if(! publishSentenceDao.isTagExist(tag)){
                    Tag tag1 = new Tag();
                    if(tag.length() >= 25){
                        tag =tag.substring(0,24);
                    }
                    tag1.setName(tag);
                    tag1.setQuoteNum(1);
                    publishSentenceDao.addTag(tag1);
                    tagId = tag1.getId();
                    System.out.println("新： " + tagId);
                }else{
                    tagId = publishSentenceDao.getTagId(tag);
                    System.out.println("旧： " + tagId);
                    publishSentenceDao.addTagQuote(tagId);
                }
                // 对标签引用表进操作
                TagQuote tagQuote = new TagQuote();
                tagQuote.setSentenceId(sentenceId);
                tagQuote.setTagId(tagId);
            }
        }
    }

    // 将句子加入句子集
    public void addSentenceToSentenceCollection(long sentenceId, String clt_id){
        long collectionId = 0;
        try{
            collectionId = Long.valueOf(clt_id);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
        if(collectionId == 0){
            return;
        }
        CollectionSentence collectionSentence = new CollectionSentence();
        collectionSentence.setCollectionId(collectionId);
        collectionSentence.setSentenceId(sentenceId);
        publishSentenceDao.putSentenceIntoCollection(collectionSentence);
    }

    // 将句子存入对应分类表
    public void addSentenceIntoCategory(long sentenceId, long categoryId){
        SentenceCategory sentenceCategory = new SentenceCategory();
        sentenceCategory.setSentenceId(sentenceId);
        sentenceCategory.setCategoryId(categoryId);
        publishSentenceDao.putSentenceIntoCategory(sentenceCategory);
    }

    public String getCollectSelect() {
        return collectSelect;
    }

    public void setCollectSelect(String collectSelect) {
        this.collectSelect = collectSelect;
    }

    public String getSentenceContent() {
        return sentenceContent;
    }

    public void setSentenceContent(String sentenceContent) {
        this.sentenceContent = sentenceContent;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getCategorySelect() {
        return categorySelect;
    }

    public void setCategorySelect(String categorySelect) {
        this.categorySelect = categorySelect;
    }

    public String getTagInput() {
        return tagInput;
    }

    public void setTagInput(String tagInput) {
        this.tagInput = tagInput;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }


    public void setPublishSentenceDao(PublishSentenceDao publishSentenceDao) {
        this.publishSentenceDao = publishSentenceDao;
    }


    public long getSentenceId() {
        return sentenceId;
    }

    public void setSentenceId(long sentenceId) {
        this.sentenceId = sentenceId;
    }
}
