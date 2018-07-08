package bean;

import javax.persistence.*;

/**
 * @ClassName GiantInfo
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-07 10:40
 * Version 1.0
 */
@Entity
@Table(name = "giant_info", schema = "yinyousentence", catalog = "")
public class GiantInfo {
    private long id;
    private String name;
    private String imgPath;
    private String introduction;
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
    @Column(name = "img_path", nullable = false, length = 255)
    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Basic
    @Column(name = "introduction", nullable = false, length = -1)
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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

        GiantInfo giantInfo = (GiantInfo) o;

        if (id != giantInfo.id) return false;
        if (loveNum != giantInfo.loveNum) return false;
        if (name != null ? !name.equals(giantInfo.name) : giantInfo.name != null) return false;
        if (imgPath != null ? !imgPath.equals(giantInfo.imgPath) : giantInfo.imgPath != null) return false;
        if (introduction != null ? !introduction.equals(giantInfo.introduction) : giantInfo.introduction != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (imgPath != null ? imgPath.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        result = 31 * result + (int) (loveNum ^ (loveNum >>> 32));
        return result;
    }
}
