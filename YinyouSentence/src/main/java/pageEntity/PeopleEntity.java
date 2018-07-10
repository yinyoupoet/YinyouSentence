package pageEntity;

import bean.GiantInfo;
import bean.OriginInfo;
import bean.UserInfo;
import dao.PeopleDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName PeopleEntity
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-08 11:41
 * Version 1.0
 */
public class PeopleEntity {

    @Autowired
    private PeopleDao peopleDao;

    private String userName;
    private String birthYear;
    private String gender;
    private String motto;
    private String headPath;
    private Boolean isSelf;
    private long followingNum;
    private long followerNum;
    private long userId;
    private List<GiantInfo> followingGiants = new LinkedList<GiantInfo>();
    private List<OriginInfo> loveOrigins = new LinkedList<OriginInfo>();

    public void init(long myId, long showId){
        initBasicInfo(myId, showId);
        initFollowingAndFollower(showId);
        initFollowingGiants(showId);
        initFollowingOrigins(showId);
    }

    // 初始化个人基本信息
    public void initBasicInfo(long myId, long showId){
        if(myId == showId){
            isSelf = true;
            this.userId = myId;
        }else{
            isSelf = false;
            this.userId = showId;
        }
        UserInfo userInfo = peopleDao.getUserInfoById(showId);
        this.userName = userInfo.getUserName();
        int year = userInfo.getBirth().getYear();
        year = year % 100;
        if(year < 10){
            this.birthYear = "0" +  year + "年";
        }else{
            this.birthYear = year % 100 + "年";
        }

        this.gender = userInfo.getGender();
        this.motto = userInfo.getMotto();
        this.headPath = userInfo.getHeadPath();
    }

    // 初始化关注的用户数量和粉丝数量
    public void initFollowingAndFollower(long showId){
        this.followerNum = peopleDao.getFollowerNumById(showId);
        this.followingNum = peopleDao.getFollowingNumById(showId);
    }

    // 初始化关注的名家
    public void initFollowingGiants(long showId){
        List<Long> giantIds = peopleDao.getFollowingGiantIds(showId);
        if(followingGiants != null){
            followingGiants.clear();
        }else {
            followingGiants = new LinkedList<GiantInfo>();
        }

        for (Long giantId : giantIds){
            GiantInfo giantInfo = peopleDao.getGiantInfoById(giantId);
            followingGiants.add(giantInfo);
        }
    }

    // 初始化关注的出处
    public void initFollowingOrigins(long showId){
        List<Long> originIds = peopleDao.getFollowingOriginIds(showId);
        if(loveOrigins != null){
            loveOrigins.clear();
        }else {
            loveOrigins = new LinkedList<OriginInfo>();
        }

        for (Long originId : originIds){
            OriginInfo originInfo = peopleDao.getOriginInfoById(originId);
            loveOrigins.add(originInfo);
        }
    }

    public void setPeopleDao(PeopleDao peopleDao) {
        this.peopleDao = peopleDao;
    }

    public PeopleDao getPeopleDao() {
        return peopleDao;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    public Boolean getSelf() {
        return isSelf;
    }

    public void setSelf(Boolean self) {
        isSelf = self;
    }

    public Long getFollowingNum() {
        return followingNum;
    }

    public void setFollowingNum(Long followingNum) {
        this.followingNum = followingNum;
    }

    public Long getFollowerNum() {
        return followerNum;
    }

    public void setFollowerNum(Long followerNum) {
        this.followerNum = followerNum;
    }

    public List<GiantInfo> getFollowingGiants() {
        return followingGiants;
    }

    public void setFollowingGiants(List<GiantInfo> followingGiants) {
        this.followingGiants = followingGiants;
    }

    public List<OriginInfo> getLoveOrigins() {
        return loveOrigins;
    }

    public void setLoveOrigins(List<OriginInfo> loveOrigins) {
        this.loveOrigins = loveOrigins;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
