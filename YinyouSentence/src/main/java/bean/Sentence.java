package bean;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @ClassName Sentence
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-07 10:40
 * Version 1.0
 */
@Entity
public class Sentence {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String content;
    private long publisherId;
    private byte isOriginal;
    private Long authorId;
    private Long originId;
    private Timestamp publishTime;
    private long loveNum;
    private String tag;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "content", nullable = false, length = 255)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "publisher_id", nullable = false)
    public long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(long publisherId) {
        this.publisherId = publisherId;
    }

    @Basic
    @Column(name = "is_original", nullable = false)
    public byte getIsOriginal() {
        return isOriginal;
    }

    public void setIsOriginal(byte isOriginal) {
        this.isOriginal = isOriginal;
    }

    @Basic
    @Column(name = "author_id", nullable = true)
    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    @Basic
    @Column(name = "origin_id", nullable = true)
    public Long getOriginId() {
        return originId;
    }

    public void setOriginId(Long originId) {
        this.originId = originId;
    }

    @Basic
    @Column(name = "publish_time", nullable = false)
    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }

    @Basic
    @Column(name = "love_num", nullable = false)
    public long getLoveNum() {
        return loveNum;
    }

    public void setLoveNum(long loveNum) {
        this.loveNum = loveNum;
    }

    @Basic
    @Column(name = "tag", nullable = false, length = 255)
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sentence sentence = (Sentence) o;

        if (id != sentence.id) return false;
        if (publisherId != sentence.publisherId) return false;
        if (isOriginal != sentence.isOriginal) return false;
        if (loveNum != sentence.loveNum) return false;
        if (content != null ? !content.equals(sentence.content) : sentence.content != null) return false;
        if (authorId != null ? !authorId.equals(sentence.authorId) : sentence.authorId != null) return false;
        if (originId != null ? !originId.equals(sentence.originId) : sentence.originId != null) return false;
        if (publishTime != null ? !publishTime.equals(sentence.publishTime) : sentence.publishTime != null)
            return false;
        if (tag != null ? !tag.equals(sentence.tag) : sentence.tag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (int) (publisherId ^ (publisherId >>> 32));
        result = 31 * result + (int) isOriginal;
        result = 31 * result + (authorId != null ? authorId.hashCode() : 0);
        result = 31 * result + (originId != null ? originId.hashCode() : 0);
        result = 31 * result + (publishTime != null ? publishTime.hashCode() : 0);
        result = 31 * result + (int) (loveNum ^ (loveNum >>> 32));
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        return result;
    }
}
