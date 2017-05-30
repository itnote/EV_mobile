package egovframework.evcar.historySearch;

import egovframework.evcar.search.vo.SearchVO;

import java.util.List;

/**
 * Created by Doum on 2017-05-30.
 */
public interface HistorySearchService {

    /** 이력조회 화면 카운터  **/
    public int selectHistorySearchCount(SearchVO vo);

    /** 이력조회 화면 리스트 **/
    public List<HistorySearchVO> selectHistorySearchList(SearchVO vo);
}
