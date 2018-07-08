package bean;

import javax.persistence.*;

/**
 * @ClassName OriginLove
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-07 10:40
 * Version 1.0
 */
@Entity
@Table(name = "origin_love", schema = "yinyousentence", catalog = "")
public class OriginLove {
    private long id;
    private long userId;
    private long originId;

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
    @Column(name = "origin_id", nullable = false)
    public long getOriginId() {
        return originId;
    }

    public void setOriginId(long originId) {
        this.originId = originId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OriginLove that = (OriginLove) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (originId != that.originId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (originId ^ (originId >>> 32));
        return result;
    }
}
