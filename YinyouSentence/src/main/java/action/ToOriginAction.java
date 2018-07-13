package action;

import bean.Sentence;
import bean.UserInfo;
import com.opensymphony.xwork2.ActionSupport;
import dao.OriginDao;
import dao.SentenceDao;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import pageEntity.OriginInfoEntity;
import pageEntity.SentenceEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ToOriginAction
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-08 14:53
 * Version 1.0
 */
public class ToOriginAction extends ActionSupport implements SessionAware{

    private Map session;
    private String originId;
    @Autowired
    private SentenceDao sentenceDao;

    @Autowired
    private OriginDao originDao;

    @Autowired
    private OriginInfoEntity originInfoEntity;

    private List<SentenceEntity> sentenceEntities;

    @Override
    public String execute() throws Exception {
        long theOriginId = 0;
        try{
            theOriginId = Long.valueOf(originId);
        }catch (Exception e){
            return ERROR;
        }
        if(! originDao.checkIfOriginExist(theOriginId)){
            return ERROR;
        }
        long userId = 0;
        UserInfo userInfo = (UserInfo) session.get("userInfo");
        if(userInfo != null){
            userId = userInfo.getId();
        }
        originInfoEntity.init(userId,theOriginId);
        if(originInfoEntity.getLoveUsers().size() > 12){
            originInfoEntity.getLoveUsers().subList(0,12);
        }

        // 初始化句子集
        initSentenceEntities(userId, theOriginId);

        session.put("originInfoEntity",originInfoEntity);
        session.put("sentenceEntities",sentenceEntities);
        return SUCCESS;
    }

    // 初始化句子集
    public void initSentenceEntities(long userId, long originId){
        if(sentenceEntities != null){
            sentenceEntities.clear();
        }else{
            sentenceEntities = new ArrayList<SentenceEntity>();
        }

        List<Sentence> sentences = originDao.getSentencesInTheOrigin(originId);
        for(Sentence sentence : sentences){
            SentenceEntity sentenceEntity = new SentenceEntity();
            sentenceEntity.setSentenceDao(sentenceDao);
            sentenceEntity.init(sentence.getId(),userId);
            sentenceEntities.add(sentenceEntity);
        }
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public OriginInfoEntity getOriginInfoEntity() {
        return originInfoEntity;
    }

    public void setOriginInfoEntity(OriginInfoEntity originInfoEntity) {
        this.originInfoEntity = originInfoEntity;
    }

    public SentenceDao getSentenceDao() {
        return sentenceDao;
    }

    public void setSentenceDao(SentenceDao sentenceDao) {
        this.sentenceDao = sentenceDao;
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

    public OriginDao getOriginDao() {
        return originDao;
    }

    public void setOriginDao(OriginDao originDao) {
        this.originDao = originDao;
    }
}
