package bean;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @ClassName UserMessage
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-07 10:40
 * Version 1.0
 */
@Entity
@Table(name = "user_message", schema = "yinyousentence", catalog = "")
public class UserMessage {
    private long id;
    private String content;
    private Timestamp sendTime;
    private long sendUserId;
    private long isRead;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "content", nullable = false, length = 500)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "send_time", nullable = false)
    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    @Basic
    @Column(name = "send_user_id", nullable = false)
    public long getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(long sendUserId) {
        this.sendUserId = sendUserId;
    }

    @Basic
    @Column(name = "is_read", nullable = false)
    public long getIsRead() {
        return isRead;
    }

    public void setIsRead(long isRead) {
        this.isRead = isRead;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserMessage that = (UserMessage) o;

        if (id != that.id) return false;
        if (sendUserId != that.sendUserId) return false;
        if (isRead != that.isRead) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (sendTime != null ? !sendTime.equals(that.sendTime) : that.sendTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (sendTime != null ? sendTime.hashCode() : 0);
        result = 31 * result + (int) (sendUserId ^ (sendUserId >>> 32));
        result = 31 * result + (int) (isRead ^ (isRead >>> 32));
        return result;
    }
}
