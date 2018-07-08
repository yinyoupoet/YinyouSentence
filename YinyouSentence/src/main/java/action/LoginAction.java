package action;

import bean.Login;
import bean.UserInfo;
import com.opensymphony.xwork2.ActionSupport;
import dao.LoginRegisterDao;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @ClassName LoginAction
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-05 15:12
 * Version 1.0
 */
public class LoginAction extends ActionSupport implements SessionAware{

    @Autowired
    private LoginRegisterDao loginRegisterDao;
    private String lUserName;
    private String lUserPwd;

    private Map session;

    public String doLogin(){
        Login login = new Login();
        login.setName(lUserName);
        login.setPwd(lUserPwd);
        if(! loginRegisterDao.checkLogin(login)){
            return INPUT;
        }

        try {
            logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserInfo userInfo = loginRegisterDao.getUserInfoByUserName(getlUserName());
        if(userInfo == null){
            return INPUT;
        }
        session.put("userInfo",userInfo);
        return SUCCESS;
    }

    // 注销session
    public String logout() throws Exception {
        if(this.session != null){
            ((org.apache.struts2.dispatcher.SessionMap<String, Object>) this.session).invalidate();
        }
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getlUserName() {
        return lUserName;
    }

    public void setlUserName(String lUserName) {
        this.lUserName = lUserName;
    }

    public String getlUserPwd() {
        return lUserPwd;
    }

    public void setlUserPwd(String lUserPwd) {
        this.lUserPwd = lUserPwd;
    }

    public LoginRegisterDao getLoginRegisterDao() {
        return loginRegisterDao;
    }

    public void setLoginRegisterDao(LoginRegisterDao loginRegisterDao) {
        this.loginRegisterDao = loginRegisterDao;
    }
}
