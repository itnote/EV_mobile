package egovframework.evcar.bbs;

import egovframework.evcar.common.vo.BaseVO;

/**
 * Created by dongguk on 2017-06-04.
 */
public class BbsMasterVO extends BaseVO {

    private String bbsId;
    private String bbsNm;
    private String bbsIntrcn;
    private String bbsTyCode;
    private String bbsAttrbCode;
    private String replyPosblAt;
    private String fileAtchPosblAt;
    private String atchPosblFileNumber;
    private String atchPosblFileSize;
    private String useAt;
    private String tmplatId;
    private String frstRegisterId;
    private String frstRegistPnttm;
    private String lastUpdusrId;
    private String lastUpdtPnttm;


    public String getBbsId() {
        return bbsId;
    }

    public void setBbsId(String bbsId) {
        this.bbsId = bbsId;
    }

    public String getBbsNm() {
        return bbsNm;
    }

    public void setBbsNm(String bbsNm) {
        this.bbsNm = bbsNm;
    }

    public String getBbsIntrcn() {
        return bbsIntrcn;
    }

    public void setBbsIntrcn(String bbsIntrcn) {
        this.bbsIntrcn = bbsIntrcn;
    }

    public String getBbsTyCode() {
        return bbsTyCode;
    }

    public void setBbsTyCode(String bbsTyCode) {
        this.bbsTyCode = bbsTyCode;
    }

    public String getBbsAttrbCode() {
        return bbsAttrbCode;
    }

    public void setBbsAttrbCode(String bbsAttrbCode) {
        this.bbsAttrbCode = bbsAttrbCode;
    }

    public String getReplyPosblAt() {
        return replyPosblAt;
    }

    public void setReplyPosblAt(String replyPosblAt) {
        this.replyPosblAt = replyPosblAt;
    }

    public String getFileAtchPosblAt() {
        return fileAtchPosblAt;
    }

    public void setFileAtchPosblAt(String fileAtchPosblAt) {
        this.fileAtchPosblAt = fileAtchPosblAt;
    }

    public String getAtchPosblFileNumber() {
        return atchPosblFileNumber;
    }

    public void setAtchPosblFileNumber(String atchPosblFileNumber) {
        this.atchPosblFileNumber = atchPosblFileNumber;
    }

    public String getAtchPosblFileSize() {
        return atchPosblFileSize;
    }

    public void setAtchPosblFileSize(String atchPosblFileSize) {
        this.atchPosblFileSize = atchPosblFileSize;
    }

    public String getUseAt() {
        return useAt;
    }

    public void setUseAt(String useAt) {
        this.useAt = useAt;
    }

    public String getTmplatId() {
        return tmplatId;
    }

    public void setTmplatId(String tmplatId) {
        this.tmplatId = tmplatId;
    }

    public String getFrstRegisterId() {
        return frstRegisterId;
    }

    public void setFrstRegisterId(String frstRegisterId) {
        this.frstRegisterId = frstRegisterId;
    }

    public String getFrstRegistPnttm() {
        return frstRegistPnttm;
    }

    public void setFrstRegistPnttm(String frstRegistPnttm) {
        this.frstRegistPnttm = frstRegistPnttm;
    }

    public String getLastUpdusrId() {
        return lastUpdusrId;
    }

    public void setLastUpdusrId(String lastUpdusrId) {
        this.lastUpdusrId = lastUpdusrId;
    }

    public String getLastUpdtPnttm() {
        return lastUpdtPnttm;
    }

    public void setLastUpdtPnttm(String lastUpdtPnttm) {
        this.lastUpdtPnttm = lastUpdtPnttm;
    }
}
