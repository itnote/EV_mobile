package egovframework.evcar.user.vo;

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
    private int camt;
    private int cwh;
    private String pyn;
    private int totUnclAmt;
    private int totSetamt;
    private String useYn;
    private String createdAt;
    private String createdBy;
    private String updatedAt;
    private String updatedBy;

    private List<UsrCardVO> usrCardVOList;

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

    public int getCamt() {
        return camt;
    }

    public void setCamt(int camt) {
        this.camt = camt;
    }

    public int getCwh() {
        return cwh;
    }

    public void setCwh(int cwh) {
        this.cwh = cwh;
    }

    public String getPyn() {
        return pyn;
    }

    public void setPyn(String pyn) {
        this.pyn = pyn;
    }

    public int getTotUnclAmt() {
        return totUnclAmt;
    }

    public void setTotUnclAmt(int totUnclAmt) {
        this.totUnclAmt = totUnclAmt;
    }

    public int getTotSetamt() {
        return totSetamt;
    }

    public void setTotSetamt(int totSetamt) {
        this.totSetamt = totSetamt;
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

    public List<UsrCardVO> getUsrCardVOList() {
        return usrCardVOList;
    }

    public void setUsrCardVOList(List<UsrCardVO> usrCardVOList) {
        this.usrCardVOList = usrCardVOList;
    }
}
