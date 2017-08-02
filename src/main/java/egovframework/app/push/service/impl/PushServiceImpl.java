package egovframework.app.push.service.impl;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import egovframework.app.push.dao.PushDAO;
import egovframework.app.push.service.PushService;
import egovframework.app.push.vo.PushVO;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.evcar.card.web.CardController;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by jodongguk on 2017-08-02.
 */
@Service("PushService")
public class PushServiceImpl implements PushService {

    private static final Logger logger = LoggerFactory.getLogger(CardController.class);

    @Resource(name = "pushKeyIdGnrService")
    private EgovIdGnrService pushKeyIdGnrService;

    @Resource(name = "PushDAO")
    private PushDAO pushDAO;

    @Override
    public String insertPush(PushVO vo) throws Exception {

        String regId = vo.getRegId();

        String key = "0";
        if(regId != null && !"".equals(regId) && pushDAO.checkPush(vo) < 1) {
            key = pushKeyIdGnrService.getNextStringId();
            vo.setPushSq(key);
            pushDAO.insertPush(vo);
        }
        return key;
    }

    @Override
    public void sendPush(PushVO vo, String msg) throws Exception {

        List<PushVO> list = pushDAO.selectPushUser(vo);
        String androidPushKey = EgovProperties.getProperty("androidPushKey");
        msg = URLEncoder.encode(msg, "UTF-8");

        for(PushVO pvo : list) {

            if("ANDROID".equals(pvo.getPhoneType())) {
                logger.debug("PUSH ANDROID");
                String MESSAGE_ID = String.valueOf(Math.random() % 100 + 1);	//메시지 고유 ID
                boolean SHOW_ON_IDLE = false;	//기기가 활성화 상태일때 보여줄것인지
                int LIVE_TIME = 1;	//기기가 비활성화 상태일때 GCM가 메시지를 유효화하는 시간
                int RETRY = 2;	//메시지 전송실패시 재시도 횟수

                Sender sender = new Sender(androidPushKey);
                Message message = new Message.Builder().collapseKey(MESSAGE_ID)
                        .delayWhileIdle(SHOW_ON_IDLE)
                        .timeToLive(LIVE_TIME)

                        .addData("key",pvo.getPushSq())
                        .addData("msg",msg)
                        //.addData("param1",nullTrim(param1))
                        //.addData("param2",nullTrim(param2))
                        //.addData("pushType",nullTrim(pushType))
                        .build();

                Result result = sender.send(message,pvo.getRegId(),RETRY);

            }else if("IOS".equals(pvo.getPhoneType())){
                logger.debug("PUSH IOS");
            }

        }

    }

    private String nullTrim(String str) {
        if ("null".equals(str)) {
            str = "";
        } else if (str == null) {
            str = "";
        }
        return str;
    }
}
