package bean;

import javax.persistence.*;

/**
 * @ClassName CollectionLove
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-12 11:14
 * Version 1.0
 */
@Entity
@Table(name = "collection_love", schema = "yinyousentence", catalog = "")
public class CollectionLove {
    private long id;
    private long userId;
    private long collectionId;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "collection_id", nullable = false)
    public long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(long collectionId) {
        this.collectionId = collectionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CollectionLove that = (CollectionLove) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (collectionId != that.collectionId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (collectionId ^ (collectionId >>> 32));
        return result;
    }
}
