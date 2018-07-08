package action;

import bean.UserInfo;
import com.opensymphony.xwork2.ActionSupport;
import dao.LoginRegisterDao;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import util.DateUtil;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.sql.Date;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @ClassName EditAction
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-06 16:29
 * Version 1.0
 */
public class EditAction extends ActionSupport implements SessionAware{
    /* 文件上传：https://blog.csdn.net/Stephen__Xu/article/details/78598369*/
   private String headChange;
    private File imgUpload;
    private String imgUploadFileName;
    private String imgUploadContentType;
    private Map session;
    private String userName;
    private String userPwd;
    private String optionsRadios;
    private String userBirth;
    private String userMotto;

    @Autowired
    private LoginRegisterDao loginRegisterDao;



    @Autowired
    private DateUtil dateUtil;


    public String doEdit(){
        System.out.println(headChange + " " + imgUploadFileName + " " + imgUploadContentType + " " + userName + " " + userPwd + " " + optionsRadios + " " + userBirth + " " + userMotto);

        UserInfo userInfo = (UserInfo) session.get("userInfo");
        if(userInfo == null){
            return ERROR;
        }
        String fileName = userInfo.getHeadPath();
        // 如果头像改变了
        if("true".equals(headChange.trim())){
            fileName = saveImg();
        }

        long id = userInfo.getId();
        // 存入数据库，先改密码，再改详细信息
        try {
            if (!"".equals(userPwd.trim())) {
                // 如果密码长度不为空
                loginRegisterDao.changePwd(id,userPwd);
            }

            // 封装一个UserInfo对象进行修改
            userInfo.setUserName(userName);
            userInfo.setGender(optionsRadios);
            userInfo.setHeadPath(fileName);
            Date date = dateUtil.getSqlDate(userBirth);
            userInfo.setBirth(date);
            userInfo.setMotto(userMotto);
            loginRegisterDao.editUserInfo(userInfo);

        }catch (Exception e){
            return ERROR;
        }

        session.put("userInfo",userInfo);
        return SUCCESS;
    }


    /**
    * @author hasee
    * @Description 保存文件
    * @Date 22:03 2018-07-06
    * @Param []
    * @return java.lang.String 返回文件相对路径
    **/
    private String saveImg(){
        try{
            UserInfo userInfo = (UserInfo) session.get("userInfo");
            if(userInfo == null){
                return INPUT;
            }
            String uploadDir = "C:\\imgs\\" + userInfo.getId();
            File dir = new File(uploadDir);
            if(!dir.isDirectory()){
                dir.mkdir();
            }
            String fileName = System.currentTimeMillis() + this.getImgUploadFileName();
            String savePath = uploadDir + "\\" + fileName;
            File saveImg = new File(savePath);

            FileUtils.copyFile(imgUpload,saveImg);
            return "/imgs/" + userInfo.getId() + "/" + fileName;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }


    public String getImgUploadFileName() {
        return imgUploadFileName;
    }

    public void setImgUploadFileName(String imgUploadFileName) {
        this.imgUploadFileName = imgUploadFileName;
    }

    public String getImgUploadContentType() {
        return imgUploadContentType;
    }

    public void setImgUploadContentType(String imgUploadContentType) {
        this.imgUploadContentType = imgUploadContentType;
    }

    public File getImgUpload() {
        return imgUpload;
    }

    public void setImgUpload(File imgUpload) {
        this.imgUpload = imgUpload;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getHeadChange() {
        return headChange;
    }

    public void setHeadChange(String headChange) {
        this.headChange = headChange;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getOptionsRadios() {
        return optionsRadios;
    }

    public void setOptionsRadios(String optionsRadios) {
        this.optionsRadios = optionsRadios;
    }

    public String getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(String userBirth) {
        this.userBirth = userBirth;
    }

    public String getUserMotto() {
        return userMotto;
    }

    public void setUserMotto(String userMotto) {
        this.userMotto = userMotto;
    }

    public void setLoginRegisterDao(LoginRegisterDao loginRegisterDao) {
        this.loginRegisterDao = loginRegisterDao;
    }
    public void setDateUtil(DateUtil dateUtil) {
        this.dateUtil = dateUtil;
    }
}
