package egovframework.evcar.user.service.impl;

import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.evcar.user.dao.EvcarUsrDAO;
import egovframework.evcar.user.service.EvcarUsrService;
import egovframework.evcar.user.vo.EvcarUsrVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by dongguk on 2017-05-30.
 */
@Service("EvcarUserService")
public class EvcarUsrServiceImpl implements EvcarUsrService {

    private static final Logger logger = LoggerFactory.getLogger(EvcarUsrServiceImpl.class);

    @Resource(name="EvcarUsrDAO")
    private EvcarUsrDAO evcarUserDAO;

    @Resource(name="userAthKeyIdGnrService")
    private EgovIdGnrService userAthKey;

    @Override
    public EvcarUsrVO loginAction(EvcarUsrVO evcarUsrVO) throws Exception {

        // 입력한 비밀번호를 암호화한다.
        String enpassword = EgovFileScrty.encryptPassword(evcarUsrVO.getUsrPwd(), evcarUsrVO.getUsrId());
        evcarUsrVO.setUsrPwd(enpassword);

        evcarUsrVO = evcarUserDAO.loginAction(evcarUsrVO);

        return evcarUsrVO;
    }

    @Override
    public EvcarUsrVO joinUserData(EvcarUsrVO evcarUsrVO) throws Exception {

        // 고유키 발급
        evcarUsrVO.setUsrSno(userAthKey.getNextStringId());

        // 입력한 비밀번호를 암호화한다.
        String enpassword = EgovFileScrty.encryptPassword(evcarUsrVO.getUsrPwd(), evcarUsrVO.getUsrId());
        evcarUsrVO.setUsrPwd(enpassword);

        evcarUserDAO.joinUserData(evcarUsrVO);

        return evcarUsrVO;
    }
}
