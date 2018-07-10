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
    public Long register(Login login){
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(login);
        tx.commit();

        String hql = "select id FROM Login login where name = ?";
        long id =  (Long) session.createQuery(hql).setParameter(0,login.getName()).uniqueResult();
        return id;
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


    /**
    * @author hasee
    * @Description 修改密码
    * @Date 9:01 2018-07-07
    * @Param [id, pwd]
    * @return void
    **/
    public void changePwd(long id,String pwd){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        String hql = "update Login login set login.pwd = ? where login.id = ?";
        session.createQuery(hql).setParameter(0,pwd).setParameter(1,id).executeUpdate();
        tx.commit();
    }


    /**
    * @author hasee
    * @Description 通过用户名获得UserInfo
    * @Date 21:15 2018-07-07
    * @Param [userName]
    * @return bean.UserInfo
    **/
    public UserInfo getUserInfoByUserName(String userName){
        Session session = sessionFactory.openSession();
        String hql = "FROM UserInfo userInfo WHERE userName = ?";
        UserInfo userInfo = null;
        Transaction tx = session.beginTransaction();
        userInfo = (UserInfo) session.createQuery(hql).setParameter(0,userName).uniqueResult();
        tx.commit();
        return userInfo;
    }


    /**
    * @author hasee
    * @Description 修改个人信息
    * @Date 9:11 2018-07-07
    * @Param [userInfo]
    * @return void
    **/
    public void editUserInfo(UserInfo userInfo){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(userInfo);
        tx.commit();
    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
