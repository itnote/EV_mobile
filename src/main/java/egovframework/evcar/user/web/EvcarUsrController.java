package egovframework.evcar.user.web;

import egovframework.app.push.service.PushService;
import egovframework.app.push.vo.PushVO;
import egovframework.evcar.common.BaseController;
import egovframework.evcar.user.service.EvcarUsrService;
import egovframework.evcar.user.vo.EvcarUsrVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 사용자
 * Created by dongguk on 2017-05-30.
 */
@Controller
@RequestMapping(value = "/user")
public class EvcarUsrController extends BaseController {

    @Resource(name="EvcarUserService")
    private EvcarUsrService evcarUsrService;

    @Resource(name="PushService")
    private PushService pushService;

    /**
     * 사용자 로그인 페이지
     * @param model
     * @return
     */
    @RequestMapping(value = "/login.mdo")
    public String loginView(ModelMap model) {
        return "egovframework/evcar/user/login";
    }

    /**
     * 사용자 로그인 처리 GET
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/loginAct.mdo", method = RequestMethod.GET)
    public String loginAct(HttpServletRequest request, ModelMap model) throws Exception {
        model.addAttribute("message", egovMessageSource.getMessage("fail.request.msg"));
        return "egovframework/evcar/user/login";
    }

    /**
     * 사용자 로그인 처리 POST
     * @param request
     * @param model
     * @param evcarUsrVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/loginAct.mdo", method = RequestMethod.POST)
    public String loginAct(HttpServletRequest request, ModelMap model, EvcarUsrVO evcarUsrVO) throws Exception {

        EvcarUsrVO resultVO = this.evcarUsrService.loginAction(evcarUsrVO);

        if (resultVO != null && resultVO.getUsrId() != null && !resultVO.getUsrPwd().equals("")) {

            String pushType = request.getParameter("pushType");
            String pushKey = request.getParameter("pushKey");

            if(StringUtils.isNotEmpty(pushType)&& StringUtils.isNotEmpty(pushKey))
                pushService.insertPush(new PushVO(request));

            // 2-1. 로그인 정보를 세션에 저장
            request.getSession().setAttribute("loginVO", resultVO);

            return "redirect:/main.mdo";

        } else {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "egovframework/evcar/user/login";
        }
    }

    /**
     * 사용자 로그아웃
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/logout.mdo")
    public String logoutAct(HttpServletRequest request, ModelMap model) throws Exception {

        request.getSession().setAttribute("loginVO", null);
        return "redirect:/main.mdo";
    }

    /**
     * 사용자 회원가입 페이지
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/join.mdo")
    public String joinView(HttpServletRequest request, ModelMap model){

        return "egovframework/evcar/user/join";
    }

    /**
     * 사용자 회원가입 처리 GET
     * @param request
     * @param model
     * @param evcarUsrVO
     * @return
     */
    @RequestMapping(value = "/joinAct.mdo", method = RequestMethod.GET)
    public String joinAct(HttpServletRequest request, ModelMap model, EvcarUsrVO evcarUsrVO) {

        return "egovframework/evcar/user/join";
    }

    /***
     * 사용자 회원가입 처리 POST
     * @param request
     * @param model
     * @param evcarUsrVO
     * @param redirectAttr
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/joinAct.mdo", method = RequestMethod.POST)
    public String joinAct(HttpServletRequest request, ModelMap model, EvcarUsrVO evcarUsrVO, RedirectAttributes redirectAttr) throws Exception {

        evcarUsrService.joinUserData(evcarUsrVO);

        redirectAttr.addFlashAttribute("message", egovMessageSource.getMessage("cop.request.msg"));
        return "redirect:/user/login.mdo";
    }

}
