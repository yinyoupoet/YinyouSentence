package bean;

import javax.persistence.*;
import java.sql.Date;

/**
 * @ClassName UserInfo
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-05 15:35
 * Version 1.0
 */
@Entity
@Table(name = "user_info", schema = "yinyousentence", catalog = "")
public class UserInfo {
    private long id;
    private String headPath;
    private String gender;
    private Date birth;
    private String motto;
    private String userName;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "head_path", nullable = false, length = 255)
    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    @Basic
    @Column(name = "gender", nullable = false, length = 1)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "birth", nullable = false)
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Basic
    @Column(name = "motto", nullable = true, length = 64)
    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    @Basic
    @Column(name = "user_name", nullable = false, length = 20)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfo userInfo = (UserInfo) o;

        if (id != userInfo.id) return false;
        if (headPath != null ? !headPath.equals(userInfo.headPath) : userInfo.headPath != null) return false;
        if (gender != null ? !gender.equals(userInfo.gender) : userInfo.gender != null) return false;
        if (birth != null ? !birth.equals(userInfo.birth) : userInfo.birth != null) return false;
        if (motto != null ? !motto.equals(userInfo.motto) : userInfo.motto != null) return false;
        if (userName != null ? !userName.equals(userInfo.userName) : userInfo.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (headPath != null ? headPath.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (birth != null ? birth.hashCode() : 0);
        result = 31 * result + (motto != null ? motto.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        return result;
    }
}
