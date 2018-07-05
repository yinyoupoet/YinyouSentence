package dao;

import bean.Login;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
        System.out.println(num);
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
        System.out.println("register: " + num + " " + userName);
        if(num >= 1){
            return false;
        }
        return true;
    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
