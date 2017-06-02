package egovframework.evcar.card.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.evcar.card.vo.UsrCardVO;
import org.springframework.stereotype.Repository;

/**
 * Created by dongguk on 2017-05-30.
 */
@Repository("CardDAO")
public class CardDAO extends EgovComAbstractDAO {

    public void insertUserCard(UsrCardVO usrCardVO) {
        insert("CardDAO.insertUserCard", usrCardVO);
    }

}
