package service;

import bean.Login;
import dao.LoginRegisterDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName LoginService
 * @Description DWR中异步处理登录相关事宜
 * @Author hasee
 * @Date 2018-07-05 11:57
 * Version 1.0
 */
public class LoginService {
    @Autowired
    private LoginRegisterDao loginRegisterDao;

    public Boolean isInfoRight(String name, String pwd){
        Login login = new Login();
        login.setName(name);
        login.setPwd(pwd);
        System.out.println(login.getName() + "  " + login.getPwd());
        if(loginRegisterDao.checkLogin(login)){
            return true;
        }
        return false;
    }

    public void setLoginRegisterDao(LoginRegisterDao loginRegisterDao) {
        this.loginRegisterDao = loginRegisterDao;
    }
}
