package dwrPOJO;

/**
 * @ClassName CollectionCollectPOJO
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-10 23:47
 * Version 1.0
 */
public class CollectionCollectPOJO {
    private String sentenceId;
    private Boolean success;
    private String reason;
    private Boolean collect;
    private long sentenceNum;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Boolean getCollect() {
        return collect;
    }

    public void setCollect(Boolean collect) {
        this.collect = collect;
    }

    public long getSentenceNum() {
        return sentenceNum;
    }

    public void setSentenceNum(long sentenceNum) {
        this.sentenceNum = sentenceNum;
    }



    public String getSentenceId() {
        return sentenceId;
    }

    public void setSentenceId(String sentenceId) {
        this.sentenceId = sentenceId;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
