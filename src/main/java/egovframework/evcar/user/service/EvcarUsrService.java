package egovframework.evcar.user.service;

import egovframework.evcar.user.vo.EvcarUsrVO;

/**
 * Created by dongguk on 2017-05-30.
 */
public interface EvcarUsrService {

    public EvcarUsrVO loginAction(EvcarUsrVO evcarUsrVO) throws Exception;

    public EvcarUsrVO joinUserData(EvcarUsrVO evcarUsrVO) throws Exception;

    public int IdCheck(EvcarUsrVO vo)throws Exception;

    public int CardCheck(EvcarUsrVO vo)throws Exception;

}
