package action;

import bean.UserInfo;
import com.opensymphony.xwork2.ActionSupport;
import dao.PeopleDao;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import pageEntity.PeopleEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ClassName ToPeopleAction
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-08 10:13
 * Version 1.0
 */
public class ToPeopleAction extends ActionSupport implements SessionAware,ServletRequestAware{

    @Autowired
    private PeopleEntity peopleEntity;

    @Autowired
    private PeopleDao peopleDao;
    private HttpServletRequest request;
    private Map session;


    @Override
    public String execute() throws Exception {
        String showId = request.getParameter("id");
        if(showId == null){
            return ERROR;
        }
        int show_id = 0;
        try{
            show_id = Integer.valueOf(showId);
        }catch (Exception e){
            return ERROR;
        }
        if(! peopleDao.isUserIdExist(show_id)){
            // 如果用户ID不存在
            return ERROR;
        }

        long myId = 0;
        UserInfo userInfo = (UserInfo) session.get("userInfo");
        if(userInfo != null){
            myId = userInfo.getId();
        }


        peopleEntity.init(myId, show_id);

        session.put("peopleEntity",peopleEntity);
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setPeopleDao(PeopleDao peopleDao) {
        this.peopleDao = peopleDao;
    }

    public void setPeopleEntity(PeopleEntity peopleEntity) {
        this.peopleEntity = peopleEntity;
    }
}
