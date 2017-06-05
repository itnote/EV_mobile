package egovframework.evcar.common;


import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.evcar.common.vo.BaseVO;
import egovframework.evcar.user.vo.EvcarUsrVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dongguk on 2017-05-30.
 */
@Component
public class BaseController {

    @Resource(name = "egovMessageSource")
    protected EgovMessageSource egovMessageSource;

    @Autowired
    private HttpServletRequest request;

    /**
     * 브라우저타입
     *
     * @return
     */
    @ModelAttribute("browser")
    public String getBrowser() {
        String agent = request.getHeader("User-Agent");
        String browser = "";
        if (agent != null && agent.indexOf("MSIE") > -1) {
            browser = "M";
        } else {
            browser = "N";
        }
        return browser;
    }

    /**
     * 세션에서 로그인 정보를 불러온다
     *
     * @return loginVO
     */
    @ModelAttribute("loginVO")
    public EvcarUsrVO getLoginUserVO() {
        EvcarUsrVO loginVO = (EvcarUsrVO) request.getSession().getAttribute("loginVO");
        return loginVO;
    }


    /**
     * PAGING 처리
     *
     * @param vo
     * @param totalPage
     * @return
     */
    protected PaginationInfo getPaginationInfo(BaseVO vo, int totalPage) {
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(vo.getPageIndex());
        paginationInfo.setRecordCountPerPage(vo.getRecordCountPerPage());
        paginationInfo.setPageSize(vo.getPageSize());
        paginationInfo.setTotalRecordCount(totalPage);

        vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
        vo.setLastIndex(paginationInfo.getLastRecordIndex());
        vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        if (paginationInfo.getCurrentPageNo() > paginationInfo.getTotalPageCount()) {
            vo.setPageIndex(paginationInfo.getTotalPageCount());
            paginationInfo.setCurrentPageNo(paginationInfo.getTotalPageCount());
            vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
        }

        return paginationInfo;
    }


    /**
     * 파라미터값 추출 null 일시 빈문자열로 치환
     * @param name
     * @return
     */
    protected String getParamToString(String name){
        String result = request.getParameter(name)==null?"":request.getParameter(name);
        return result;
    }


    /**
     * 기준날짜에서 몇일 전,후의 날짜를 구한다.
     * @param	sourceTS	기준날짜
     * @param	day			변경할 일수
     * @return	기준날짜에서 입력한 일수를 계산한 날짜
     */
    protected static Timestamp getTimestampWithSpan(Timestamp sourceTS, long day)
            throws Exception {
        Timestamp targetTS = null;

        if (sourceTS != null) {
            targetTS = new Timestamp(sourceTS.getTime()
                    + (day * 1000 * 60 * 60 * 24));
        }
        return targetTS;
    }

    /**
     * 현재날짜를 YYYYMMDDHHMMSS로 리턴
     */
    protected final synchronized String getyyyyMMddHHmmss() {
        /** yyyyMMddHHmmss Date Format */
        SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");

        return yyyyMMddHHmmss.format(new Date());
    }

    /**
     * <pre>
     * MD5+Base64
     * </pre>
     * @param str
     * @return String
     */
    protected static final String encodeMD5Base64(String str) {
        return new String(Base64.encodeBase64(DigestUtils.md5(str)));
    }

    protected static final String encodeMD5HexBase64(String pw) {
        return new String(Base64.encodeBase64(DigestUtils.md5Hex(pw).getBytes()));
    }
}
