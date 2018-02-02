package egovframework.evcar.user.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.evcar.card.vo.UsrCardVO;
import egovframework.evcar.user.vo.EvcarUsrVO;
import org.springframework.stereotype.Repository;

/**
 * Created by dongguk on 2017-05-30.
 */
@Repository("EvcarUsrDAO")
public class EvcarUsrDAO extends EgovComAbstractDAO {

    public EvcarUsrVO loginAction(EvcarUsrVO evcarUsrVO) {
        return (EvcarUsrVO) select("EvcarUsrDAO.loginAction", evcarUsrVO);
    }

    public void joinUserData(EvcarUsrVO evcarUsrVO) {
        insert("EvcarUsrDAO.joinUserData", evcarUsrVO);
    }

    public void acrdUserData(EvcarUsrVO evcarUsrVO) {
        insert("EvcarUsrDAO.acrdUserData", evcarUsrVO);
    }

    public void updateUsrCard(UsrCardVO vo) {
        update("EvcarUsrDAO.updateUsrCard", vo);
    }

    public int IdCheck(EvcarUsrVO vo){
        Object value = this.getSqlMapClientTemplate().queryForObject("EvcarUsrDAO.IdCheck", vo);
        return value == null ? 1 : ((Integer) value).intValue();
    }
    public int CardCheck(EvcarUsrVO vo){
        Object value = this.getSqlMapClientTemplate().queryForObject("EvcarUsrDAO.CardCheck", vo);
        return value == null ? 1 : ((Integer) value).intValue();
    }
}
