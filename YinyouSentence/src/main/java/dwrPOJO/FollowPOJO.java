package dwrPOJO;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * @ClassName FollowPOJO
 * @Description 关注返回值对象
 * @Author hasee
 * @Date 2018-07-12 10:15
 * Version 1.0
 */
public class FollowPOJO {
    // 操作是否成功
    private Boolean success;
    // 如果操作失败的原因
    private String reason;
    // 已关注的用户/名家/出处的关注者数量
    private long followerNum;
    // 判断是关注还是取消关注，true为关注
    private Boolean follow;


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public long getFollowerNum() {
        return followerNum;
    }

    public void setFollowerNum(long followerNum) {
        this.followerNum = followerNum;
    }

    public Boolean getFollow() {
        return follow;
    }

    public void setFollow(Boolean follow) {
        this.follow = follow;
    }
}
