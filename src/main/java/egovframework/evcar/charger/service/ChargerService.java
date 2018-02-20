package egovframework.evcar.charger.service;

import egovframework.evcar.common.vo.BaseVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

import java.util.List;

public interface ChargerService {

    public List<EgovMap> selectDistanceStationList(BaseVO baseVO);

    public List<EgovMap> stationInfo(BaseVO baseVO) throws Exception;

    List<EgovMap> getId(String id);
}
