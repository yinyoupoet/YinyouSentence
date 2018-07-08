package bean;

import javax.persistence.*;

/**
 * @ClassName CollectionSentence
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-08 9:28
 * Version 1.0
 */
@Entity
@Table(name = "collection_sentence", schema = "yinyousentence", catalog = "")
public class CollectionSentence {
    private long id;
    private long collectionId;
    private long sentenceId;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "collection_id", nullable = false)
    public long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(long collectionId) {
        this.collectionId = collectionId;
    }

    @Basic
    @Column(name = "sentence_id", nullable = false)
    public long getSentenceId() {
        return sentenceId;
    }

    public void setSentenceId(long sentenceId) {
        this.sentenceId = sentenceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CollectionSentence that = (CollectionSentence) o;

        if (id != that.id) return false;
        if (collectionId != that.collectionId) return false;
        if (sentenceId != that.sentenceId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (collectionId ^ (collectionId >>> 32));
        result = 31 * result + (int) (sentenceId ^ (sentenceId >>> 32));
        return result;
    }
}
