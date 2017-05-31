package egovframework.evcar.bbs.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.evcar.bbs.BbsVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Doum on 2017-05-31.
 */
@Repository("BbsDAO")
public class BbsDAO extends EgovComAbstractDAO {

    /** 게시판 화면 카운터 **/
    public int selectBbsCount(BbsVO vo) {
        Object value = this.getSqlMapClientTemplate().queryForObject("BbsDAO.selectBbsCount", vo);
        return value == null ? 1 : ((Integer) value).intValue();
    }
    /** 게시판 화면 리스트 **/
    public List<BbsVO> selectBbsList(BbsVO vo){
        return (List<BbsVO>) list("BbsDAO.selectBbsList", vo);
    }

    /** 게시판 상세 화면 **/
    public BbsVO ViewBbs(BbsVO bbsVO){
        return (BbsVO) selectByPk("BbsDAO.ViewBbs", bbsVO);
    }
}
