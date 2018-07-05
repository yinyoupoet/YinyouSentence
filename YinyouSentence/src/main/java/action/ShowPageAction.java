package action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @ClassName ShowPageAction
 * @Description 一个综合访问控制类，控制页面的访问
 * @Author hasee
 * @Date 2018-07-05 10:35
 * Version 1.0
 */
public class ShowPageAction extends ActionSupport{
    /**
    * @author hasee
    * @Description 进入登录注册页面，不含控制逻辑
    * @Date 10:37 2018-07-05
    * @Param []
    * @return java.lang.String
    **/
    public String toLoginOrRegister(){
        return SUCCESS;
    }
}
