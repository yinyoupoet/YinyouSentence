package bean;

import javax.persistence.*;

/**
 * @ClassName GiantLove
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-07 10:40
 * Version 1.0
 */
@Entity
@Table(name = "giant_love", schema = "yinyousentence", catalog = "")
public class GiantLove {
    private long id;
    private long userId;
    private long giantId;

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
    @Column(name = "giant_id", nullable = false)
    public long getGiantId() {
        return giantId;
    }

    public void setGiantId(long giantId) {
        this.giantId = giantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GiantLove giantLove = (GiantLove) o;

        if (id != giantLove.id) return false;
        if (userId != giantLove.userId) return false;
        if (giantId != giantLove.giantId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (giantId ^ (giantId >>> 32));
        return result;
    }
}
