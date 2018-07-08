package bean;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @ClassName SentenceCollection
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-07 10:40
 * Version 1.0
 */
@Entity
@Table(name = "sentence_collection", schema = "yinyousentence", catalog = "")
public class SentenceCollection {
    private long id;
    private String name;
    private long publisherId;
    private Timestamp publishDate;
    private String imgPath;
    private String introduction;
    private long sentenceNum;
    private long loveNum;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 25)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "publish_date", nullable = false)
    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    @Basic
    @Column(name = "img_path", nullable = false, length = 255)
    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Basic
    @Column(name = "introduction", nullable = true, length = 255)
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Basic
    @Column(name = "sentence_num", nullable = false)
    public long getSentenceNum() {
        return sentenceNum;
    }

    public void setSentenceNum(long sentenceNum) {
        this.sentenceNum = sentenceNum;
    }

    @Basic
    @Column(name = "love_num", nullable = false)
    public long getLoveNum() {
        return loveNum;
    }

    public void setLoveNum(long loveNum) {
        this.loveNum = loveNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SentenceCollection that = (SentenceCollection) o;

        if (id != that.id) return false;
        if (publisherId != that.publisherId) return false;
        if (sentenceNum != that.sentenceNum) return false;
        if (loveNum != that.loveNum) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (publishDate != null ? !publishDate.equals(that.publishDate) : that.publishDate != null) return false;
        if (imgPath != null ? !imgPath.equals(that.imgPath) : that.imgPath != null) return false;
        if (introduction != null ? !introduction.equals(that.introduction) : that.introduction != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (publisherId ^ (publisherId >>> 32));
        result = 31 * result + (publishDate != null ? publishDate.hashCode() : 0);
        result = 31 * result + (imgPath != null ? imgPath.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        result = 31 * result + (int) (sentenceNum ^ (sentenceNum >>> 32));
        result = 31 * result + (int) (loveNum ^ (loveNum >>> 32));
        return result;
    }
}
