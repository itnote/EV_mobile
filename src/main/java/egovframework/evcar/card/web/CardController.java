package egovframework.evcar.card.web;

import egovframework.evcar.card.service.CardService;
import egovframework.evcar.card.vo.UsrCardVO;
import egovframework.evcar.common.BaseController;
import egovframework.evcar.common.annotation.LoginCheck;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by dongguk on 2017-06-01.
 */
@RequestMapping("/card")
@Controller
public class CardController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CardController.class);

    @Resource(name = "smartroKeyIdGnrService")
    private EgovIdGnrService smartroKeyIdGnrService;

    @Resource(name = "CardService")
    private CardService cardService;

    /**
     * 카드 결제 정보
     * @param request
     * @return
     */
    @LoginCheck
    @RequestMapping("/status.mdo")
    public String listView(HttpServletRequest request){

        return "egovframework/evcar/card/status";
    }


    /**
     * 카드 등록페이지
     * @return
     */
    @LoginCheck
    @RequestMapping("/register.mdo")
    public String registerView(HttpServletRequest request, ModelMap model) throws Exception{

        model.addAttribute("smartroKey", smartroKeyIdGnrService.getNextStringId());
        return "egovframework/evcar/card/mainMobileBillPay";
    }


    /**
     * 결제결과
     * @param request
     * @return
     */
    @LoginCheck
    @RequestMapping("/returnBillPay.mdo")
    public String returnBillPay(HttpServletRequest request) throws Exception {

        String PayMethod		= getParamToString("PayMethod");
        String MID				= getParamToString("MID");
        String Amt				= getParamToString("Amt");
        String BuyerName		= getParamToString("BuyerName");    // 구매자명
        String GoodsName		= getParamToString("GoodsName");
        String mallUserID       = getParamToString("mallUserID");   // 구매자ID
        String TID              = getParamToString("TID");
        String Moid				= getParamToString("OID");
        String AuthDate			= getParamToString("AuthDate");
        String AuthCode			= getParamToString("AuthCode");
        String ResultCode		= getParamToString("ResultCode");
        String ResultMsg		= getParamToString("ResultMsg");
        String MallReserved     = getParamToString("MallReserved");

        String fn_cd			= getParamToString("fn_cd");        // 카드사 코드
        String fn_name			= getParamToString("fn_name");      // 카드사 명
        String CardNum			= getParamToString("CardNum");      // 카드사 명

        String CardQuota		= getParamToString("CardQuota");
        String BuyerEmail		= getParamToString("BuyerEmail");
        String BuyerAuthNum		= getParamToString("BuyerAuthNum");
        String ReceiptType		= getParamToString("ReceiptType");
        String SignValue		= getParamToString("SignValue");
        String BillTid			= getParamToString("BillTid");
        String VerifyValue		= getParamToString("VerifyValue");
        String BillKeyMode     	= getParamToString("BillKeyMode");


        String merchantKey = "KiS8NWHjZ49FzG91HMI9hVXOSxYrvFBKzl2bYpr2ac7lg369iZxy0xhCJfg4juCuVH27mO/TQ4kG2qnjEr5Z4Q==";
        String VerifySignValue = encodeMD5HexBase64(TID.substring(0, 10) + ResultCode +TID.substring(10, 15) + merchantKey +TID.substring(15, TID.length()));
        String VerifyBillKValue ="";

        String DecryptBillTid="";
        if("0".equals(BillKeyMode)) {
            try{
                kr.co.smartro.xpg_pay.crypt cy = new kr.co.smartro.xpg_pay.crypt();
                DecryptBillTid = cy.xpgClientDecrypt(merchantKey,BillTid);

                logger.debug("BillTid: "+BillTid);
                logger.debug("MID: "+MID);
                logger.debug("merchantKey: "+merchantKey);
                logger.debug("AuthDate: "+AuthDate);
                logger.debug("AuthCode: "+AuthCode);
                logger.debug("Amt: "+Amt);
                logger.debug("DecryptBillTid: "+DecryptBillTid);

                VerifyBillKValue = encodeMD5Base64(MID+merchantKey+AuthDate+AuthCode+DecryptBillTid);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            VerifyBillKValue = encodeMD5Base64(MID+merchantKey+AuthDate+AuthCode+Amt+BillTid);
        }

        UsrCardVO usrCardVO = new UsrCardVO();
        usrCardVO.setUseYn("Y");
        usrCardVO.setCardCd("card");
        usrCardVO.setCardSno(CardNum);
        usrCardVO.setFnCode(fn_cd);
        usrCardVO.setFnName(fn_name);
        usrCardVO.setUsrSno(getLoginUserVO().getUsrSno());
        usrCardVO.setBilKey(BillTid);

        cardService.changeUsrCard(usrCardVO);

        return "redirect:/card/status.mdo";
    }

    @LoginCheck
    @RequestMapping("/inform.mdo")
    public String inform(HttpServletRequest request){
        return "egovframework/evcar/card/informMobile";
    }

    @LoginCheck
    @RequestMapping("/stopUrl.mdo")
    public String stopUrl(HttpServletRequest request){
        return "egovframework/evcar/card/stopUrl";
    }


}
