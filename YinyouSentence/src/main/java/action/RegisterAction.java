package action;

import bean.Login;
import bean.UserInfo;
import com.opensymphony.xwork2.ActionSupport;
import dao.LoginRegisterDao;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import util.DateUtil;

import java.sql.Date;
import java.util.Map;

/**
 * @ClassName RegisterAction
 * @Description 字段全都合格后，进行注册操作
 * @Author hasee
 * @Date 2018-07-05 15:32
 * Version 1.0
 */
public class RegisterAction extends ActionSupport implements SessionAware {
    @Autowired
    private DateUtil dateUtil;

    @Autowired
    private LoginRegisterDao loginRegisterDao;

    private Map session;

    private String rUserName;
    private String rUserPwd;

    /**
    * @author hasee
    * @Description 注册方法，将数据插入数据库，并为其生成默认userInfo，并跳转到资料编写页
    * @Date 15:33 2018-07-05
    * @Param []
    * @return java.lang.String
    **/
    public String doRegister(){
        // 1、清空session
        try {
            logout();
        }catch (Exception e){
            return ERROR;
        }

        // 2、将数据存入数据库
        Login login = new Login();
        login.setName(rUserName);
        login.setPwd(rUserPwd);
        long id = loginRegisterDao.register(login);

        // 3、初始化用户详细信息，并保存到数据库
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setUserName(login.getName());
        Date date = dateUtil.getSqlDate("2000-01-01");
        userInfo.setBirth(date);
        userInfo.setGender("女");
        userInfo.setHeadPath("http://47.94.212.1:8080/imgs/1.png");
        userInfo.setMotto("");
        loginRegisterDao.initUserInfo(userInfo);

        // 4、将userInfo存入session
        session.put("userInfo",userInfo);
        return SUCCESS;
    }

    public void setLoginRegisterDao(LoginRegisterDao loginRegisterDao) {
        this.loginRegisterDao = loginRegisterDao;
    }

    public String getrUserName() {
        return rUserName;
    }

    public void setrUserName(String rUserName) {
        this.rUserName = rUserName;
    }

    public String getrUserPwd() {
        return rUserPwd;
    }

    public void setrUserPwd(String rUserPwd) {
        this.rUserPwd = rUserPwd;
    }


    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }


    // 注销session
    public String logout() throws Exception {
        if(this.session != null){
            ((org.apache.struts2.dispatcher.SessionMap<String, Object>) this.session).invalidate();
        }
        return SUCCESS;
    }

    public void setDateUtil(DateUtil dateUtil) {
        this.dateUtil = dateUtil;
    }
}
