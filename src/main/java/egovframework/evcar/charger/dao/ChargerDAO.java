package egovframework.evcar.charger.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.evcar.card.vo.UsrCardVO;
import egovframework.evcar.charger.vo.StationInfoVO;
import egovframework.evcar.common.vo.BaseVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dongguk on 2017-05-30.
 */
@Repository("ChargerDAO")
public class ChargerDAO extends EgovComAbstractDAO {

    /**
     * 근처충전기 위치 조회
     * 기본값 500m / 현재 위치 값 필요.
     * @param baseVO
     * @return
     */
    public List<StationInfoVO> selectDistanceStationList(BaseVO baseVO) {
        return (List<StationInfoVO>) list("ChargerDAO.selectDistanceStationList",baseVO);
    }

    /**
     * 충전기 상세조회
     * @param baseVO
     * @return
     */
    public List<?> stationInfo(BaseVO baseVO) {
        return list("ChargerDAO.stationInfo", baseVO);
    }

}
