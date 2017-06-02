package egovframework.evcar.bbs.web;

import egovframework.com.cmm.SessionVO;
import egovframework.evcar.bbs.BbsService;
import egovframework.evcar.bbs.BbsVO;
import egovframework.evcar.common.BaseController;
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
 * Created by Doum on 2017-05-31.
 */
@Controller
@SessionAttributes(types = { SessionVO.class })
@RequestMapping({ "/evcar/bbs" })
public class BbsController  extends BaseController {

    @Resource(name = "BbsService")
    private BbsService bbsService;

    /**
     * 게시판 리스트\화면
     * @param request
     * @param response
     * @param model
     * @param vo
     * @return
     * @throws Exception
     */
    @RequestMapping({ "/list.*" })
    public String bbsList(HttpServletRequest request, HttpServletResponse response, ModelMap model,
                              @ModelAttribute("BbsVO") BbsVO vo) throws Exception{
        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(vo.getPageIndex());
        paginationInfo.setRecordCountPerPage(vo.getPageUnit());
        paginationInfo.setPageSize(vo.getPageSize());
        vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
        vo.setLastIndex(paginationInfo.getLastRecordIndex());
        vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        int totCnt = bbsService.selectBbsCount(vo);
        List<BbsVO> BbsList = bbsService.selectBbsList(vo);

        paginationInfo.setTotalRecordCount(totCnt);

        if (paginationInfo.getCurrentPageNo() > paginationInfo.getTotalPageCount()) {
            vo.setPageIndex(paginationInfo.getTotalPageCount());
            paginationInfo.setCurrentPageNo(paginationInfo.getTotalPageCount());
            vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
        }
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("BbsList", BbsList);
        model.addAttribute("BbsVO", vo);

        return "egovframework/evcar/bbs/list";
    }
    /**
     * 게시판 상세화면
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping({ "/info.*" })
    public String bbsView(HttpServletRequest request, HttpServletResponse response, ModelMap model,
                              @ModelAttribute("BbsVO") BbsVO bbsVO) throws Exception {
        // TODO: 페이징 개선필요
        int pageIndex = bbsVO.getPageIndex();
        bbsVO = bbsService.ViewBbs(bbsVO);
        bbsVO.setPageIndex(pageIndex);

        model.addAttribute("BbsVO",bbsVO);

        return "egovframework/evcar/bbs/info";
    }
}
