package bean;

import javax.persistence.*;

/**
 * @ClassName UserFollow
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-08 12:29
 * Version 1.0
 */
@Entity
@Table(name = "user_follow", schema = "yinyousentence", catalog = "")
public class UserFollow {
    private long followerId;
    private long id;
    private long userId;

    @Basic
    @Column(name = "follower_id", nullable = false)
    public long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(long followerId) {
        this.followerId = followerId;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserFollow that = (UserFollow) o;

        if (followerId != that.followerId) return false;
        if (id != that.id) return false;
        if (userId != that.userId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (followerId ^ (followerId >>> 32));
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        return result;
    }
}
