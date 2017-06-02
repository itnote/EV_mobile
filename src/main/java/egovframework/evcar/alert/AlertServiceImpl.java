package egovframework.evcar.alert;

import egovframework.evcar.alert.dao.AlertDAO;
import egovframework.evcar.common.vo.BaseVO;
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
    public int selectAlertCount(BaseVO vo) {
        return alertDAO.selectAlertCount(vo);
    }

    @Override
    public List<AlertVO> selectAlertList(BaseVO vo) {
        return alertDAO.selectAlertList(vo);
    }
}
