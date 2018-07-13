package action;

import bean.GiantInfo;
import bean.Sentence;
import bean.UserInfo;
import com.opensymphony.xwork2.ActionSupport;
import dao.SentenceDao;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import pageEntity.GiantInfoEntity;
import pageEntity.SentenceEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ToGiantSentenceAction
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-09 9:34
 * Version 1.0
 */
public class ToGiantSentenceAction extends ActionSupport implements SessionAware{

    @Autowired
    private SentenceDao sentenceDao;
    @Autowired
    private GiantInfoEntity giantInfoEntity;

    private String giantId;
    private Map session;


    private List<SentenceEntity> sentenceEntities = new ArrayList<SentenceEntity>();


    @Override
    public String execute() throws Exception {
        long theGiantId = 0;
        try {
            theGiantId = Long.valueOf(giantId);
        }catch (Exception e){
            return ERROR;
        }
        UserInfo userInfo = (UserInfo) session.get("userInfo");
        long myId = 0;
        if(userInfo != null){
            myId = userInfo.getId();
        }
        giantInfoEntity.init(myId,theGiantId);

        initSentenceEntities(myId,theGiantId);

        session.put("giantInfoEntity",giantInfoEntity);
        session.put("sentenceEntities",sentenceEntities);
        return SUCCESS;
    }


    public void initSentenceEntities(long userId, long giantId){
        if(sentenceEntities != null){
            sentenceEntities.clear();
        }else {
            sentenceEntities = new ArrayList<SentenceEntity>();
        }
        List<Sentence> sentences = sentenceDao.getSentencesByAuthorId(giantId);
        for(Sentence s : sentences){
            SentenceEntity sentenceEntity = new SentenceEntity();
            sentenceEntity.setSentenceDao(sentenceDao);
            sentenceEntity.init(s.getId(), userId);
            sentenceEntities.add(sentenceEntity);
        }
    }


    public String getGiantId() {
        return giantId;
    }

    public void setGiantId(String giantId) {
        this.giantId = giantId;
    }

    public GiantInfoEntity getGiantInfoEntity() {
        return giantInfoEntity;
    }

    public void setGiantInfoEntity(GiantInfoEntity giantInfoEntity) {
        this.giantInfoEntity = giantInfoEntity;
    }

    public List<SentenceEntity> getSentenceEntities() {
        return sentenceEntities;
    }

    public void setSentenceEntities(List<SentenceEntity> sentenceEntities) {
        this.sentenceEntities = sentenceEntities;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public SentenceDao getSentenceDao() {
        return sentenceDao;
    }

    public void setSentenceDao(SentenceDao sentenceDao) {
        this.sentenceDao = sentenceDao;
    }
}
