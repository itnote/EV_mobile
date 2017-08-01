package egovframework.evcar.card.web;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.evcar.card.service.CardService;
import egovframework.evcar.card.vo.UsrCardVO;
import egovframework.evcar.common.BaseController;
import egovframework.evcar.common.annotation.LoginCheck;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import kr.co.smartro.xpg_pay.urlsample.App;
import kr.co.smartro.xpg_pay.urlsample.Dto;
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
    public String listView(HttpServletRequest request, ModelMap model){

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

    @LoginCheck
    @RequestMapping("/chargePay.mdo")
    public  static  void sample( )
    {
        App app = new App();
        String UrlCall = "https://pay.smilepay.co.kr/billcard/billPayTrans.jsp"; //테스트 서버 URL
        //String UrlCall = "https://pay.smilepay.co.kr/billcard/billPayTrans.jsp"; //실서버 URL
        String sendMID = "anymobi02m";// 설정값
        String merchantKey = "eDVE2h7z8nlN1JmQjd22Cc9boNVNnDMbp5hak52loDjiVUBatMlGoA4o+04piNyJYZwOsZkqw4pqKaad3tUuOA=="; //  설정값
        String BL_TID = ""; //발급을 통해서 생성한 빌링키를 입력하시오
        try {

            Dto dtos = new  Dto();
            //아래 입력값에는 특수문자 포함 불가 ~`’:;{}[]<>,.!@#$%^&*()_+|\\/?
            //아래의 입력값중 값이 한글이라면 java.net.URLEncoder.encode("한글내용","EUC-KR")으로 "EUC-KR"로 변경하여 보내어 주세요.
            dtos.setPayMethod("CARDBILL");																			//지불수단 CARDBILL:신용카드
            dtos.setEdiDate(app.getyyyyMMddHHmmss());																//전문생성일시
            dtos.setGoodsCnt("01"); 																				// 결제 상품 품목 개수	 예 > 01
            dtos.setGoodsName(java.net.URLEncoder.encode("스마트로테스트","EUC-KR")); 								// GoodsName	char(80) 영문: 80자리 한글: 40자리
            dtos.setAmt("50000"); 																					// 거래금액 상품가격 char(12) ‘,’는 입력하지 않는다
            dtos.setMoid("0001"); 																					// 상품주문번호 char(64)  옆의 특수문자 포함 불가 ~`’:;{}[]<>,.!@#$%^&*()_+|\\/?
            dtos.setMID(sendMID); 																			    	// 상점아이디 char(10)  계약 시 부여된 Merchant ID. 테스트 시에는 임의로 부여된 테스트 아이디로 테스트 가능
            dtos.setBL_TID(BL_TID); 																				// 빌링키 암호화 하여 전송
            dtos.setCardQuota("03"); 																				// 할부개월 00 변경불가
            dtos.setChannelType("WBL"); 																			// 채널유형 char(3) WBL 변경불가
            dtos.setMallIP(""); 							   														// 상점서버IP char(20) 필수
            dtos.setMallReserved(""); 							   													// 상점예비정보 char(500) 한글 입력불가
            dtos.setMallUserID(""); 							    												// 회원사고객ID char(10)
            dtos.setBuyerName(java.net.URLEncoder.encode("곰돌이","EUC-KR")); 										// 구매자명 char(30)
            dtos.setBuyerTel("01010101010");																		// 구매자연락처 char(40)
            dtos.setBuyerEmail("test@smartro.co.kr");																// 구매자메일주소 char(60)
            dtos.setParentEmail("");																				// 보호자메일주소 char(60) 사용안함
            dtos.setBuyerAddr("");																					// 배송지주소 char(100)
            dtos.setBuyerPostNo("");																				// 우편번호 char(6)
            dtos.setUserIP("");																						// 회원사고객 IP char(20)  필수  가맹점의 서버 IP 입력요망
            dtos.setBillKeyMode("0");				    															// 빌링키 암호화 여부 IP char(1)  필수  0 = 암호화 1= 비 암호화 암호화는 권장사항입니다
            dtos.setCardPoint("0");                                                                                 // 카드 포인트 사용여부 0 = 미사용 1=사용(카드사와의 계약필수)
            dtos.setVERIFY_V(app.encodeMD5HexBase64(sendMID+merchantKey+dtos.getEdiDate()+dtos.getAmt()+BL_TID)); 	// 검증값 char(3) 필수 Base64(MD5(상점ID+상점KEY+전문전송일시(YYYYMMDDhhmmss)+ 결제금액+빌링키))
            if("0".equals(dtos.getBillKeyMode()))
            {
                kr.co.smartro.xpg_pay.crypt cy = new kr.co.smartro.xpg_pay.crypt();
                System.out.println("open_BL_TID:"+dtos.getBL_TID());
                try {
                    String merchantKeyget=cy.xpgClientEncrypt(merchantKey,dtos.getBL_TID());
                    System.out.println("merchantKeyget:"+ merchantKeyget);
                    String bufferBL_TID = app.UrlencodeBase64(merchantKeyget);
                    dtos.setBL_TID(bufferBL_TID);
                } catch (Exception e) {
                    System.out.println("xpgClientEncrypt Exception:"+ e);
                    throw new Exception("xpgClientEncrypt Exception:"+ e);
                }
                System.out.println("end_BL_TID:"+dtos.getBL_TID());
            }
            //서버 전송
            String result = app.UrlCall(UrlCall,dtos);
            System.out.println("결과값:"+result);

		    	/* 파싱 예제 */
            //아래 데이트를 이용하여 DB에 저장하시오 필요에 따라서 아래와 같이 decode 하시오 서버에서전송하는 값은 EUC-KR입니다
            // java.net.URLDecoder.decode("입력값","UTF-8")
            //java.net.URLDecoder.decode("입력값","EUC-KR")
            String SplitStr = "!@#";
            String[] resultArray = result.split(SplitStr);
            System.out.println("resultArray.length:"+resultArray.length);
            String PayMethod ="";
            String MID = "";
            String Amt = "";
            String Name = "";
            String GoodsName ="";
            String mallUserID ="";
            String TID = "";
            String OID ="";
            String AuthDate = "";
            String AuthCode = "";
            String ResultCode = "";
            String ResultMsg = "";
            String MallReserved = "";
            String fn_cd = "";
            String fn_name = "";
            String BuyerEmail = "";
            String VerifyValue = "";

            if(resultArray.length == 17)
            {
                PayMethod = resultArray[0];
                MID = resultArray[1];
                Amt = resultArray[2];
                Name = resultArray[3];
                GoodsName = resultArray[4];
                mallUserID = resultArray[5];
                TID = resultArray[6];
                OID = resultArray[7];
                AuthDate = resultArray[8];
                AuthCode = resultArray[9];
                ResultCode = resultArray[10];
                ResultMsg = resultArray[11];
                MallReserved = resultArray[12];
                fn_cd = resultArray[13];
                fn_name = resultArray[14];
                BuyerEmail = resultArray[15];
                VerifyValue = resultArray[16];
            }

            System.out.println("PayMethod:"+PayMethod);
            System.out.println("MID:"+MID);
            System.out.println("Amt:"+Amt);
            System.out.println("Name:"+Name);
            System.out.println("GoodsName:"+GoodsName);
            System.out.println("mallUserID:"+mallUserID);
            System.out.println("TID:"+TID);
            System.out.println("OID:"+OID);
            System.out.println("AuthDate:"+AuthDate);
            System.out.println("AuthCode:"+AuthCode);
            System.out.println("ResultCode:"+ResultCode); //정산 코드:3001
            System.out.println("ResultMsg:"+ResultMsg);
            System.out.println("MallReserved:"+MallReserved);
            System.out.println("fn_cd:"+fn_cd);
            System.out.println("fn_name:"+fn_name);
            System.out.println("BuyerEmail:"+BuyerEmail);
            System.out.println("VerifyValue:"+VerifyValue);
            System.out.println("VerifyValue:"+app.encodeMD5HexBase64(MID+merchantKey+AuthDate+AuthCode+Amt+BL_TID));

        } catch (Exception e) {
            System.out.println(" Exception:"+ e);
        }
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


        String merchantKey = EgovProperties.getProperty("EVCAR.PAY.MERCHANTKEY");
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
        return "redirect:/card/status.mdo";
        //return "egovframework/evcar/card/stopUrl";
    }


}
