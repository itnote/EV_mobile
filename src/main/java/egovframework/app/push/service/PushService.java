package egovframework.app.push.service;

import egovframework.app.push.vo.PushVO;

/**
 * Created by jodongguk on 2017-08-02.
 */
public interface PushService {

    /**
     * PUSH 사용자 등록
     * @param vo
     * @return
     * @throws Exception
     */
    String insertPush(PushVO vo) throws Exception;

    /**
     * PUSH 발송
     * @param vo
     * @param msg
     * @throws Exception
     */
    void sendPush(PushVO vo, String msg) throws Exception;
}
