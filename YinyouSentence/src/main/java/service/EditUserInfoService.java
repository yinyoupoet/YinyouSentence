package service;

import bean.UserInfo;
import jdk.internal.util.xml.impl.Input;
import org.directwebremoting.WebContextFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;

/**
 * @ClassName EditUserInfoService
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-06 10:41
 * Version 1.0
 */
public class EditUserInfoService {


    /**
    * @author hasee
    * @Description 上传头像
    * @Date 10:42 2018-07-06
    * @Param [is, filename]
    * @return java.lang.String 返回相对路径
    **/
    public String uploadHeadImg(String img, String filename){
       // System.out.println(is);
        //return "hqppy";

        String suffix = filename.substring(filename.lastIndexOf("."));

        HttpSession session = WebContextFactory.get().getSession();
        //InputStream   is   =   new ByteArrayInputStream(img.getBytes());
        //System.out.println(is);
        String url = null;
        byte[] bytes = null;
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if(userInfo == null){
            return "WRONG";
        }
        String uploadDir = "D:\\imgs\\" + userInfo.getId();
        File dir = new File(uploadDir);
        if(!dir.isDirectory()){
            dir.mkdir();
        }

        String fileName = System.currentTimeMillis() + suffix;


        String filePath = uploadDir + "\\" + fileName;
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        System.out.println(filePath);
        try{
            // bytes = new byte[is.available()];
            bytes = img.getBytes();
            File temp = new File(filePath);
            if(!temp.exists()){
                temp.createNewFile();
            }
            System.out.println(temp.getAbsolutePath());
            fos = new FileOutputStream(temp);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(bos != null){
                try {
                    bos.close();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if(fos != null){
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
        System.out.println(request.getContextPath()+fileName);
        String backPath = "/file/" + fileName;
        return backPath;
    }
}
