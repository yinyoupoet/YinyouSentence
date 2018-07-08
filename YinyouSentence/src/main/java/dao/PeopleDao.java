package dao;

import bean.GiantInfo;
import bean.OriginInfo;
import bean.UserInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName PeopleDao
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-08 11:42
 * Version 1.0
 */
public class PeopleDao {
    @Autowired
    private SessionFactory sessionFactory;

    // 判断用户ID是否存在
    public boolean isUserIdExist(long id){
        Session session = sessionFactory.openSession();
        String hql = "select count(*) from UserInfo userInfo where id = ?";
        long num = 0;
        Transaction tx = session.beginTransaction();
        num = (Long) session.createQuery(hql).setParameter(0,id).uniqueResult();
        tx.commit();
        if(num > 0){
            return true;
        }
        return false;
    }

    // 根据用户ID返回UserInfo
    public UserInfo getUserInfoById(long id){
        Session session = sessionFactory.openSession();
        String hql = "FROM UserInfo userInfo WHERE id = ?";
        UserInfo userInfo = null;
        Transaction tx = session.beginTransaction();
        userInfo = (UserInfo) session.createQuery(hql).setParameter(0,id).uniqueResult();
        tx.commit();
        return userInfo;
    }

    // 根据用户Id获得用户关注了几个人
    public long getFollowingNumById(long id){
        Session session = sessionFactory.openSession();
        String hql = "select count(*) from UserFollow userFollow where followerId = ?";
        long num = 0;
        Transaction tx = session.beginTransaction();
        num = (Long) session.createQuery(hql).setParameter(0,id).uniqueResult();
        tx.commit();
        return num;
    }

    // 根据用户Id获得用户被几个人关注
    public long getFollowerNumById(long id){
        Session session = sessionFactory.openSession();
        String hql = "select count(*) from UserFollow userFollow where userId = ?";
        long num = 0;
        Transaction tx = session.beginTransaction();
        num = (Long) session.createQuery(hql).setParameter(0,id).uniqueResult();
        tx.commit();
        return num;
    }

    // 根据用户Id获取关注的名家的Id
    public List<Long> getFollowingGiantIds(long id){
        Session session = sessionFactory.openSession();
        List<Long> giantIds = new LinkedList<Long>();
        String hql = "select giantId FROM GiantLove where userId = ? ORDER BY giantId desc ";
        giantIds = session.createQuery(hql).setParameter(0,id).list();
        return giantIds;
    }

    // 根据名家Id获取其信息
    public GiantInfo getGiantInfoById(long giantId){
        Session session = sessionFactory.openSession();
        String hql = "FROM GiantInfo giantInfo WHERE id = ?";
        GiantInfo giantInfo = null;
        Transaction tx = session.beginTransaction();
        giantInfo = (GiantInfo) session.createQuery(hql).setParameter(0,giantId).uniqueResult();
        tx.commit();
        return giantInfo;
    }

    // 根据用户Id获取关注的出处的Id
    public List<Long> getFollowingOriginIds(long id){
        Session session = sessionFactory.openSession();
        List<Long> originIds = new LinkedList<Long>();
        String hql = "select originId FROM OriginLove where userId = ? ORDER BY originId desc ";
        originIds = session.createQuery(hql).setParameter(0,id).list();
        return originIds;
    }

    // 根据出处Id获取其信息
    public OriginInfo getOriginInfoById(long originId){
        Session session = sessionFactory.openSession();
        String hql = "FROM OriginInfo giantInfo WHERE id = ?";
        OriginInfo originInfo = null;
        Transaction tx = session.beginTransaction();
        originInfo = (OriginInfo) session.createQuery(hql).setParameter(0,originId).uniqueResult();
        tx.commit();
        return originInfo;
    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
