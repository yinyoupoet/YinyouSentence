package pageEntity;

import bean.GiantInfo;
import bean.OriginInfo;
import bean.OriginLove;
import bean.UserInfo;
import dao.GiantDao;
import dao.OriginDao;
import dao.PeopleDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OriginInfoEntity
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-13 11:17
 * Version 1.0
 */
public class OriginInfoEntity {
    @Autowired
    private OriginDao originDao;
    @Autowired
    private GiantDao giantDao;
    @Autowired
    private PeopleDao peopleDao;

    private OriginInfo originInfo;
    private GiantInfo giantInfo;
    private List<UserInfo> loveUsers = new ArrayList<UserInfo>();
    private Boolean loveOrNot;


    public void init(long userId, long originId){
        originInfo = originDao.getOriginInfoById(originId);
        loveOrNot = originDao.checkIfUserLoveOrigin(userId,originId);
        giantInfo = giantDao.getGiantInfoById(originInfo.getAuthorId());

        if(loveUsers != null){
            loveUsers.clear();
        }else{
            loveUsers = new ArrayList<UserInfo>();
        }
        List<OriginLove> originLoves = originDao.getLoveOriginUsers(originId);
        for(OriginLove originLove : originLoves){
            UserInfo userInfo = peopleDao.getUserInfoById(originLove.getUserId());
            loveUsers.add(userInfo);
        }
    }


    public OriginDao getOriginDao() {
        return originDao;
    }

    public void setOriginDao(OriginDao originDao) {
        this.originDao = originDao;
    }

    public GiantDao getGiantDao() {
        return giantDao;
    }

    public void setGiantDao(GiantDao giantDao) {
        this.giantDao = giantDao;
    }

    public PeopleDao getPeopleDao() {
        return peopleDao;
    }

    public void setPeopleDao(PeopleDao peopleDao) {
        this.peopleDao = peopleDao;
    }

    public OriginInfo getOriginInfo() {
        return originInfo;
    }

    public void setOriginInfo(OriginInfo originInfo) {
        this.originInfo = originInfo;
    }

    public List<UserInfo> getLoveUsers() {
        return loveUsers;
    }

    public void setLoveUsers(List<UserInfo> loveUsers) {
        this.loveUsers = loveUsers;
    }

    public Boolean getLoveOrNot() {
        return loveOrNot;
    }

    public void setLoveOrNot(Boolean loveOrNot) {
        this.loveOrNot = loveOrNot;
    }

    public GiantInfo getGiantInfo() {
        return giantInfo;
    }

    public void setGiantInfo(GiantInfo giantInfo) {
        this.giantInfo = giantInfo;
    }
}
