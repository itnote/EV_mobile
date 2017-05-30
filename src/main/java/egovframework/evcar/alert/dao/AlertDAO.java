package egovframework.evcar.alert.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.evcar.alert.AlertVO;
import egovframework.evcar.search.vo.SearchVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Doum on 2017-05-30.
 */
@Repository("AlertDAO")
public class AlertDAO extends EgovComAbstractDAO{

    /** 알림 화면 카운터 **/
    public int selectAlertCount(SearchVO vo) {
        Object value = this.getSqlMapClientTemplate().queryForObject("AlertDAO.selectAlertCount", vo);
        return value == null ? 1 : ((Integer) value).intValue();
    }
    /** 알림 화면 리스트 **/
    public List<AlertVO> selectAlertList(SearchVO vo){
        return (List<AlertVO>) list("AlertDAO.selectAlertList", vo);
    }
}
