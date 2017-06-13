package egovframework.app.push.web;

import egovframework.evcar.common.BaseController;
import egovframework.evcar.common.vo.BaseVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by itnote on 2017. 6. 12..
 */
@Controller
@RequestMapping({ "/app/push" })
public class PushController extends BaseController {


    @RequestMapping({ "/insertPush.do" })
    public @ResponseBody
   String  stationInfoList(BaseVO baseVO) throws Exception{
        return "1";
    }


}
