package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import pageEntity.PublishSentenceEntity;

import java.util.Map;

/**
 * @ClassName ToPublishSentenceAction
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-07 10:49
 * Version 1.0
 */
public class ToPublishSentenceAction extends ActionSupport implements SessionAware{

    private Map session;

    @Autowired
    private PublishSentenceEntity publishSentenceEntity;

    @Override
    public String execute() throws Exception {
        publishSentenceEntity.init();
        session.put("publishSentenceEntity",publishSentenceEntity);
        return SUCCESS;
    }

    public PublishSentenceEntity getPublishSentenceEntity() {
        return publishSentenceEntity;
    }

    public void setPublishSentenceEntity(PublishSentenceEntity publishSentenceEntity) {
        this.publishSentenceEntity = publishSentenceEntity;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
