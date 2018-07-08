package util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * @ClassName DateUtil
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-05 16:04
 * Version 1.0
 */
public class DateUtil {

    /**
    * @author hasee
    * @Description 根据字符串获得日期
    * @Date 16:09 2018-07-05
    * @Param [s_date]
    * @return java.util.Date
    **/
    public Date getDate(String s_date){
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = ft.parse(s_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
    * @author hasee
    * @Description 根据输入的字符串转化成相应的时间
    * @Date 16:13 2018-07-05
    * @Param [s_date_time]
    * @return java.util.Date
    **/
    public Date getDateTime(String s_date_time){
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
        Date date = new Date();
        try {
            date = ft.parse(s_date_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
    * @author hasee
    * @Description 根据 sql.Date 返回一个格式化字符串
    * @Date 19:24 2018-07-05
    * @Param []
    * @return java.lang.String
    **/
    public String getStringBySQLDate(java.sql.Date date){
        Date date1 = new Date(date.getTime());
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        String s = ft.format(date1);
        return s;
    }

    public String getStringByDate(Date date){
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        String s = ft.format(date);
        return s;
    }


    public java.sql.Date getSqlDate(String s_date){
        java.sql.Date date = java.sql.Date.valueOf(s_date);
        return date;
    }

}
