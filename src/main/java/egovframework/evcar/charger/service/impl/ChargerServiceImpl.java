package egovframework.evcar.charger.service.impl;

import egovframework.evcar.charger.dao.ChargerDAO;
import egovframework.evcar.charger.service.ChargerService;
import egovframework.evcar.charger.vo.StationInfoVO;
import egovframework.evcar.common.vo.BaseVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dongguk on 2017-06-01.
 */

@Service("ChargerService")
public class ChargerServiceImpl implements ChargerService {

    @Resource(name="ChargerDAO")
    private ChargerDAO chargerDAO;


    @Override
    public List<StationInfoVO> selectDistanceStationList(BaseVO baseVO) {
        return chargerDAO.selectDistanceStationList(baseVO);
    }

    @Override
    public List<?> stationInfo(BaseVO baseVO) throws Exception {
        return chargerDAO.stationInfo(baseVO);
    }


}
