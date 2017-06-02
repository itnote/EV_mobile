package egovframework.evcar.user.vo;

import egovframework.evcar.card.vo.UsrCardVO;

import java.util.List;

/**
 * Created by dongguk on 20170530.
 */
public class EvcarUsrVO {

    private String usrSno;
    private String usrCard;
    private String ath;
    private String usr;
    private String usrNm;
    private String usrId;
    private String usrPwd;
    private String usrCel;
    private String usrEmail;

    private String camt;
    private String cwh;
    private String pyn;
    private String totUnclAmt;
    private String totSetamt;
    private String totChw;
    private String useYn;
    private String createdAt;
    private String createdBy;
    private String updatedAt;
    private String updatedBy;

    private List<UsrCardVO> usrCardList;

    public String getUsrSno() {
        return usrSno;
    }

    public void setUsrSno(String usrSno) {
        this.usrSno = usrSno;
    }

    public String getUsrCard() {
        return usrCard;
    }

    public void setUsrCard(String usrCard) {
        this.usrCard = usrCard;
    }

    public String getAth() {
        return ath;
    }

    public void setAth(String ath) {
        this.ath = ath;
    }

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getUsrNm() {
        return usrNm;
    }

    public void setUsrNm(String usrNm) {
        this.usrNm = usrNm;
    }

    public String getUsrId() {
        return usrId;
    }

    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }

    public String getUsrPwd() {
        return usrPwd;
    }

    public void setUsrPwd(String usrPwd) {
        this.usrPwd = usrPwd;
    }

    public String getUsrCel() {
        return usrCel;
    }

    public void setUsrCel(String usrCel) {
        this.usrCel = usrCel;
    }

    public String getUsrEmail() {
        return usrEmail;
    }

    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }

    public String getCamt() {
        return camt;
    }

    public void setCamt(String camt) {
        this.camt = camt;
    }

    public String getCwh() {
        return cwh;
    }

    public void setCwh(String cwh) {
        this.cwh = cwh;
    }

    public String getPyn() {
        return pyn;
    }

    public void setPyn(String pyn) {
        this.pyn = pyn;
    }

    public String getTotUnclAmt() {
        return totUnclAmt;
    }

    public void setTotUnclAmt(String totUnclAmt) {
        this.totUnclAmt = totUnclAmt;
    }

    public String getTotSetamt() {
        return totSetamt;
    }

    public void setTotSetamt(String totSetamt) {
        this.totSetamt = totSetamt;
    }

    public String getTotChw() {
        return totChw;
    }

    public void setTotChw(String totChw) {
        this.totChw = totChw;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public List<UsrCardVO> getUsrCardList() {
        return usrCardList;
    }

    public void setUsrCardList(List<UsrCardVO> usrCardList) {
        this.usrCardList = usrCardList;
    }
}
