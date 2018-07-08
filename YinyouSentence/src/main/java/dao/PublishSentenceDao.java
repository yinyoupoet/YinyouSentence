package dao;

import bean.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionCallback;

/**
 * @ClassName PublishSentenceDao
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-07 23:55
 * Version 1.0
 */
public class PublishSentenceDao {
    @Autowired
    private SessionFactory sessionFactory;


    // 判断作者是否存在数据库
    public Boolean isAuthorExist(String authorName){
        Session session = sessionFactory.openSession();
        String hql = "select count(id) FROM GiantInfo giantInfo where name = ?";
        long count = 0;
        Transaction tx = session.beginTransaction();
        count = (Long) session.createQuery(hql).setParameter(0,authorName).uniqueResult();
        tx.commit();
        if(count >= 1){
            return true;
        }
        return false;
    }

    // 判断出处是否存在数据库
    public Boolean isOriginExist(String originName){
        Session session = sessionFactory.openSession();
        String hql = "select count(id) FROM OriginInfo originInfo where name = ?";
        long count = 0;
        Transaction tx = session.beginTransaction();
        count = (Long) session.createQuery(hql).setParameter(0,originName).uniqueResult();
        tx.commit();
        if(count >= 1){
            return true;
        }
        return false;
    }


    // 根据作者名获得作者ID
    public long getAuthorId(String authorName){
        Session session = sessionFactory.openSession();
        String hql = "select id from GiantInfo giantInfo where name = ?";
        long id = 0;
        Transaction tx = session.beginTransaction();
        id = (Long) session.createQuery(hql).setParameter(0,authorName).uniqueResult();
        tx.commit();
        return id;
    }

    // 根据出处名获得出处ID
    public long getOriginId(String originName){
        Session session = sessionFactory.openSession();
        String hql = "select id from OriginInfo originInfo where name = ?";
        long id = 0;
        Transaction tx = session.beginTransaction();
        id = (Long) session.createQuery(hql).setParameter(0,originName).uniqueResult();
        tx.commit();
        return id;
    }

    // 新建一个作者
    public void addGiant(GiantInfo giantInfo){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(giantInfo);
        tx.commit();
    }

    // 新建一个出处
    public void addOrigin(OriginInfo originInfo){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(originInfo);
        tx.commit();
    }

    // 新建一条句子
    public void addSentence(Sentence sentence){

        Session session = sessionFactory.openSession();


        Transaction tx = session.beginTransaction();
        session.save(sentence);
        tx.commit();
    }

    // 判断标签是否存在数据库
    public Boolean isTagExist(String tagName){
        Session session = sessionFactory.openSession();
        String hql = "select count(id) FROM Tag tag where name = ?";
        long count = 0;
        Transaction tx = session.beginTransaction();
        count = (Long) session.createQuery(hql).setParameter(0,tagName).uniqueResult();
        tx.commit();
        if(count >= 1){
            return true;
        }
        return false;
    }

    // 根据标签名获得标签ID
    public long getTagId(String tagName){
        Session session = sessionFactory.openSession();
        String hql = "select id from Tag tag where name = ?";
        long id = 0;
        Transaction tx = session.beginTransaction();
        id = (Long) session.createQuery(hql).setParameter(0,tagName).uniqueResult();
        tx.commit();
        return id;
    }

    // 添加Tag
    public void addTag(Tag tag){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(tag);
        tx.commit();
    }

    // 让这个Tag引用次数加一
    public void addTagQuote(long tagId){
        Session session = sessionFactory.openSession();
        String hql = "update Tag set quoteNum = quoteNum + 1 where id = ?";
        Transaction tx = session.beginTransaction();
        session.createQuery(hql).setParameter(0,tagId).executeUpdate();
        tx.commit();
    }

    // 插入标签引用表
    public void setTagQuote(TagQuote tagQuote){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(tagQuote);
        tx.commit();
    }

    // 添加句子到句子集
    public void putSentenceIntoCollection(CollectionSentence cs){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(cs);
        tx.commit();
    }

    // 添加句子到对应分类下
    public void putSentenceIntoCategory(SentenceCategory sc){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(sc);
        tx.commit();
    }




    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
