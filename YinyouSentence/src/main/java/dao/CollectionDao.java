package dao;

import bean.CollectionLove;
import bean.CollectionSentence;
import bean.GiantInfo;
import bean.SentenceCollection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName CollectionDao
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-10 22:41
 * Version 1.0
 */
public class CollectionDao {
    @Autowired
    private SessionFactory sessionFactory;


    /**
    * @author hasee
    * @Description 根据用户名查询其所有句子集
    * @Date 22:55 2018-07-10
    * @Param [id]
    * @return java.util.List<bean.SentenceCollection>
    **/
    public List<SentenceCollection> getSentenceCollectionsByUserId(long id){
        Session session = sessionFactory.openSession();
        String hql = "FROM SentenceCollection where publisherId = ?";
        List<SentenceCollection> sentenceCollections = new ArrayList<SentenceCollection>();
        Transaction tx = session.beginTransaction();
        sentenceCollections = session.createQuery(hql).setParameter(0,id).list();
        tx.commit();
        return sentenceCollections;
    }


    /**
    * @author hasee
    * @Description 判断一个句子在一个句子集中是否已被收藏
    * @Date 23:20 2018-07-10
    * @Param [sentenceId, collectionId]
    * @return java.lang.Boolean
    **/
    public Boolean isSentenceCollectedInCollection(long sentenceId, long collectionId){
        Session session = sessionFactory.openSession();
        String hql = "select count(*) FROM CollectionSentence where sentenceId = ? AND collectionId = ?";
        long num = 0;
        Transaction tx = session.beginTransaction();
        num = (Long) session.createQuery(hql).setParameter(0,sentenceId).setParameter(1,collectionId).uniqueResult();
        tx.commit();
        if(num > 0){
            return true;
        }
        return false;
    }

    /**
    * @author hasee
    * @Description 获取 句子-句子集 收藏表中，对应记录的id值，如果没有记录，那么返回0
    * @Date 23:49 2018-07-10
    * @Param [sentenceId, collectionId]
    * @return long
    **/
    public long getCollectionSentenceId(long sentenceId,long collectionId){
        Session session = sessionFactory.openSession();
        String hql = "FROM CollectionSentence where sentenceId = ? AND collectionId = ?";
        CollectionSentence collectionSentence = null;
        Transaction tx = session.beginTransaction();
        collectionSentence = (CollectionSentence) session.createQuery(hql).setParameter(0,sentenceId).setParameter(1,collectionId).uniqueResult();
        tx.commit();
        if(collectionSentence == null){
            return 0;
        }else{
            return collectionSentence.getId();
        }
    }


    /**
    * @author hasee
    * @Description 将句子从句子集中移出来
    * @Date 23:53 2018-07-10
    * @Param [csId, collectionId]
    * @return void
    **/
    public void removeSentenceFromCollection(long csId,long collectionId){
        Session session = sessionFactory.openSession();
        String hql = "delete FROM CollectionSentence where id = ?";
        String hql2 = "update SentenceCollection set sentenceNum = sentenceNum - 1 where id = ?";
        Transaction tx = session.beginTransaction();
        session.createQuery(hql).setParameter(0,csId).executeUpdate();
        session.createQuery(hql2).setParameter(0,collectionId).executeUpdate();
        tx.commit();
    }


    /**
    * @author hasee
    * @Description 将一个句子加入一个句子集
    * @Date 0:00 2018-07-11
    * @Param [cs]
    * @return void
    **/
    public void addSentenceIntoCollection(CollectionSentence cs){
        Session session = sessionFactory.openSession();
        String hql = "update SentenceCollection set sentenceNum = sentenceNum + 1 where id = ?";
        Transaction tx = session.beginTransaction();
        session.save(cs);
        session.createQuery(hql).setParameter(0,cs.getCollectionId()).executeUpdate();
        tx.commit();
    }


    /**
    * @author hasee
    * @Description 判断一个句子集Id是否属于这个用户
    * @Date 0:16 2018-07-11
    * @Param [userId, collectionId]
    * @return java.lang.Boolean
    **/
    public Boolean checkCollectionBelongToUser(long userId, long collectionId){
        Session session = sessionFactory.openSession();
        String hql = "select count(*) FROM SentenceCollection where publisherId = ? AND id = ?";
        long num = 0;
        Transaction tx = session.beginTransaction();
        num = (Long) session.createQuery(hql).setParameter(0,userId).setParameter(1,collectionId).uniqueResult();
        tx.commit();
        if(num > 0){
            return true;
        }
        return false;
    }

    // 根据句子集Id获取句子集包含的句子的数量
    public long getSentenceNumInCollection(long collectionId){
        Session session = sessionFactory.openSession();
        String hql = "select count(*) FROM CollectionSentence where collectionId = ?";
        long num = 0;
        Transaction tx = session.beginTransaction();
        num = (Long) session.createQuery(hql).setParameter(0,collectionId).uniqueResult();
        tx.commit();
        return num;
    }


    /**
    * @author hasee
    * @Description 判断一个用户是否已经有一个同名句子集，即句子集可以重名，但是同一个用户的所有句子集不行
    * @Date 9:19 2018-07-11
    * @Param [userId, collectionName]
    * @return java.lang.Boolean
    **/
    public Boolean isColletionNameExistForTheUser(long userId, String collectionName){
        Session session = sessionFactory.openSession();
        String hql = "select count(*) FROM SentenceCollection where publisherId = ? AND name = ?";
        long num = 0;
        Transaction tx = session.beginTransaction();
        num = (Long) session.createQuery(hql).setParameter(0,userId).setParameter(1,collectionName).uniqueResult();
        tx.commit();
        if(num > 0){
            return true;
        }
        return false;
    }


    /**
    * @author hasee
    * @Description 添加新句子集
    * @Date 9:20 2018-07-11
    * @Param [sentenceCollection]
    * @return void
    **/
    public void addNewCollection(SentenceCollection sentenceCollection){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(sentenceCollection);
        tx.commit();
    }

    /**
    * @author hasee
    * @Description 获取热门的句子集
    * @Date 16:36 2018-07-11
    * @Param [num] 返回的条数
    * @return java.util.List<bean.SentenceCollection>
    **/
    public List<SentenceCollection> getHotCollections(int num){
        Session session = sessionFactory.openSession();
        String hql = "From SentenceCollection order by loveNum desc";
        List<SentenceCollection> sentenceCollections = new ArrayList<SentenceCollection>();
        Transaction tx = session.beginTransaction();
        sentenceCollections =  session.createQuery(hql).setMaxResults(num).list();
        tx.commit();
        return sentenceCollections;
    }


    /**
    * @author hasee
    * @Description 判断用户是否喜欢一个句子集
    * @Date 17:03 2018-07-12
    * @Param [userId, sentenceCollectionId]
    * @return java.lang.Boolean
    **/
    public Boolean checkUserLoveCollection(long userId, long sentenceCollectionId){
        Session session = sessionFactory.openSession();
        String hql = "select count(*) From CollectionLove where userId = ? AND collectionId = ?";
        long num = 0;
        Transaction tx = session.beginTransaction();
        num = (Long) session.createQuery(hql).setParameter(0,userId).setParameter(1,sentenceCollectionId).uniqueResult();
        tx.commit();
        if(num > 0){
            return true;
        }
        return false;
    }



    // 根据用户Id返回其喜欢的所有句子集的列表
    public List<CollectionLove> getLoveSentenceCollectionsByUserId(long userId){
        Session session = sessionFactory.openSession();
        String hql = "From CollectionLove where userId = ?";
        List<CollectionLove> collectionLoves = new ArrayList<CollectionLove>();
        Transaction tx = session.beginTransaction();
        collectionLoves =  session.createQuery(hql).setParameter(0,userId).list();
        tx.commit();
        return collectionLoves;
    }

    // 根据句子集编号返回句子集对象
    public SentenceCollection getSentenceCollectionByCollectionId(long id){
        Session session = sessionFactory.openSession();
        String hql = "From SentenceCollection where id = ?";
        SentenceCollection sentenceCollection = new SentenceCollection();
        Transaction tx = session.beginTransaction();
        sentenceCollection = (SentenceCollection) session.createQuery(hql).setParameter(0,id).uniqueResult();
        tx.commit();
        return sentenceCollection;
    }

    // 判断句子集是否存在
    public Boolean isCollectionExist(long id){
        Session session = sessionFactory.openSession();
        String hql = "select count(*) From SentenceCollection where id = ?";
        long num = 0;
        Transaction tx = session.beginTransaction();
        num = (Long) session.createQuery(hql).setParameter(0,id).uniqueResult();
        tx.commit();
        if(num > 0){
            return true;
        }
        return false;
    }

    // 添加一条用户喜欢句子集的记录
    public void addUserLoveCollection(CollectionLove collectionLove){
        Session session = sessionFactory.openSession();
        String hql = "update SentenceCollection set loveNum = loveNum + 1 where id = ?";
        Transaction tx = session.beginTransaction();
        session.save(collectionLove);
        session.createQuery(hql).setParameter(0,collectionLove.getCollectionId()).executeUpdate();
        tx.commit();
    }

    // 从句子集喜欢表中删除一条记录
    public void cancelUserLoveCollection(long userId, long collectionId){
        Session session = sessionFactory.openSession();
        String hql = "delete From CollectionLove where userId = ? AND collectionId = ?";
        String hql_2 = "update SentenceCollection set loveNum = loveNum - 1 where id = ?";
        Transaction tx = session.beginTransaction();
        session.createQuery(hql).setParameter(0,userId).setParameter(1,collectionId).executeUpdate();
        session.createQuery(hql_2).setParameter(0,collectionId).executeUpdate();
        tx.commit();
    }

    // 获取一个句子集对应的  句子集-句子 列表
    public List<CollectionSentence> getCollectionSentenceByCltId(long collectionId){
        Session session = sessionFactory.openSession();
        String hql = "From CollectionSentence where collectionId = ?";
        List<CollectionSentence> collectionSentences = new ArrayList<CollectionSentence>();
        Transaction tx = session.beginTransaction();
        collectionSentences =  session.createQuery(hql).setParameter(0,collectionId).list();
        tx.commit();
        return collectionSentences;
    }


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
