package dao;

import bean.GiantInfo;
import bean.OriginInfo;
import bean.UserFollow;
import bean.UserInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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


    // 判断是否已关注了该用户
    public Boolean isFollowPeopleOrNot(long myId, long toFollowId){
        Session session = sessionFactory.openSession();
        String hql = "select count(*) FROM UserFollow WHERE userId = ? AND followerId = ?";
        long num = 0;
        Transaction tx = session.beginTransaction();
        num = (Long) session.createQuery(hql).setParameter(0,toFollowId).setParameter(1,myId).uniqueResult();
        tx.commit();
        if(num > 0){
            return true;
        }
        return false;
    }

    // 取消关注
    public void cancelFollow(long myId, long toFollowId){
        Session session = sessionFactory.openSession();
        String hql = "delete FROM UserFollow WHERE userId = ? AND followerId = ?";
        Transaction tx = session.beginTransaction();
        session.createQuery(hql).setParameter(0,toFollowId).setParameter(1,myId).executeUpdate();
        tx.commit();
    }

    // 进行关注
    public void toFollow(UserFollow userFollow){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(userFollow);
        tx.commit();
    }


    // 获得用户喜欢了几个句子
    public long getLoveSentenceNum(long userId){
        Session session = sessionFactory.openSession();
        String hql = "select count(*) from SentenceLove where userId = ?";
        long num = 0;
        Transaction tx = session.beginTransaction();
        num = (Long) session.createQuery(hql).setParameter(0,userId).uniqueResult();
        tx.commit();
        return num;
    }

    // 获得用户发布了几个句子
    public long getPublishSentenceNum(long userId){
        Session session = sessionFactory.openSession();
        String hql = "select count(*) from Sentence where publisherId = ?";
        long num = 0;
        Transaction tx = session.beginTransaction();
        num = (Long) session.createQuery(hql).setParameter(0,userId).uniqueResult();
        tx.commit();
        return num;
    }

    // 获得用户发布了几个原创句子
    public long getPublishOriginalSentenceNum(long userId){
        Session session = sessionFactory.openSession();
        String hql = "select count(*) from Sentence where publisherId = ? AND isOriginal = 1";
        long num = 0;
        Transaction tx = session.beginTransaction();
        num = (Long) session.createQuery(hql).setParameter(0,userId).uniqueResult();
        tx.commit();
        return num;
    }


    // 获得用户发布了几个句子集
    public long getPublishCollectionNum(long userId){
        Session session = sessionFactory.openSession();
        String hql = "select count(*) from SentenceCollection where publisherId = ?";
        long num = 0;
        Transaction tx = session.beginTransaction();
        num = (Long) session.createQuery(hql).setParameter(0,userId).uniqueResult();
        tx.commit();
        return num;
    }

    // 获得用户喜欢了几个句子集
    public long getLoveCollectionNum(long userId){
        Session session = sessionFactory.openSession();
        String hql = "select count(*) from CollectionLove where userId = ?";
        long num = 0;
        Transaction tx = session.beginTransaction();
        num = (Long) session.createQuery(hql).setParameter(0,userId).uniqueResult();
        tx.commit();
        return num;
    }

    // 根据用户名模糊查询用户信息
    public List<UserInfo> getUserInfoByuserName(String s){
        Session session = sessionFactory.openSession();
        String hql = "From UserInfo where userName like ?";
        List<UserInfo> userInfos = new ArrayList<UserInfo>();
        Transaction tx = session.beginTransaction();
        userInfos =  session.createQuery(hql).setParameter(0,"%" + s + "%").list();
        tx.commit();
        return userInfos;
    }

    // 根据句子id获取喜欢该句子的用户的id
    public List<Long> getUserIdsBySentenceId(long sentenceId, int beginIndex, int num){
        Session session = sessionFactory.openSession();
        String hql = "SELECT userId from SentenceLove where sentenceId = ?";
        List<Long> userIds = new ArrayList<Long>();
        Transaction tx = session.beginTransaction();
        userIds =  session.createQuery(hql).setFirstResult(beginIndex).setParameter(0, sentenceId).setMaxResults(num).list();
        tx.commit();
        return userIds;
    }

    // 根据名家id获取喜欢该名家的用户的id
    public List<Long> getUserIdsByGiantId(long giantId, int beginIndex, int num){
        Session session = sessionFactory.openSession();
        String hql = "SELECT userId from GiantLove where giantId = ?";
        List<Long> userIds = new ArrayList<Long>();
        Transaction tx = session.beginTransaction();
        userIds =  session.createQuery(hql).setFirstResult(beginIndex).setParameter(0, giantId).setMaxResults(num).list();
        tx.commit();
        return userIds;
    }

    // 根据出处id获取喜欢该出处的用户的id
    public List<Long> getUserIdsByOriginId(long originId, int beginIndex, int num){
        Session session = sessionFactory.openSession();
        String hql = "SELECT userId from OriginLove where originId = ?";
        List<Long> userIds = new ArrayList<Long>();
        Transaction tx = session.beginTransaction();
        userIds =  session.createQuery(hql).setFirstResult(beginIndex).setParameter(0, originId).setMaxResults(num).list();
        tx.commit();
        return userIds;
    }

    // 根据用户id获取该用户关注的用户的id
    public List<Long> getUserIdsByFollowerId(long fanId, int beginIndex, int num){
        Session session = sessionFactory.openSession();
        String hql = "SELECT userId from UserFollow where followerId = ?";
        List<Long> userIds = new ArrayList<Long>();
        Transaction tx = session.beginTransaction();
        userIds =  session.createQuery(hql).setFirstResult(beginIndex).setParameter(0, fanId).setMaxResults(num).list();
        tx.commit();
        return userIds;
    }

    // 根据用户id获取该用户的粉丝的id
    public List<Long> getUserIdsByFollowingId(long userId, int beginIndex, int num){
        Session session = sessionFactory.openSession();
        String hql = "SELECT followerId from UserFollow where userId = ?";
        List<Long> userIds = new ArrayList<Long>();
        Transaction tx = session.beginTransaction();
        userIds =  session.createQuery(hql).setFirstResult(beginIndex).setParameter(0, userId).setMaxResults(num).list();
        tx.commit();
        return userIds;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
