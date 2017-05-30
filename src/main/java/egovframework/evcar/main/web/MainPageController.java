package egovframework.evcar.main.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dongguk on 2017-05-30.
 */
@Controller
public class MainPageController {

    @RequestMapping(value = "/main.mdo")
    public String view(){

        return "egovframework/evcar/main";
    }

}
