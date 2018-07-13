package pageEntity;

import bean.GiantInfo;
import bean.GiantLove;
import bean.OriginInfo;
import bean.UserInfo;
import dao.GiantDao;
import dao.PeopleDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName GiantInfoEntity
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-13 0:00
 * Version 1.0
 */
public class GiantInfoEntity {
    @Autowired
    private GiantDao giantDao;

    @Autowired
    private PeopleDao peopleDao;

    private GiantInfo giantInfo;
    // 当前用户是否喜欢该名家
    private Boolean loveGiantState;
    // 喜欢王小波的用户们
    private List<UserInfo> fansList = new ArrayList<UserInfo>();
    // 王小波的作品集
    private List<OriginInfo> originInfoList = new ArrayList<OriginInfo>();


    public void init(long userId, long giantId){
        giantInfo = giantDao.getGiantInfoById(giantId);

        // 判断当前用户是否喜欢该名家
        loveGiantState = giantDao.checkIfUserLoveGiant(userId,giantId);

        // 喜欢王小波的用户们
        initFans(giantId);

        // 王小波的作品集
        initOriginList(giantId);
    }



    // 初始化王小波的作品集
    public void initOriginList(long giantId){
        if(originInfoList != null){
            originInfoList.clear();
        }else{
            originInfoList = new ArrayList<OriginInfo>();
        }
        originInfoList = giantDao.getOriginInfoByAuthorId(giantId);
    }



    // 初始化喜欢王小波的用户们
    public void initFans(long giantId){
        if(fansList != null){
            fansList.clear();
        }else{
            fansList = new ArrayList<UserInfo>();
        }
        List<GiantLove> giantLoves = giantDao.getGiantLoveRecordForLoveGiant(giantId);
        for(GiantLove giantLove : giantLoves){
            UserInfo userInfo = peopleDao.getUserInfoById(giantLove.getUserId());
            fansList.add(userInfo);
        }

        if(fansList.size() > 12){
            fansList.subList(0,12);
        }

    }


    public GiantDao getGiantDao() {
        return giantDao;
    }

    public void setGiantDao(GiantDao giantDao) {
        this.giantDao = giantDao;
    }

    public GiantInfo getGiantInfo() {
        return giantInfo;
    }

    public void setGiantInfo(GiantInfo giantInfo) {
        this.giantInfo = giantInfo;
    }

    public Boolean getLoveGiantState() {
        return loveGiantState;
    }

    public void setLoveGiantState(Boolean loveGiantState) {
        this.loveGiantState = loveGiantState;
    }

    public List<UserInfo> getFansList() {
        return fansList;
    }

    public void setFansList(List<UserInfo> fansList) {
        this.fansList = fansList;
    }

    public List<OriginInfo> getOriginInfoList() {
        return originInfoList;
    }

    public void setOriginInfoList(List<OriginInfo> originInfoList) {
        this.originInfoList = originInfoList;
    }

    public PeopleDao getPeopleDao() {
        return peopleDao;
    }

    public void setPeopleDao(PeopleDao peopleDao) {
        this.peopleDao = peopleDao;
    }
}
