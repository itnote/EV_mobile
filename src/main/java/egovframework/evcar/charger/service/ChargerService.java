package egovframework.evcar.charger.service;

import egovframework.evcar.charger.vo.StationInfoVO;
import egovframework.evcar.common.vo.BaseVO;

import java.util.List;

/**
 * Created by dongguk on 2017-06-01.
 */
public interface ChargerService {

    public List<StationInfoVO> selectDistanceStationList(BaseVO baseVO);
}
