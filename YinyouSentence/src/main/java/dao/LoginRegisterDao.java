package dao;

import bean.Login;
import bean.UserInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName LoginRegisterDao
 * @Description 登录注册数据访问类
 * @Author hasee
 * @Date 2018-07-05 11:58
 * Version 1.0
 */
public class LoginRegisterDao {
    @Autowired
    private SessionFactory sessionFactory;

    /**
    * @author hasee
    * @Description 检查登录账号名密码是否正确
    * @Date 15:00 2018-07-05
    * @Param [login]
    * @return boolean
    **/
    public boolean checkLogin(Login login){
        Session session = sessionFactory.openSession();
        String hql = "select count(id) from Login login where name= ? AND pwd = ?";
        Long num = (Long) session.createQuery(hql).setParameter(0,login.getName()).setParameter(1,login.getPwd()).uniqueResult();
        if(num >= 1){
            return true;
        }
        return false;
    }

    /*
     *  用于在注册时，检查重复的用户名
     * */
    public boolean checkDuplicateName(String userName){
        Session session = sessionFactory.openSession();
        String hql = "select count(id) from Login login where name = ?";
        Long num = (Long) session.createQuery(hql).setParameter(0,userName).uniqueResult();
        if(num >= 1){
            return false;
        }
        return true;
    }

    /**
    * @author hasee
    * @Description 进行注册，返回用户ID
    * @Date 15:54 2018-07-05
    * @Param [login]
    * @return void
    **/
    public int register(Login login){
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(login);
        tx.commit();

        String hql = "select id FROM Login login where name = ?";
        long id =  (Long) session.createQuery(hql).setParameter(0,login.getName()).uniqueResult();
        return  (int) id;
    }

    /**
    * @author hasee
    * @Description 注册的时候，初始化userInfo这个表
    * @Date 16:19 2018-07-05
    * @Param [userInfo]
    * @return void
    **/
    public void initUserInfo(UserInfo userInfo){
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(userInfo);
        tx.commit();
    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
