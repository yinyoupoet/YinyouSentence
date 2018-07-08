package dao;

import bean.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SentenceDao
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-08 15:55
 * Version 1.0
 */
public class SentenceDao {
    @Autowired
    private SessionFactory sessionFactory;

    // 根据句子Id返回句子信息
    public Sentence getSentenceById(long id){
        Session session = sessionFactory.openSession();
        String hql = "From Sentence where id = ?";
        Sentence sentence = null;
        Transaction tx = session.beginTransaction();
        sentence = (Sentence) session.createQuery(hql).setParameter(0,id).uniqueResult();
        tx.commit();
        return sentence;
    }

    // 根据作者ID获得其所有句子
    public List<Sentence> getSentencesByAuthorId(long id){
        Session session = sessionFactory.openSession();
        String hql = "From Sentence where authorId = ?  ORDER BY loveNum desc ";
        List<Sentence> sentences = new ArrayList<Sentence>();
        Transaction tx = session.beginTransaction();
        sentences =  session.createQuery(hql).setParameter(0,id).list();
        tx.commit();
        return sentences;
    }

    // 根据句子Id判断句子是否已经存在
    public boolean isSentenceExist(long id){
        Session session = sessionFactory.openSession();
        String hql = "select count(*) from Sentence where id = ?";
        long num = 0;
        Transaction tx = session.beginTransaction();
        num = (Long) session.createQuery(hql).setParameter(0,id).uniqueResult();
        tx.commit();
        if(num > 0){
            return true;
        }
        return false;
    }

    // 根据作者Id获得作者信息
    public GiantInfo getGiantInfoById(long id){
        Session session = sessionFactory.openSession();
        String hql = "From GiantInfo where id = ?";
        GiantInfo giantInfo = null;
        Transaction tx = session.beginTransaction();
        giantInfo = (GiantInfo) session.createQuery(hql).setParameter(0,id).uniqueResult();
        tx.commit();
        return giantInfo;
    }

    // 根据用户ID获取用户信息
    public UserInfo getUserInfoById(long id){
        Session session = sessionFactory.openSession();
        String hql = "From UserInfo where id = ?";
        UserInfo userInfo = null;
        Transaction tx = session.beginTransaction();
        userInfo = (UserInfo) session.createQuery(hql).setParameter(0,id).uniqueResult();
        tx.commit();
        return userInfo;
    }

    // 根据句子Id获取喜欢该句子的用户的id
    public List<Long> getLoveSentenceUserIdBySentenceId(long id){
        Session session = sessionFactory.openSession();
        String hql = "From SentenceLove where sentenceId = ?  ORDER BY id desc ";
        List<Long> userIds = new ArrayList<Long>();
        Transaction tx = session.beginTransaction();
        userIds =  session.createQuery(hql).setParameter(0,id).list();
        tx.commit();
        return userIds;
    }

    // 通过出处id获取出处信息
    public OriginInfo getOriginInfoById(long id){
        Session session = sessionFactory.openSession();
        String hql = "From OriginInfo where id = ?";
        OriginInfo originInfo = null;
        Transaction tx = session.beginTransaction();
        originInfo = (OriginInfo) session.createQuery(hql).setParameter(0,id).uniqueResult();
        tx.commit();
        return originInfo;
    }

    // 通过标签名获取标签对象
    public Tag getTagByName(String tagName){
        Session session = sessionFactory.openSession();
        String hql = "From Tag where name = ?";
        Tag tag = null;
        Transaction tx = session.beginTransaction();
        List<Tag> tags =  session.createQuery(hql).setParameter(0,tagName).list();
        tx.commit();
        if(tags != null && tags.size() > 0){
            tag = tags.get(0);
        }
        return tag;
    }


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
