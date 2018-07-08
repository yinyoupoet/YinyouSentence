package bean;

import javax.persistence.*;

/**
 * @ClassName TagQuote
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-07 10:40
 * Version 1.0
 */
@Entity
@Table(name = "tag_quote", schema = "yinyousentence", catalog = "")
public class TagQuote {
    private long id;
    private long sentenceId;
    private long tagId;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "sentence_id", nullable = false)
    public long getSentenceId() {
        return sentenceId;
    }

    public void setSentenceId(long sentenceId) {
        this.sentenceId = sentenceId;
    }

    @Basic
    @Column(name = "tag_id", nullable = false)
    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagQuote tagQuote = (TagQuote) o;

        if (id != tagQuote.id) return false;
        if (sentenceId != tagQuote.sentenceId) return false;
        if (tagId != tagQuote.tagId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (sentenceId ^ (sentenceId >>> 32));
        result = 31 * result + (int) (tagId ^ (tagId >>> 32));
        return result;
    }
}
