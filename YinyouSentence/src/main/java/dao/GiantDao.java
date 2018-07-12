package dao;

import bean.GiantInfo;
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




    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
