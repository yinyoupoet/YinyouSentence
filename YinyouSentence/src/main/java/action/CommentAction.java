package action;

import bean.SentenceComment;
import bean.UserInfo;
import com.opensymphony.xwork2.ActionSupport;
import dao.CommentDao;
import dao.SentenceDao;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Map;

/**
 * @ClassName CommentAction
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-10 9:59
 * Version 1.0
 */
public class CommentAction extends ActionSupport implements SessionAware{

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private SentenceDao sentenceDao;
    private String sentenceId;
    private String content;
    private Map session;

    @Override
    public String execute() throws Exception {
        long sId = 0;
        // 验证sId格式是否正确
        try{
            sId = Long.valueOf(sentenceId);
        }catch (Exception e){
            return INPUT;
        }
        // 验证sId这句子是否真的存在
        if(! sentenceDao.isSentenceExist(sId)){
            // 如果句子不存在
            return INPUT;
        }
        // 判断是否已登录
        UserInfo userInfo = (UserInfo) session.get("userInfo");
        if(userInfo == null){
            return INPUT;
        }

        SentenceComment sc = new SentenceComment();
        sc.setUserId(userInfo.getId());
        sc.setSentenceId(sId);
        sc.setContent(content);
        sc.setCommentTime(new Timestamp(System.currentTimeMillis()));
        commentDao.publishComment(sc);


        return SUCCESS;
    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSentenceId() {
        return sentenceId;
    }

    public void setSentenceId(String sentenceId) {
        this.sentenceId = sentenceId;
    }

    public CommentDao getCommentDao() {
        return commentDao;
    }

    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public SentenceDao getSentenceDao() {
        return sentenceDao;
    }

    public void setSentenceDao(SentenceDao sentenceDao) {
        this.sentenceDao = sentenceDao;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
