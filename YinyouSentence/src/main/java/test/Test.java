package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import service.LoginService;

/**
 * @ClassName Test
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-04 10:42
 * Version 1.0
 */
public class Test {
    public static void main(String[] srgs){
        ApplicationContext ac = new FileSystemXmlApplicationContext("web/WEB-INF/applicationContext.xml");
        TestService ts = (TestService) ac.getBean("testService");
        ts.hello();

    }
}
