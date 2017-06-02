package egovframework.evcar.common;


import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.evcar.user.vo.EvcarUsrVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    protected PaginationInfo getPaginationInfo(ComDefaultVO vo, int totalPage) {
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
}
