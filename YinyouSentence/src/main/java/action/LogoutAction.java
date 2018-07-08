package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * @ClassName LogoutAction
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-08 13:08
 * Version 1.0
 */
public class LogoutAction extends ActionSupport implements SessionAware {
    private Map session;

    @Override
    public String execute() throws Exception {
        try {
            logout();
        }catch (Exception e){
            e.printStackTrace();
        }
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
}
