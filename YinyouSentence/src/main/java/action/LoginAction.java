package action;

import bean.Login;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import service.LoginService;

/**
 * @ClassName LoginAction
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-04 14:23
 * Version 1.0
 */
public class LoginAction extends ActionSupport{

    @Autowired
    private LoginService loginService;

    private String name;
    private String pwd;

    public String login(){
        Login login = new Login();
        login.setName(name);
        login.setPwd(pwd);
        loginService.login(login);
        return SUCCESS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
}
