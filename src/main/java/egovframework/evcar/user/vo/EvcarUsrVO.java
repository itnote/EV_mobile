package egovframework.evcar.user.vo;

import egovframework.evcar.card.vo.UsrCardVO;

import java.util.List;

/**
 * Created by doum on 20171120.
 */
public class EvcarUsrVO {

    private String acrdCrdNo;
    private String userNm;
    private String userId;
    private String pwdNo;
    private String userEmail;
    private String addr1;
    private String addr1Dtl;
    private String telNo;
    private String telNo2;
    private String carCd;
    private String carYear;
    private String carNo;
    private String entryDd;
    private String crdIssueDd;
    private String crdRegDd;
    private String crdAcptDd;
    private String regDt;
    private String modDt;
    private String custId;

    private String crdIssueYear;
    private String crdIssueMonth;

    public String getCrdIssueYear() {
        return crdIssueYear;
    }

    public void setCrdIssueYear(String crdIssueYear) {
        this.crdIssueYear = crdIssueYear;
    }

    public String getCrdIssueMonth() {
        return crdIssueMonth;
    }

    public void setCrdIssueMonth(String crdIssueMonth) {
        this.crdIssueMonth = crdIssueMonth;
    }

    public String getAcrdCrdNo() {
        return acrdCrdNo;
    }

    public void setAcrdCrdNo(String acrdCrdNo) {
        this.acrdCrdNo = acrdCrdNo;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPwdNo() {
        return pwdNo;
    }

    public void setPwdNo(String pwdNo) {
        this.pwdNo = pwdNo;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr1Dtl() {
        return addr1Dtl;
    }

    public void setAddr1Dtl(String addr1Dtl) {
        this.addr1Dtl = addr1Dtl;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getTelNo2() {
        return telNo2;
    }

    public void setTelNo2(String telNo2) {
        this.telNo2 = telNo2;
    }

    public String getCarCd() {
        return carCd;
    }

    public void setCarCd(String carCd) {
        this.carCd = carCd;
    }

    public String getCarYear() {
        return carYear;
    }

    public void setCarYear(String carYear) {
        this.carYear = carYear;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getEntryDd() {
        return entryDd;
    }

    public void setEntryDd(String entryDd) {
        this.entryDd = entryDd;
    }

    public String getCrdIssueDd() {
        return crdIssueDd;
    }

    public void setCrdIssueDd(String crdIssueDd) {
        this.crdIssueDd = crdIssueDd;
    }

    public String getCrdRegDd() {
        return crdRegDd;
    }

    public void setCrdRegDd(String crdRegDd) {
        this.crdRegDd = crdRegDd;
    }

    public String getCrdAcptDd() {
        return crdAcptDd;
    }

    public void setCrdAcptDd(String crdAcptDd) {
        this.crdAcptDd = crdAcptDd;
    }

    public String getRegDt() {
        return regDt;
    }

    public void setRegDt(String regDt) {
        this.regDt = regDt;
    }

    public String getModDt() {
        return modDt;
    }

    public void setModDt(String modDt) {
        this.modDt = modDt;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    private List<UsrCardVO> usrCardList;

    public List<UsrCardVO> getUsrCardList() {
        return usrCardList;
    }

    public void setUsrCardList(List<UsrCardVO> usrCardList) {
        this.usrCardList = usrCardList;
    }
}
