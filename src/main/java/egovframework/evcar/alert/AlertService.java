package egovframework.evcar.alert;

import egovframework.evcar.search.vo.SearchVO;

import java.util.List;

/**
 * Created by Doum on 2017-05-30.
 */
public interface AlertService {

    /** 알림 화면 카운터  **/
    public int selectAlertCount(SearchVO vo);

    /** 알림 화면 리스트 **/
    public List<AlertVO> selectAlertList(SearchVO vo);
}
