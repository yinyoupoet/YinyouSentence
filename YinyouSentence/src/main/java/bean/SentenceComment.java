package bean;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @ClassName SentenceComment
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-07 10:40
 * Version 1.0
 */
@Entity
@Table(name = "sentence_comment", schema = "yinyousentence", catalog = "")
public class SentenceComment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String content;
    private long sentenceId;
    private long userId;
    private Timestamp commentTime;

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
    @Column(name = "sentence_id", nullable = false)
    public long getSentenceId() {
        return sentenceId;
    }

    public void setSentenceId(long sentenceId) {
        this.sentenceId = sentenceId;
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
    @Column(name = "comment_time", nullable = false)
    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SentenceComment that = (SentenceComment) o;

        if (id != that.id) return false;
        if (sentenceId != that.sentenceId) return false;
        if (userId != that.userId) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (commentTime != null ? !commentTime.equals(that.commentTime) : that.commentTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (int) (sentenceId ^ (sentenceId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (commentTime != null ? commentTime.hashCode() : 0);
        return result;
    }
}
