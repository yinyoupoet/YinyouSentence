package pageEntity;

import auxiliary.CommentAuxiliary;
import auxiliary.ReplyAuxiliary;
import bean.CommentReply;
import bean.SentenceComment;
import bean.UserInfo;
import dao.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName CommentEntity
 * @Description 评论相关
 * @Author hasee
 * @Date 2018-07-09 15:02
 * Version 1.0
 */
public class CommentEntity {

    @Autowired
    private CommentDao commentDao;



    /**
    * 辅助Comment列表
    **/
    private List<CommentAuxiliary> commentAuxiliaries = new LinkedList<CommentAuxiliary>();


    /**
    * @author hasee
    * @Description 初始化方法，建造者模式，顺序不许变
    * @Date 15:30 2018-07-09
    * @Param [sentenceId]
    * @return void
    **/
    public void init(long sentenceId){
        if(commentAuxiliaries != null){
            commentAuxiliaries.clear();
        }else{
            commentAuxiliaries = new LinkedList<CommentAuxiliary>();
        }

        // 初始化评论
        initComment(sentenceId);

        // 初始化发布者
        initPublisherInfo();

        // 初始化回复
        initReply(sentenceId);


    }

    /**
    * @author hasee
    * @Description 初始化评论
    * @Date 15:28 2018-07-09
    * @Param [sentenceId] 句子ID
    * @return void
    **/
    public void initComment(long sentenceId){
        List<SentenceComment> sentenceComments = commentDao.getSentenceCommentsBySentenceId(sentenceId);
        for(SentenceComment comment : sentenceComments){
            CommentAuxiliary commentAuxiliary = new CommentAuxiliary();
            commentAuxiliary.setSentenceComment(comment);
            commentAuxiliaries.add(commentAuxiliary);
        }
    }


    /**
    * @author hasee
    * @Description 初始化评论发布者的信息
    * @Date 16:40 2018-07-09
    * @Param []
    * @return void
    **/
    public void initPublisherInfo(){
        for(CommentAuxiliary commentAuxiliary : commentAuxiliaries){
            commentAuxiliary.setUserInfo(commentDao.getUserInfoById(commentAuxiliary.getSentenceComment().getUserId()));
        }
    }

    /**
    * @author hasee
    * @Description 初始化回复
    * @Date 15:39 2018-07-09
    * @Param [sentenceId]
    * @return void
    **/
    public void initReply(long sentenceId){
        for(CommentAuxiliary commentAuxiliary: commentAuxiliaries){
            List<CommentReply> commentReplys = commentDao.getCommentReplyByCommentId(commentAuxiliary.getSentenceComment().getId());

            List<ReplyAuxiliary> replyAuxiliaries = new LinkedList<ReplyAuxiliary>();
            for(CommentReply commentReply : commentReplys){
                UserInfo userInfo = commentDao.getUserInfoById(commentReply.getReplyWriterId());
                UserInfo userInfo1 = commentDao.getUserInfoById(commentReply.getReplyObjectUserId());
                ReplyAuxiliary replyAuxiliary = new ReplyAuxiliary();
                replyAuxiliary.setCommentReply(commentReply);
                replyAuxiliary.setPublisherInfo(userInfo);
                replyAuxiliary.setUserBeRepliedInfo(userInfo1);
                replyAuxiliaries.add(replyAuxiliary);
            }
            commentAuxiliary.setReplyAuxiliaries(replyAuxiliaries);
        }
    }

    public CommentDao getCommentDao() {
        return commentDao;
    }

    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public List<CommentAuxiliary> getCommentAuxiliaries() {
        return commentAuxiliaries;
    }

    public void setCommentAuxiliaries(List<CommentAuxiliary> commentAuxiliaries) {
        this.commentAuxiliaries = commentAuxiliaries;
    }
}
