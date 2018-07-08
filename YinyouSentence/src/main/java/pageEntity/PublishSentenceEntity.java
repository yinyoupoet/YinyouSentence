package pageEntity;

import bean.Category;
import bean.SentenceCollection;
import bean.Tag;
import bean.UserInfo;
import dao.InformationDao;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName PublishSentenceEntity
 * @Description 实体类，用于给发布文章页面去显示的内容
 * @Author hasee
 * @Date 2018-07-07 10:53
 * Version 1.0
 */
public class PublishSentenceEntity {
    @Autowired
    private InformationDao informationDao;

    private List<SentenceCollection> sentenceCollections = new LinkedList<SentenceCollection>();
    private List<Category> categories = new LinkedList<Category>();
    private List<Tag> hotTags = new LinkedList<Tag>();


    /**
    * @author hasee
    * @Description 监督者模式对其进行创建，尽管对顺序不要求
    * @Date 12:13 2018-07-07
    * @Param []
    * @return void
    **/
    public void init(){
        initSentenceCollection();
        initCategories();
        initHotTags();

    }


    public void initHotTags(){
        hotTags = informationDao.getHotTags(10);
    }

    public void initSentenceCollection(){
        // 1、getUserId
        HttpSession session = ServletActionContext.getRequest().getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if(userInfo == null){
            return;
        }
        sentenceCollections = informationDao.getSentenceCollections(userInfo.getId());
    }

    public void initCategories(){
        categories = informationDao.getAllCategories();
    }







    public InformationDao getInformationDao() {
        return informationDao;
    }

    public void setInformationDao(InformationDao informationDao) {
        this.informationDao = informationDao;
    }

    public List<SentenceCollection> getSentenceCollections() {
        return sentenceCollections;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    public void setSentenceCollections(List<SentenceCollection> sentenceCollections) {
        this.sentenceCollections = sentenceCollections;
    }

    public List<Tag> getHotTags() {
        return hotTags;
    }

    public void setHotTags(List<Tag> hotTags) {
        this.hotTags = hotTags;
    }
}
