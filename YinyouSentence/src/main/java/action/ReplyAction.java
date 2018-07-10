package action;

import bean.CommentReply;
import bean.SentenceComment;
import bean.UserInfo;
import com.opensymphony.xwork2.ActionSupport;
import dao.CommentDao;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Map;

/**
 * @ClassName ReplyAction
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-10 10:00
 * Version 1.0
 */
public class ReplyAction extends ActionSupport implements SessionAware{

    @Autowired
    private CommentDao commentDao;
    private String type;
    private String commentId;
    private String rpObjId;
    private String content;
    private String sentenceId;
    private Map session;

    @Override
    public String execute() throws Exception {
        // 数据不合法，直接返回
        if(! dataValidationCheck()){
            return INPUT;
        }
        CommentReply commentReply = new CommentReply();
        // 回复评论
        if("0".equals(type)){
            replyComment(commentReply);
        }else if("1".equals(type)){
            // 回复回复
            replyReply(commentReply);
        }
        SentenceComment sentenceComment = commentDao.getCommentInfoById(commentReply.getCommentId());
        sentenceId = sentenceComment.getSentenceId() + "";
        return SUCCESS;
    }


    /**
    * @author hasee
    * @Description 回复评论
    * @Date 16:01 2018-07-10
    * @Param [commentReply]
    * @return void
    **/
    public void replyComment(CommentReply commentReply){
        commentReply.setContent(content);
        Long cmtId = Long.valueOf(commentId);
        commentReply.setCommentId(cmtId);
        commentReply.setReplyType((byte) 0);
        SentenceComment sc = commentDao.getCommentInfoById(cmtId);
        commentReply.setReplyCommentUserId(sc.getUserId());
        commentReply.setReplyObjectUserId(sc.getUserId());
        commentReply.setReplyObjectId(cmtId);
        UserInfo userInfo = (UserInfo) session.get("userInfo");
        commentReply.setReplyWriterId(userInfo.getId());
        commentReply.setReplyTime(new Timestamp(System.currentTimeMillis()));
        commentDao.publishReply(commentReply);
    }

    /**
    * @author hasee
    * @Description 回复回复
    * @Date 16:00 2018-07-10
    * @Param [commentReply]
    * @return void
    **/
    public void replyReply(CommentReply commentReply){
        commentReply.setContent(content);
        long rpOId = Long.valueOf(rpObjId);
        CommentReply cr = commentDao.getCommentReplyInfoById(rpOId);
        commentReply.setCommentId(cr.getCommentId());
        commentReply.setReplyType((byte) 1);
        commentReply.setReplyCommentUserId(cr.getReplyCommentUserId());
        commentReply.setReplyObjectUserId(cr.getReplyWriterId());
        commentReply.setReplyObjectId(cr.getId());
        UserInfo userInfo = (UserInfo) session.get("userInfo");
        commentReply.setReplyWriterId(userInfo.getId());
        commentReply.setReplyTime(new Timestamp(System.currentTimeMillis()));
        commentDao.publishReply(commentReply);
    }


    /**
    * @author hasee
    * @Description 数据合法性检验
    * @Date 15:47 2018-07-10
    * @Param []
    * @return java.lang.Boolean
    **/
    public Boolean dataValidationCheck(){
        UserInfo userInfo = (UserInfo) session.get("userInfo");
        if(userInfo == null){
            return false;
        }

        if(type == null){
            return false;
        }
        int sType = -1;
        try{
           sType  = Integer.valueOf(type);
        }catch (Exception e){
            return false;
        }
        if(sType != 0 && sType != 1){
            return false;
        }
        if(sType == 0){
            try{
                long cmdId = Long.valueOf(commentId);
                if(! commentDao.checkIfCommentExistById(cmdId)){
                    return false;
                }
            }catch (Exception e){
                return false;
            }
        }else{
            try{
                long rpOId = Long.valueOf(rpObjId);
                if(! commentDao.checkIfReplyExistById(rpOId)){
                    return false;
                }
            }catch (Exception e){
                return false;
            }
        }
        if(content == null || "".equals(content)){
            return false;
        }
        return true;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getRpObjId() {
        return rpObjId;
    }

    public void setRpObjId(String rpObjId) {
        this.rpObjId = rpObjId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CommentDao getCommentDao() {
        return commentDao;
    }

    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public String getSentenceId() {
        return sentenceId;
    }

    public void setSentenceId(String sentenceId) {
        this.sentenceId = sentenceId;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
