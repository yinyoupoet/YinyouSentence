package bean;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @ClassName Login
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-04 14:22
 * Version 1.0
 */
@Entity
public class Login {
    private String name;
    private long id;
    private String pwd;

    @Basic
    @Column(name = "name", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "pwd", nullable = false, length = 16)
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Login login = (Login) o;

        if (id != login.id) return false;
        if (name != null ? !name.equals(login.name) : login.name != null) return false;
        if (pwd != null ? !pwd.equals(login.pwd) : login.pwd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (pwd != null ? pwd.hashCode() : 0);
        return result;
    }
}
