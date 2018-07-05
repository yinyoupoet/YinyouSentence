package test;

import bean.Login;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


/**
 * @ClassName Test
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-05 9:28
 * Version 1.0
 */
public class Test {
    public static void main(String[] args){
        ApplicationContext ac = new FileSystemXmlApplicationContext("web/WEB-INF/applicationContext.xml");
        Login login = (Login) ac.getBean("login");
        //System.out.println(login.getName());
        SessionFactory sessionFactory = (SessionFactory) ac.getBean("sessionFactory");
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(login);
        tx.commit();
        System.out.println("存储成功");
    }
}
