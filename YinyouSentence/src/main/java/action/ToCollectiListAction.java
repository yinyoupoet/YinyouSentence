package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * @ClassName ToCollectiListAction
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-11 21:34
 * Version 1.0
 */
public class ToCollectiListAction extends ActionSupport implements SessionAware{

    private Map session;

    @Override
    public String execute() throws Exception {
        return super.execute();
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public Map getSession() {
        return session;
    }
}
