package dao;

import bean.Category;
import bean.SentenceCollection;
import bean.Tag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName InformationDao
 * @Description 一些辅助信息的查询
 * @Author hasee
 * @Date 2018-07-07 11:07
 * Version 1.0
 */
public class InformationDao {
    @Autowired
    private SessionFactory sessionFactory;

    /**
    * @author hasee
    * @Description 从数据库读取所有文章类别并返回
    * @Date 11:12 2018-07-07
    * @Param []
    * @return java.util.List<bean.Category>
    **/
    public List<Category> getAllCategories(){
        Session session = sessionFactory.openSession();
        String hql = "from Category c";
        List<Category> list = session.createQuery(hql).list();
        return list;
    }


    /**
    * @author hasee
    * @Description 得到指定用户ID的所有句子集
    * @Date 12:05 2018-07-07
    * @Param [id]
    * @return java.util.List<bean.SentenceCollection>
    **/
    public List<SentenceCollection> getSentenceCollections(long id){
        Session session = sessionFactory.openSession();
        String hql = "from SentenceCollection s where publisherId = ?";
        List<SentenceCollection> list = session.createQuery(hql).setParameter(0,id).list();
        return list;
    }

    /**
    * @author hasee
    * @Description 得到热度前几的标签
    * @Date 12:06 2018-07-07
    * @Param []
    * @return java.util.List<bean.Tag>
    **/
    public List<Tag> getHotTags(int num){
        Session session = sessionFactory.openSession();
        String hql = "FROM Tag order by quoteNum desc";
        List<Tag> tags = session.createQuery(hql).setFirstResult(0).setMaxResults(num).list();
        return tags;
    }


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
