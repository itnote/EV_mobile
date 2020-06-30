package egovframework.evcar.card.vo;

import egovframework.evcar.user.vo.EvcarUsrVO; /**
 * Created by doum on 2017-12-12.
 */
public class UsrCardVO {

    private int userSno;
    private String billKey;
    private String dsbrsMthdCd;
    private String frDd;
    private String endDd;
    private String crdcCd;
    private String crdcNm;
    private String cardNo;
    private String cardNm;

    public String getCardNm() {
        return cardNm;
    }

    public void setCardNm(String cardNm) {
        this.cardNm = cardNm;
    }

    public UsrCardVO(){}

    public UsrCardVO(EvcarUsrVO loginVO) {
        this.userSno = loginVO.getUserSno();
    }
    public int getUserSno() {
        return userSno;
    }

    public void setUserSno(int userSno) {
        this.userSno = userSno;
    }

    public String getBillKey() {
        return billKey;
    }

    public void setBillKey(String billKey) {
        this.billKey = billKey;
    }

    public String getDsbrsMthdCd() {
        return dsbrsMthdCd;
    }

    public void setDsbrsMthdCd(String dsbrsMthdCd) {
        this.dsbrsMthdCd = dsbrsMthdCd;
    }

    public String getFrDd() {
        return frDd;
    }

    public void setFrDd(String frDd) {
        this.frDd = frDd;
    }

    public String getEndDd() {
        return endDd;
    }

    public void setEndDd(String endDd) {
        this.endDd = endDd;
    }

    public String getCrdcCd() {
        return crdcCd;
    }

    public void setCrdcCd(String crdcCd) {
        this.crdcCd = crdcCd;
    }

    public String getCrdcNm() {
        return crdcNm;
    }

    public void setCrdcNm(String crdcNm) {
        this.crdcNm = crdcNm;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}
