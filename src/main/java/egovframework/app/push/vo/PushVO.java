package egovframework.app.push.vo;

/**
 * Created by jodongguk on 2017-08-02.
 */
public class PushVO {

    private String pushSq;
    private String userId;
    private String phoneType;
    private String handPhone;
    private String regId;
    private String regDt;
    private String msg;
    private String jobOfferNo;
    private String pushType;

    private String  param1;
    private String  param2;

    public String getPushSq() {
        return pushSq;
    }

    public void setPushSq(String pushSq) {
        this.pushSq = pushSq;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getHandPhone() {
        return handPhone;
    }

    public void setHandPhone(String handPhone) {
        this.handPhone = handPhone;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getRegDt() {
        return regDt;
    }

    public void setRegDt(String regDt) {
        this.regDt = regDt;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getJobOfferNo() {
        return jobOfferNo;
    }

    public void setJobOfferNo(String jobOfferNo) {
        this.jobOfferNo = jobOfferNo;
    }

    public String getPushType() {
        return pushType;
    }

    public void setPushType(String pushType) {
        this.pushType = pushType;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }


    @Override
    public String toString() {
        return "PushVO{" +
                "pushSq='" + pushSq + '\'' +
                ", userId='" + userId + '\'' +
                ", phoneType='" + phoneType + '\'' +
                ", handPhone='" + handPhone + '\'' +
                ", regId='" + regId + '\'' +
                ", regDt='" + regDt + '\'' +
                ", msg='" + msg + '\'' +
                ", jobOfferNo='" + jobOfferNo + '\'' +
                ", pushType='" + pushType + '\'' +
                ", param1='" + param1 + '\'' +
                ", param2='" + param2 + '\'' +
                '}';
    }
}
