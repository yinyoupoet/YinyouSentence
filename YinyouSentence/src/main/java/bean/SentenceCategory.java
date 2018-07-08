package bean;

import javax.persistence.*;

/**
 * @ClassName SentenceCategory
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-07 11:11
 * Version 1.0
 */
@Entity
@Table(name = "sentence_category", schema = "yinyousentence", catalog = "")
public class SentenceCategory {
    private long id;
    private long sentenceId;
    private long categoryId;

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
    @Column(name = "category_id", nullable = false)
    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SentenceCategory that = (SentenceCategory) o;

        if (id != that.id) return false;
        if (sentenceId != that.sentenceId) return false;
        if (categoryId != that.categoryId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (sentenceId ^ (sentenceId >>> 32));
        result = 31 * result + (int) (categoryId ^ (categoryId >>> 32));
        return result;
    }
}
