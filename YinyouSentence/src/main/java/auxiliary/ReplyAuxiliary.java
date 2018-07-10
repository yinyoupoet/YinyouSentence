package auxiliary;

import bean.CommentReply;
import bean.UserInfo;

/**
 * @ClassName ReplyAuxiliary
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-09 16:36
 * Version 1.0
 */
public class ReplyAuxiliary {
    private CommentReply commentReply;
    private UserInfo publisherInfo;
    private UserInfo userBeRepliedInfo;


    public CommentReply getCommentReply() {
        return commentReply;
    }

    public void setCommentReply(CommentReply commentReply) {
        this.commentReply = commentReply;
    }


    public UserInfo getPublisherInfo() {
        return publisherInfo;
    }

    public void setPublisherInfo(UserInfo publisherInfo) {
        this.publisherInfo = publisherInfo;
    }

    public UserInfo getUserBeRepliedInfo() {
        return userBeRepliedInfo;
    }

    public void setUserBeRepliedInfo(UserInfo userBeRepliedInfo) {
        this.userBeRepliedInfo = userBeRepliedInfo;
    }
}
