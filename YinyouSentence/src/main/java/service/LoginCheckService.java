package service;

import bean.UserInfo;
import org.directwebremoting.WebContextFactory;

import javax.servlet.http.HttpSession;

/**
 * @ClassName LoginCheckService
 * @Description 判断用户是否登录
 * @Author hasee
 * @Date 2018-07-10 11:37
 * Version 1.0
 */
public class LoginCheckService {

    // 是否已登录
    public Boolean isLoginYet(){
        HttpSession session = WebContextFactory.get().getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if(userInfo == null){
            return false;
        }
        return true;
    }
}
