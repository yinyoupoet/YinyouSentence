package dao;

import bean.CommentReply;
import bean.Sentence;
import bean.SentenceComment;
import bean.UserInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName CommentDao
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-09 15:17
 * Version 1.0
 */
public class CommentDao {

    @Autowired
    private SessionFactory sessionFactory;


    /**
    * @author hasee
    * @Description 用评论id获取评论（没管回复）
    * @Date 15:42 2018-07-09
    * @Param []
    * @return java.util.List<bean.SentenceComment>
    **/
    public List<SentenceComment> getSentenceCommentsBySentenceId(long sentenceId){
        Session session = sessionFactory.openSession();
        List<SentenceComment> sentenceComments = new ArrayList<SentenceComment>();
        String hql = "FROM SentenceComment where sentenceId = ? order by id desc ";
        Transaction tx = session.beginTransaction();
        sentenceComments = session.createQuery(hql).setParameter(0,sentenceId).list();
        tx.commit();
        return sentenceComments;
    }

    /**
    * @author hasee
    * @Description 根据评论ID获得回复列表
    * @Date 16:10 2018-07-09
    * @Param [commentId]
    * @return java.util.List<bean.CommentReply>
    **/
    public List<CommentReply> getCommentReplyByCommentId(long commentId){
        Session session = sessionFactory.openSession();
        List<CommentReply> commentReplies = new ArrayList<CommentReply>();
        String hql = "FROM CommentReply where commentId = ? order by id asc ";
        Transaction tx = session.beginTransaction();
        commentReplies = session.createQuery(hql).setParameter(0,commentId).list();
        tx.commit();
        return commentReplies;
    }


    /**
    * @author hasee
    * @Description 根据用户Id获取用户信息
    * @Date 16:41 2018-07-09
    * @Param [id]
    * @return bean.UserInfo
    **/
    public UserInfo getUserInfoById(long id){
        Session session = sessionFactory.openSession();
        UserInfo userInfo = null;
        String hql = "FROM UserInfo where id = ?";
        Transaction tx = session.beginTransaction();
        userInfo = (UserInfo) session.createQuery(hql).setParameter(0,id).uniqueResult();
        tx.commit();
        return userInfo;
    }

    /**
    * @author hasee
    * @Description 发布一条评论
    * @Date 16:17 2018-07-10
    * @Param [sentenceComment]
    * @return void
    **/
    public void publishComment(SentenceComment sentenceComment){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(sentenceComment);
        tx.commit();
    }

    // 根据评论Id判断评论是否存在
    public Boolean checkIfCommentExistById(long id){
        Session session = sessionFactory.openSession();
        long num = 0;
        String hql = "select count(*) FROM SentenceComment where id = ?";
        Transaction tx = session.beginTransaction();
        num = (Long) session.createQuery(hql).setParameter(0,id).uniqueResult();
        tx.commit();
        if(num > 0){
            return true;
        }
        return false;
    }

    // 根据回复id判断回复是否存在
    public Boolean checkIfReplyExistById(long id){
        Session session = sessionFactory.openSession();
        long num = 0;
        String hql = "select count(*) FROM CommentReply where id = ?";
        Transaction tx = session.beginTransaction();
        num = (Long) session.createQuery(hql).setParameter(0,id).uniqueResult();
        tx.commit();
        if(num > 0){
            return true;
        }
        return false;
    }


    /**
    * @author hasee
    * @Description 根据
    * @Date 16:17 2018-07-10
    * @Param [id]
    * @return bean.SentenceComment
    **/
    public SentenceComment getCommentInfoById(long id){
        Session session = sessionFactory.openSession();
        SentenceComment sentenceComment = null;
        String hql = "FROM SentenceComment where id = ?";
        Transaction tx = session.beginTransaction();
        sentenceComment = (SentenceComment) session.createQuery(hql).setParameter(0,id).uniqueResult();
        tx.commit();
        return sentenceComment;
    }


    /**
    * @author hasee
    * @Description 发布一条回复
    * @Date 16:41 2018-07-10
    * @Param [commentReply]
    * @return void
    **/
    public void publishReply(CommentReply commentReply){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(commentReply);
        tx.commit();
    }

    public CommentReply getCommentReplyInfoById(long id){
        Session session = sessionFactory.openSession();
        CommentReply commentReply = null;
        String hql = "FROM CommentReply where id = ?";
        Transaction tx = session.beginTransaction();
        commentReply = (CommentReply) session.createQuery(hql).setParameter(0,id).uniqueResult();
        tx.commit();
        return commentReply;
    }


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
