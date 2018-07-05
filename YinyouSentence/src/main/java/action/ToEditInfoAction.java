package action;

import bean.UserInfo;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import util.DateUtil;

import java.sql.Date;
import java.util.Map;

/**
 * @ClassName ToEditInfoAction
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-05 18:07
 * Version 1.0
 */
public class ToEditInfoAction extends ActionSupport implements SessionAware {
    @Autowired
    private DateUtil dateUtil;

    private Map session;

    /**
    * @author hasee
    * @Description 进入编辑个人资料页，需要的东西都在session中啦
    * @Date 18:08 2018-07-05
    * @Param []
    * @return java.lang.String
    **/
    public String toEditInfoAction(){
        // 如果没登录就进不来这个页面
        UserInfo userInfo = (UserInfo) session.get("userInfo");
        if(userInfo == null){
            return INPUT;
        }

        // 在session中设置日期，需要数据库的日期、现在的日期
        Date date = userInfo.getBirth();
        String birthDate =  dateUtil.getStringBySQLDate(date);
        java.util.Date date1 = new java.util.Date();
        String nowDate = dateUtil.getStringByDate(date1);
        session.put("birthDate",birthDate);
        session.put("nowDate",nowDate);

        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public void setDateUtil(DateUtil dateUtil) {
        this.dateUtil = dateUtil;
    }
}
