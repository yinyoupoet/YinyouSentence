package service;

import dao.LoginRegisterDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName RegisterService
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-05 14:43
 * Version 1.0
 */
public class RegisterService {
    @Autowired
    private LoginRegisterDao loginRegisterDao;

    /**
     * 判断用户名是否已经存在，如果已经存在则返回false，否则返回true
     * */
    public Boolean isNameDuplicate(String userName){
        return loginRegisterDao.checkDuplicateName(userName);
    }

    public void setLoginRegisterDao(LoginRegisterDao loginRegisterDao) {
        this.loginRegisterDao = loginRegisterDao;
    }
}
