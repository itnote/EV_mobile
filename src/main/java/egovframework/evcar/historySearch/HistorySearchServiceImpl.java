package egovframework.evcar.historySearch;

import egovframework.evcar.historySearch.dao.HistorySearchDAO;
import egovframework.evcar.search.vo.SearchVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Doum on 2017-05-30.
 */
@Service("HistorySearchService")
public class HistorySearchServiceImpl implements HistorySearchService{

    @Resource(name = "HistorySearchDAO")
    private HistorySearchDAO historySearchDAO;

    @Override
    public int selectHistorySearchCount(SearchVO vo) {
        return historySearchDAO.selectHistorySearchCount(vo);
    }

    @Override
    public List<HistorySearchVO> selectHistorySearchList(SearchVO vo) {
        return historySearchDAO.selectHistorySearchList(vo);
    }
}
