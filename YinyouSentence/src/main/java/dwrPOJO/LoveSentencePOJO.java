package dwrPOJO;



/**
 * @ClassName LoveSentencePOJO
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-09 11:13
 * Version 1.0
 */
public class LoveSentencePOJO {
    // 操作是否成功
    private Boolean success = false;
    // 操作失败的原因
    private String reason = null;
    // 点击后的状态，是喜欢还是不喜欢
    private Boolean follow = false;
    // 还喜欢的人数
    private Long loveNum = (long) 0;

    public Long getLoveNum() {
        return loveNum;
    }

    public void setLoveNum(Long loveNum) {
        this.loveNum = loveNum;
    }



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

    public Boolean getFollow() {
        return follow;
    }

    public void setFollow(Boolean follow) {
        this.follow = follow;
    }


}
