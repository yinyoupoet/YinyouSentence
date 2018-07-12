package service;

import bean.UserFollow;
import bean.UserInfo;
import dao.PeopleDao;
import dwrPOJO.FollowPOJO;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

/**
 * @ClassName PeopleService
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-12 10:12
 * Version 1.0
 */
public class PeopleService {
    @Autowired
    private PeopleDao peopleDao;

    public FollowPOJO followUser(String s_userId){
        FollowPOJO followPOJO = new FollowPOJO();
        HttpSession session = WebContextFactory.get().getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if(userInfo == null){
            followPOJO.setSuccess(false);
            followPOJO.setReason("请先登录呦");
            return followPOJO;
        }
        long userId = 0;
        try {
            userId = Long.valueOf(s_userId);
        }catch (Exception e){
            followPOJO.setSuccess(false);
            followPOJO.setReason("参数错误，请刷新页面后重试");
            return followPOJO;
        }
        if(! peopleDao.isUserIdExist(userId)){
            followPOJO.setSuccess(false);
            followPOJO.setReason("参数错误，请刷新页面后重试");
            return followPOJO;
        }
        long myId = userInfo.getId();
        if(myId == userId){
            followPOJO.setSuccess(false);
            followPOJO.setReason("不能关注自己呦");
            return followPOJO;
        }

        // 正常情况，可以进行关注了
        boolean isFollow = peopleDao.isFollowPeopleOrNot(myId,userId);
        if(isFollow){
            //如果已关注，那么就要取消关注
            peopleDao.cancelFollow(myId,userId);
            followPOJO.setFollow(false);
        }else{
            UserFollow userFollow = new UserFollow();
            userFollow.setFollowerId(myId);
            userFollow.setUserId(userId);
            peopleDao.toFollow(userFollow);
            followPOJO.setFollow(true);
        }
        followPOJO.setFollowerNum(peopleDao.getFollowerNumById(userId));
        followPOJO.setSuccess(true);
        return followPOJO;
    }



    public PeopleDao getPeopleDao() {
        return peopleDao;
    }

    public void setPeopleDao(PeopleDao peopleDao) {
        this.peopleDao = peopleDao;
    }
}
