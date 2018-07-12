package pageEntity;

import bean.SentenceCollection;
import bean.UserInfo;
import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * @ClassName OutLookCollectionEntity
 * @Description 显示的是句子集的大概，而非全貌，即将句子集的属性示人，而非里面的句子
 * @Author hasee
 * @Date 2018-07-12 16:45
 * Version 1.0
 */
public class OutLookCollectionEntity {
    private SentenceCollection sentenceCollection;
    private UserInfo publisherInfo;
    private Boolean loveOrNot;

    public SentenceCollection getSentenceCollection() {
        return sentenceCollection;
    }

    public void setSentenceCollection(SentenceCollection sentenceCollection) {
        this.sentenceCollection = sentenceCollection;
    }

    public UserInfo getPublisherInfo() {
        return publisherInfo;
    }

    public void setPublisherInfo(UserInfo publisherInfo) {
        this.publisherInfo = publisherInfo;
    }

    public Boolean getLoveOrNot() {
        return loveOrNot;
    }

    public void setLoveOrNot(Boolean loveOrNot) {
        this.loveOrNot = loveOrNot;
    }
}
