package egovframework.evcar.alert.web;

import egovframework.com.cmm.SessionVO;
import egovframework.evcar.alert.AlertService;
import egovframework.evcar.alert.AlertVO;
import egovframework.evcar.search.vo.SearchVO;
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
@RequestMapping({ "/evcar/alert" })
public class AlertController{

    @Resource(name = "AlertService")
    private AlertService alertService;

    /**
     * 알림 리스트\화면
     * @param request
     * @param response
     * @param model
     * @param vo
     * @return
     * @throws Exception
     */
    @RequestMapping({ "/list.*" })
    public String alertList(HttpServletRequest request, HttpServletResponse response, ModelMap model,
                              @ModelAttribute("SearchVO") SearchVO vo) throws Exception{
        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(vo.getPageIndex());
        paginationInfo.setRecordCountPerPage(vo.getPageUnit());
        paginationInfo.setPageSize(vo.getPageSize());
        vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
        vo.setLastIndex(paginationInfo.getLastRecordIndex());
        vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        int totCnt = alertService.selectAlertCount(vo);
        List<AlertVO> alertList = alertService.selectAlertList(vo);

        System.out.println("==========================================================================================");
        for (AlertVO vo1 :alertList) {
            System.out.println(vo1.toString());
        }

        System.out.println("==========================================================================================");

        paginationInfo.setTotalRecordCount(totCnt);

        if (paginationInfo.getCurrentPageNo() > paginationInfo.getTotalPageCount()) {
            vo.setPageIndex(paginationInfo.getTotalPageCount());
            paginationInfo.setCurrentPageNo(paginationInfo.getTotalPageCount());
            vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
        }
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("alertList", alertList);
        model.addAttribute("SearchVO", vo);

        return "egovframework/evcar/alert/list";
    }
}
