package egovframework.evcar.card.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.evcar.card.vo.UsrCardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dongguk on 2017-05-30.
 */
@Repository("CardDAO")
public class CardDAO extends EgovComAbstractDAO {

    public List<UsrCardVO> selectUserCardList(UsrCardVO usrCardVO){
        return (List<UsrCardVO>) list("CardDAO.selectUserCardList", usrCardVO);
    }

    public int selectUserCardCheck(UsrCardVO usrCardVO){
        Object value = this.getSqlMapClientTemplate().queryForObject("CardDAO.selectUserCardCheck", usrCardVO);
        return value == null ? 1 : ((Integer) value).intValue();
    }
    public void updateUserCard(UsrCardVO usrCardVO) {
        update("CardDAO.updateUserCard", usrCardVO);
    }

    public void insertUserCard(UsrCardVO usrCardVO) {
        insert("CardDAO.insertUserCard", usrCardVO);
    }

}
