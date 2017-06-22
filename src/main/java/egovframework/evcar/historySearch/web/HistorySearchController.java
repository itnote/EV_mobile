package egovframework.evcar.historySearch.web;

import egovframework.com.cmm.SessionVO;
import egovframework.evcar.common.BaseController;
import egovframework.evcar.common.vo.BaseVO;
import egovframework.evcar.historySearch.HistorySearchService;
import egovframework.evcar.historySearch.HistorySearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Doum on 2017-05-30.
 */
@Controller
@SessionAttributes(types = { SessionVO.class })
@RequestMapping({ "/evcar/historySearch" })
public class HistorySearchController  extends BaseController {

    @Resource(name = "HistorySearchService")
    private HistorySearchService historySearchService;

    /**
     * 이력조회 리스트\화면
     * @param request
     * @param response
     * @param model
     * @param vo
     * @return
     * @throws Exception
     */
    @RequestMapping({ "/list.*" })
    public String HistoryList(HttpServletRequest request, HttpServletResponse response, ModelMap model,
                            @ModelAttribute("SearchVO") BaseVO vo) throws Exception{
        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(vo.getPageIndex());
        paginationInfo.setRecordCountPerPage(vo.getPageUnit());
        paginationInfo.setPageSize(vo.getPageSize());
        vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
        vo.setLastIndex(paginationInfo.getLastRecordIndex());
        vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        int totCnt = historySearchService.selectHistorySearchCount(vo);
        List<HistorySearchVO> historySearchList = historySearchService.selectHistorySearchList(vo);

        paginationInfo.setTotalRecordCount(totCnt);

        if (paginationInfo.getCurrentPageNo() > paginationInfo.getTotalPageCount()) {
            vo.setPageIndex(paginationInfo.getTotalPageCount());
            paginationInfo.setCurrentPageNo(paginationInfo.getTotalPageCount());
            vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
        }
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("historySearchList", historySearchList);
        model.addAttribute("SearchVO", vo);


        // Start time
        long startTime = System.currentTimeMillis();
        System.out.println("====================================");
        System.out.println("시작=====================>"+startTime);
        // 수행작업
        // End time
        long endTime = System.currentTimeMillis();
        System.out.println("종료=====================>"+endTime);
        // Total time
        long lTime = endTime - startTime;
        System.out.println("TIME : " + lTime + "(ms)");
        System.out.println("====================================");

        return "egovframework/evcar/historySearch/list";
    }
    /**
     * 이력조회 상세화면
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping({ "/info.*" })
    public String HistoryView(HttpServletRequest request, HttpServletResponse response, ModelMap model,
                              @ModelAttribute("HistorySearchVO") HistorySearchVO historySearchVO) throws Exception {

        historySearchVO = historySearchService.ViewHistorySearch(historySearchVO);

        model.addAttribute("HistorySearchVO",historySearchVO);

        return "egovframework/evcar/historySearch/historyInfo/info";
    }
}
