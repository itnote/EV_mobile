package egovframework.evcar.user.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.evcar.user.vo.EvcarUsrVO;
import egovframework.evcar.user.vo.UsrCardVO;
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

    public void insertUserCard(UsrCardVO usrCardVO) {
        insert("EvcarUsrDAO.insertUserCard", usrCardVO);
    }

}
