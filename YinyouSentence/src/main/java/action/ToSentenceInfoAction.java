package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ClassName ToSentenceInfoAction
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-08 14:56
 * Version 1.0
 */
public class ToSentenceInfoAction extends ActionSupport implements SessionAware, ServletRequestAware {
    private Map session;
    private HttpServletRequest request;
    private long sentenceId;

    @Override
    public String execute() throws Exception {

        return SUCCESS;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public long getSentenceId() {
        return sentenceId;
    }

    public void setSentenceId(long sentenceId) {
        this.sentenceId = sentenceId;
    }
}
