package egovframework.evcar.card.vo;

import egovframework.evcar.user.vo.EvcarUsrVO; /**
 * Created by doum on 2017-12-12.
 */
public class UsrCardVO {

    private String acrdCrdNo;
    private String usrSno;
    private String cardCd;
    private String cardSno;
    private String useYn;
    private String cardRegYear;
    private String cardRegMonth;
    private String cardRegYmd;
    private String resPayYmd;
    private String fnCode;
    private String fnName;
    private String bilKey;

    public UsrCardVO(){}

    public UsrCardVO(EvcarUsrVO loginVO) {
        this.acrdCrdNo = loginVO.getAcrdCrdNo();
    }

    public String getUsrSno() {
        return usrSno;
    }

    public void setUsrSno(String usrSno) {
        this.usrSno = usrSno;
    }

    public String getAcrdCrdNo() {
        return acrdCrdNo;
    }

    public void setAcrdCrdNo(String acrdCrdNo) {
        this.acrdCrdNo = acrdCrdNo;
    }

    public String getCardCd() {
        return cardCd;
    }

    public void setCardCd(String cardCd) {
        this.cardCd = cardCd;
    }

    public String getCardSno() {
        return cardSno;
    }

    public void setCardSno(String cardSno) {
        this.cardSno = cardSno;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getCardRegYear() {
        return cardRegYear;
    }

    public void setCardRegYear(String cardRegYear) {
        this.cardRegYear = cardRegYear;
    }

    public String getCardRegMonth() {
        return cardRegMonth;
    }

    public void setCardRegMonth(String cardRegMonth) {
        this.cardRegMonth = cardRegMonth;
    }

    public String getCardRegYmd() {
        return cardRegYmd;
    }

    public void setCardRegYmd(String cardRegYmd) {
        this.cardRegYmd = cardRegYmd;
    }

    public String getResPayYmd() {
        return resPayYmd;
    }

    public void setResPayYmd(String resPayYmd) {
        this.resPayYmd = resPayYmd;
    }

    public String getFnCode() {
        return fnCode;
    }

    public void setFnCode(String fnCode) {
        this.fnCode = fnCode;
    }

    public String getFnName() {
        return fnName;
    }

    public void setFnName(String fnName) {
        this.fnName = fnName;
    }

    public String getBilKey() {
        return bilKey;
    }

    public void setBilKey(String bilKey) {
        this.bilKey = bilKey;
    }
}
