package egovframework.evcar.card.web;

import egovframework.evcar.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dongguk on 2017-06-01.
 */
@RequestMapping("/card")
@Controller
public class CardController extends BaseController {

    /**
     * 카드결제정보
     * @param request
     * @return
     */
    @RequestMapping("/status.mdo")
    public String listView(HttpServletRequest request){
        return "egovframework/evcar/card/status";
    }


    @RequestMapping("/register.mdo")
    public String registerView(){
        return "egovframework/evcar/card/register";
    }

}
