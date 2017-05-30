package egovframework.evcar.alert;

import egovframework.evcar.alert.dao.AlertDAO;
import egovframework.evcar.search.vo.SearchVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Doum on 2017-05-30.
 */
@Service("AlertService")
public class AlertServiceImpl implements AlertService{

    @Resource(name = "AlertDAO")
    private AlertDAO alertDAO;

    @Override
    public int selectAlertCount(SearchVO vo) {
        return alertDAO.selectAlertCount(vo);
    }

    @Override
    public List<AlertVO> selectAlertList(SearchVO vo) {
        return alertDAO.selectAlertList(vo);
    }
}
