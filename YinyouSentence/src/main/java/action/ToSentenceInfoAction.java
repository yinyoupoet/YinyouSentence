package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.SentenceDao;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import pageEntity.SentenceEntity;

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

    @Autowired
    private SentenceDao sentenceDao;
    private Map session;
    private HttpServletRequest request;
    private long sentenceId;

    @Autowired
    private SentenceEntity sentenceEntity;

    @Override
    public String execute() throws Exception {
        if(! sentenceDao.isSentenceExist(sentenceId)){
            return ERROR;
        }
        sentenceEntity.init(sentenceId);
        session.put("sentenceEntity",sentenceEntity);
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

    public SentenceDao getSentenceDao() {
        return sentenceDao;
    }

    public void setSentenceDao(SentenceDao sentenceDao) {
        this.sentenceDao = sentenceDao;
    }

    public SentenceEntity getSentenceEntity() {
        return sentenceEntity;
    }

    public void setSentenceEntity(SentenceEntity sentenceEntity) {
        this.sentenceEntity = sentenceEntity;
    }
}
