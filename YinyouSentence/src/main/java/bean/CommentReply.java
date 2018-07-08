package bean;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @ClassName CommentReply
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-07 10:40
 * Version 1.0
 */
@Entity
@Table(name = "comment_reply", schema = "yinyousentence", catalog = "")
public class CommentReply {
    private long id;
    private long commentId;
    private byte replyType;
    private long replyCommentUserId;
    private long replyObjectUserId;
    private long replyObjectId;
    private long replyWriterId;
    private Timestamp replyTime;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "comment_id", nullable = false)
    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    @Basic
    @Column(name = "reply_type", nullable = false)
    public byte getReplyType() {
        return replyType;
    }

    public void setReplyType(byte replyType) {
        this.replyType = replyType;
    }

    @Basic
    @Column(name = "reply_comment_user_id", nullable = false)
    public long getReplyCommentUserId() {
        return replyCommentUserId;
    }

    public void setReplyCommentUserId(long replyCommentUserId) {
        this.replyCommentUserId = replyCommentUserId;
    }

    @Basic
    @Column(name = "reply_object_user_id", nullable = false)
    public long getReplyObjectUserId() {
        return replyObjectUserId;
    }

    public void setReplyObjectUserId(long replyObjectUserId) {
        this.replyObjectUserId = replyObjectUserId;
    }

    @Basic
    @Column(name = "reply_object_id", nullable = false)
    public long getReplyObjectId() {
        return replyObjectId;
    }

    public void setReplyObjectId(long replyObjectId) {
        this.replyObjectId = replyObjectId;
    }

    @Basic
    @Column(name = "reply_writer_id", nullable = false)
    public long getReplyWriterId() {
        return replyWriterId;
    }

    public void setReplyWriterId(long replyWriterId) {
        this.replyWriterId = replyWriterId;
    }

    @Basic
    @Column(name = "reply_time", nullable = false)
    public Timestamp getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Timestamp replyTime) {
        this.replyTime = replyTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentReply that = (CommentReply) o;

        if (id != that.id) return false;
        if (commentId != that.commentId) return false;
        if (replyType != that.replyType) return false;
        if (replyCommentUserId != that.replyCommentUserId) return false;
        if (replyObjectUserId != that.replyObjectUserId) return false;
        if (replyObjectId != that.replyObjectId) return false;
        if (replyWriterId != that.replyWriterId) return false;
        if (replyTime != null ? !replyTime.equals(that.replyTime) : that.replyTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (commentId ^ (commentId >>> 32));
        result = 31 * result + (int) replyType;
        result = 31 * result + (int) (replyCommentUserId ^ (replyCommentUserId >>> 32));
        result = 31 * result + (int) (replyObjectUserId ^ (replyObjectUserId >>> 32));
        result = 31 * result + (int) (replyObjectId ^ (replyObjectId >>> 32));
        result = 31 * result + (int) (replyWriterId ^ (replyWriterId >>> 32));
        result = 31 * result + (replyTime != null ? replyTime.hashCode() : 0);
        return result;
    }
}
