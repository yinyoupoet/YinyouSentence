package auxiliary;

import bean.UserInfo;

/**
 * @ClassName UserAuxiliary
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-15 8:22
 * Version 1.0
 */
public class UserAuxiliary {


    private UserInfo userInfo;
    private Boolean loveOrNot;


    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }


    public Boolean getLoveOrNot() {
        return loveOrNot;
    }

    public void setLoveOrNot(Boolean loveOrNot) {
        this.loveOrNot = loveOrNot;
    }
}
