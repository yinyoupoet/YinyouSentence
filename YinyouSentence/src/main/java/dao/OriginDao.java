package dao;

import bean.GiantLove;
import bean.OriginInfo;
import bean.OriginLove;
import bean.Sentence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OriginDao
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-13 11:17
 * Version 1.0
 */
public class OriginDao {
    @Autowired
    private SessionFactory sessionFactory;


    // 根据出处Id判断出处是否存在
    public Boolean checkIfOriginExist(long originId){
        Session session = sessionFactory.openSession();
        String hql = "select count(*) FROM OriginInfo where id = ?";
        long count = 0;
        Transaction tx = session.beginTransaction();
        count = (Long) session.createQuery(hql).setParameter(0,originId).uniqueResult();
        tx.commit();
        if(count >= 1){
            return true;
        }
        return false;
    }


    // 根据出处Id获取其详细信息
    public OriginInfo getOriginInfoById(long originId){
        Session session = sessionFactory.openSession();
        String hql = "From OriginInfo where id = ?";
        OriginInfo originInfo  = null;
        Transaction tx = session.beginTransaction();
        originInfo = (OriginInfo) session.createQuery(hql).setParameter(0,originId).uniqueResult();
        tx.commit();
        return originInfo;
    }

    // 判断用户是否喜欢出处
    public Boolean checkIfUserLoveOrigin(long userId, long originId){
        Session session = sessionFactory.openSession();
        String hql = "select count(*) FROM OriginLove where userId = ? and originId = ?";
        long num = 0;
        Transaction tx = session.beginTransaction();
        num = (Long) session.createQuery(hql).setParameter(0,userId).setParameter(1,originId).uniqueResult();
        tx.commit();
        if(num > 0){
            return true;
        }
        return false;
    }

    // 让用户喜欢出处
    public void addUserLoveOrigin(OriginLove originLove){
        Session session = sessionFactory.openSession();
        String hql = "update from OriginInfo set loveNum = loveNum + 1 where id = ?";
        Transaction tx = session.beginTransaction();
        session.save(originLove);
        session.createQuery(hql).setParameter(0,originLove.getOriginId()).executeUpdate();
        tx.commit();
    }

    // 取消用户对于出处的喜欢
    public void cancelUserLoveOrigin(long userId, long originId){
        Session session = sessionFactory.openSession();
        String hql = "delete FROM OriginLove where userId = ? and originId = ?";
        String hql_2 = "update from OriginInfo set loveNum = loveNum - 1 where id = ?";
        long count = 0;
        Transaction tx = session.beginTransaction();
        session.createQuery(hql).setParameter(0,userId).setParameter(1,originId).executeUpdate();
        session.createQuery(hql_2).setParameter(0,originId).executeUpdate();
        tx.commit();
    }

    // 获取所有喜欢某出处的用户的 出处喜欢列表
    public List<OriginLove> getLoveOriginUsers(long originId){
        Session session = sessionFactory.openSession();
        String hql = "From OriginLove where originId = ?";
        List<OriginLove> originLoves = new ArrayList<OriginLove>();
        Transaction tx = session.beginTransaction();
        originLoves =  session.createQuery(hql).setParameter(0,originId).list();
        tx.commit();
        return originLoves;
    }

    // 根据出处Id，返回出处下所有句子
    public List<Sentence> getSentencesInTheOrigin(long originId){
        Session session = sessionFactory.openSession();
        String hql = "From Sentence where originId = ?";
        List<Sentence> sentences = new ArrayList<Sentence>();
        Transaction tx = session.beginTransaction();
        sentences =  session.createQuery(hql).setParameter(0,originId).list();
        tx.commit();
        return sentences;
    }


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
