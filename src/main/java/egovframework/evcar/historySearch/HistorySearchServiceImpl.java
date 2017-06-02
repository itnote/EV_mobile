package egovframework.evcar.historySearch;

import egovframework.evcar.common.vo.BaseVO;
import egovframework.evcar.historySearch.dao.HistorySearchDAO;
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
    public int selectHistorySearchCount(BaseVO vo) {
        return historySearchDAO.selectHistorySearchCount(vo);
    }

    @Override
    public List<HistorySearchVO> selectHistorySearchList(BaseVO vo) {
        return historySearchDAO.selectHistorySearchList(vo);
    }
    public HistorySearchVO ViewHistorySearch(HistorySearchVO historySearchVO) {
        return historySearchDAO.ViewHistorySearch(historySearchVO);
    }
}
