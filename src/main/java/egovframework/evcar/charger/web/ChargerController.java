package egovframework.evcar.charger.web;

import egovframework.evcar.charger.service.ChargerService;
import egovframework.evcar.charger.vo.StationInfoVO;
import egovframework.evcar.common.BaseController;
import egovframework.evcar.common.annotation.LoginCheck;
import egovframework.evcar.common.vo.BaseVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dongguk on 2017-06-01.
 */
@Controller
public class ChargerController extends BaseController {

    @Resource(name = "ChargerService")
    private ChargerService chargerService;

    /**
     * 충전기위치 페이지
     * @return
     */
    @RequestMapping("/charger/station.mdo")
    public String stationView(){
        return "egovframework/evcar/charger/station";
    }

    @RequestMapping("/ajax/charger/station.mdo")
    public @ResponseBody List<StationInfoVO> stationList(BaseVO baseVO) throws Exception {
        return chargerService.selectDistanceStationList(baseVO);
    }

    @RequestMapping("/ajax/charger/stationInfo.mdo")
    public @ResponseBody List<EgovMap> stationInfo(BaseVO baseVO) throws Exception {
        return chargerService.stationInfo(baseVO);
    }

}
