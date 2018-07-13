package dao;

import bean.GiantInfo;
import bean.GiantLove;
import bean.OriginInfo;
import bean.Sentence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName GiantDao
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-11 16:30
 * Version 1.0
 */
public class GiantDao {

    @Autowired
    private SessionFactory sessionFactory;


    // 获得热门名家，参数为数量
    public List<GiantInfo> getHotGiants(int num){
        Session session = sessionFactory.openSession();
        String hql = "From GiantInfo order by loveNum desc";
        List<GiantInfo> giantInfos = new ArrayList<GiantInfo>();
        Transaction tx = session.beginTransaction();
        giantInfos =  session.createQuery(hql).setMaxResults(num).list();
        tx.commit();
        return giantInfos;
    }

    // 根据名家ID获得名家信息
    public GiantInfo getGiantInfoById(long id){
        Session session = sessionFactory.openSession();
        String hql = "FROM GiantInfo where id = ?";
        GiantInfo giantInfo = null;
        Transaction tx = session.beginTransaction();
        giantInfo = (GiantInfo) session.createQuery(hql).setParameter(0,id).uniqueResult();
        tx.commit();
        return giantInfo;
    }

    // 判断用户是否喜欢名家
    public Boolean checkIfUserLoveGiant(long userId, long giantId){
        Session session = sessionFactory.openSession();
        String hql = "select count(*) FROM GiantLove where userId = ? and giantId = ?";
        long num = 0;
        Transaction tx = session.beginTransaction();
        num = (Long) session.createQuery(hql).setParameter(0,userId).setParameter(1,giantId).uniqueResult();
        tx.commit();
        if(num > 0){
            return true;
        }
        return false;
    }

    // 获取喜欢指定名家的名家喜欢记录
    public List<GiantLove> getGiantLoveRecordForLoveGiant(long giantId){
        Session session = sessionFactory.openSession();
        String hql = "From GiantLove where giantId = ?";
        List<GiantLove> giantLoves = new ArrayList<GiantLove>();
        Transaction tx = session.beginTransaction();
        giantLoves =  session.createQuery(hql).setParameter(0,giantId).list();
        tx.commit();
        return giantLoves;
    }

    // 获取指定作者的出处列表
    public List<OriginInfo> getOriginInfoByAuthorId(long giantId){
        Session session = sessionFactory.openSession();
        String hql = "From OriginInfo where authorId = ?";
        List<OriginInfo> originInfos = new ArrayList<OriginInfo>();
        Transaction tx = session.beginTransaction();
        originInfos =  session.createQuery(hql).setParameter(0,giantId).list();
        tx.commit();
        return originInfos;
    }

    // 根据名家ID判断名家是否存在
    public Boolean checkIfGiantExist(long id){
        Session session = sessionFactory.openSession();
        String hql = "select count(*) FROM GiantInfo giantInfo where id = ?";
        long count = 0;
        Transaction tx = session.beginTransaction();
        count = (Long) session.createQuery(hql).setParameter(0,id).uniqueResult();
        tx.commit();
        if(count >= 1){
            return true;
        }
        return false;
    }


    // 取消用户对于名家的喜欢
    public void cancelUserLoveGiant(long userId, long giantId){
        Session session = sessionFactory.openSession();
        String hql = "delete FROM GiantLove where userId = ? and giantId = ?";
        String hql_2 = "update from GiantInfo set loveNum = loveNum - 1 where id = ?";
        long count = 0;
        Transaction tx = session.beginTransaction();
        session.createQuery(hql).setParameter(0,userId).setParameter(1,giantId).executeUpdate();
        session.createQuery(hql_2).setParameter(0,giantId).executeUpdate();
        tx.commit();
    }

    // 新增用户对于名家的喜欢
    public void addUserLoveGiant(GiantLove giantLove){
        Session session = sessionFactory.openSession();
        String hql = "update from GiantInfo set loveNum = loveNum + 1 where id = ?";
        Transaction tx = session.beginTransaction();
        session.save(giantLove);
        session.createQuery(hql).setParameter(0,giantLove.getGiantId()).executeUpdate();
        tx.commit();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
