package action;

import auxiliary.CommentAuxiliary;
import bean.UserInfo;
import com.opensymphony.xwork2.ActionSupport;
import dao.SentenceDao;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import pageEntity.CollectionEntity;
import pageEntity.CommentEntity;
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

    @Autowired
    private CommentEntity commentEntity;

    @Autowired
    private CollectionEntity collectionEntity;

    @Override
    public String execute() throws Exception {

        // 常规数据
        if(! sentenceDao.isSentenceExist(sentenceId)){
            return ERROR;
        }
        long myId = 0;
        if(session != null){
            UserInfo userInfo = (UserInfo) session.get("userInfo");
            if(userInfo != null){
                myId = userInfo.getId();
            }
        }
        sentenceEntity.init(sentenceId,myId);
        session.put("sentenceEntity",sentenceEntity);

        // 评论相关
        commentEntity.init(sentenceId);
        session.put("commentEntity",commentEntity);

        // 收藏夹相关
        collectionEntity.init(myId,sentenceId);
        session.put("collectionEntity",collectionEntity);

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

    public CommentEntity getCommentEntity() {
        return commentEntity;
    }

    public void setCommentEntity(CommentEntity commentEntity) {
        this.commentEntity = commentEntity;
    }

    public CollectionEntity getCollectionEntity() {
        return collectionEntity;
    }

    public void setCollectionEntity(CollectionEntity collectionEntity) {
        this.collectionEntity = collectionEntity;
    }
}
