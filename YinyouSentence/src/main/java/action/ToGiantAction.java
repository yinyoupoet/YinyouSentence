package action;

import bean.UserInfo;
import com.opensymphony.xwork2.ActionSupport;
import dao.GiantDao;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import pageEntity.GiantInfoEntity;

import java.util.Map;

/**
 * @ClassName ToGiantAction
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-08 14:20
 * Version 1.0
 */
public class ToGiantAction extends ActionSupport implements SessionAware{

    @Autowired
    private GiantInfoEntity giantInfoEntity;
    private Map session;

    private String giantId;


    @Override
    public String execute() throws Exception {
        long myGiantId = 0;
        try {
            myGiantId = Long.valueOf(giantId);
        }catch (Exception e){
            return ERROR;
        }

        long userId = 0;
        UserInfo userInfo = (UserInfo) session.get("userInfo");
        if(userInfo != null){
            userId = userInfo.getId();
        }

        giantInfoEntity.init(userId, myGiantId);
        if(giantInfoEntity.getGiantInfo() == null){
            // 说明获得的id有问题
            return ERROR;
        }
        session.put("giantInfoEntity",giantInfoEntity);
        return SUCCESS;
    }




    public GiantInfoEntity getGiantInfoEntity() {
        return giantInfoEntity;
    }

    public void setGiantInfoEntity(GiantInfoEntity giantInfoEntity) {
        this.giantInfoEntity = giantInfoEntity;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public void setGiantId(String giantId) {
        this.giantId = giantId;
    }
}
