package action;

import bean.UserInfo;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.directwebremoting.WebContextFactory;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Map;

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


    public String doEdit(){
        System.out.println(headChange);
        String fileName = saveImg();
        System.out.println(fileName);
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
}
