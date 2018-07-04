package service;

import bean.Login;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName LoginService
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-04 11:36
 * Version 1.0
 */
public class LoginService {
    @Autowired
    private SessionFactory sessionFactory;

    public void login(Login login){
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        s.save(login);
        tx.commit();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
