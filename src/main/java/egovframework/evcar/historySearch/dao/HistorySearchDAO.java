package egovframework.evcar.historySearch.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.evcar.common.vo.BaseVO;
import egovframework.evcar.historySearch.HistorySearchVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Doum on 2017-05-30.
 */
@Repository("HistorySearchDAO")
public class HistorySearchDAO extends EgovComAbstractDAO {

    /** 이력조회 화면 카운터 **/
    public int selectHistorySearchCount(BaseVO vo) {
        Object value = this.getSqlMapClientTemplate().queryForObject("HistorySearchDAO.selectHistorySearchCount", vo);
        return value == null ? 1 : ((Integer) value).intValue();
    }
    /** 이력조회 화면 리스트 **/
    public List<HistorySearchVO> selectHistorySearchList(BaseVO vo){
        return (List<HistorySearchVO>) list("HistorySearchDAO.selectHistorySearchList", vo);
    }
    /** 이력조회 화면 상세 **/
    public HistorySearchVO ViewHistorySearch(HistorySearchVO historySearchVO){
        return (HistorySearchVO) selectByPk("HistorySearchDAO.ViewHistorySearch", historySearchVO);
    }
}
