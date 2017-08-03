package egovframework.app.push.web;

import egovframework.app.push.service.PushService;
import egovframework.app.push.vo.PushVO;
import egovframework.evcar.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by itnote on 2017. 6. 12..
 */
@Controller
@RequestMapping({ "/app/push" })
public class PushController extends BaseController {

    @Resource(name="PushService")
    private PushService pushService;

    /**
     * PUSH 사용자정보 등록
     * @param vo [  regId: 디바이스고유번호
     *            , userId: 사용자 고유 아이디
     *            , phoneType: 디바이스타입 ANDROID / IOS
     *            , handPhone: 휴대전화번호
     *            ]
     * @return
     * @throws Exception
     */
    @RequestMapping({ "/insertPush.do" })
    public @ResponseBody String insertPush(PushVO vo) throws Exception {

//        System.out.println("vo:" + vo.toString());
//        return pushService.insertPush(vo);
        return "";
    }

    @RequestMapping({ "/insert.do" })
    public @ResponseBody String insert(PushVO vo) throws Exception {

//        System.out.println("vo:" + vo.toString());
//        return pushService.insertPush(vo);
        return "";
    }

    /**
     * PUSH 발송
     * @param vo
     * @param message
     * @return
     * @throws Exception
     */
    @RequestMapping({"/send.do"})
    public @ResponseBody String sendPush(PushVO vo, @RequestParam String message) throws Exception {
        pushService.sendPush(vo, message);
        return "";
    }

}