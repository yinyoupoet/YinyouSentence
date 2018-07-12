package action;

import bean.UserInfo;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import pageEntity.IndexEntity;

import java.util.Map;

/**
 * @ClassName ToIndexAction
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-07 10:35
 * Version 1.0
 */
public class ToIndexAction extends ActionSupport implements SessionAware{

    @Autowired
    private IndexEntity indexEntity;
    private Map session;

    /**
    * @author hasee
    * @Description 前往首页的准备类
    * @Date 10:37 2018-07-07
    * @Param []
    * @return java.lang.String
    **/
    @Override
    public String execute() throws Exception {
        UserInfo userInfo = (UserInfo) session.get("userInfo");
        if(userInfo == null){
            indexEntity.init(0);
        }else{
            indexEntity.init(userInfo.getId());
        }
        session.put("indexEntity",indexEntity);
        return SUCCESS;
    }

    public IndexEntity getIndexEntity() {
        return indexEntity;
    }

    public void setIndexEntity(IndexEntity indexEntity) {
        this.indexEntity = indexEntity;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
