package auxiliary;

import bean.CommentReply;
import bean.SentenceComment;
import bean.UserInfo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName CommentAuxiliary
 * @Description 一条评论及对应的回复
 * @Author hasee
 * @Date 2018-07-09 15:14
 * Version 1.0
 */
public class CommentAuxiliary {
    private UserInfo userInfo;

    private SentenceComment sentenceComment;

    private List<ReplyAuxiliary> replyAuxiliaries = new LinkedList<ReplyAuxiliary>();


    public SentenceComment getSentenceComment() {
        return sentenceComment;
    }

    public void setSentenceComment(SentenceComment sentenceComment) {
        this.sentenceComment = sentenceComment;
    }



    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }


    public List<ReplyAuxiliary> getReplyAuxiliaries() {
        return replyAuxiliaries;
    }

    public void setReplyAuxiliaries(List<ReplyAuxiliary> replyAuxiliaries) {
        this.replyAuxiliaries = replyAuxiliaries;
    }
}
