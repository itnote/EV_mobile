package egovframework.app.push.dao;

import egovframework.app.push.vo.PushVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jodongguk on 2017-08-02.
 */
@Repository("PushDAO")
public class PushDAO extends EgovComAbstractDAO {

    public int checkPush(PushVO vo) {
        return (Integer) select("PushDAO.checkPush", vo);
    }

    public void insertPush(PushVO vo) {
        insert("PushDAO.insertPush", vo);
    }

    public void updatePush(PushVO vo) {
        update("PushDAO.updatePush", vo);
    }

    public List<PushVO> selectPushUser(PushVO vo) {
        return (List<PushVO>) list("PushDAO.selectPushUser", vo);
    }

    public void insertPushMessage(PushVO vo){
        insert("PushDAO.insertPushMessage", vo);
    }

}
