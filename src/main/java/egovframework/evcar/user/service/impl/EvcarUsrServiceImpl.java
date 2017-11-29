package egovframework.evcar.user.service.impl;

import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.evcar.card.dao.CardDAO;
import egovframework.evcar.card.vo.UsrCardVO;
import egovframework.evcar.user.dao.EvcarUsrDAO;
import egovframework.evcar.user.service.EvcarUsrService;
import egovframework.evcar.user.vo.EvcarUsrVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * Created by doum on 2017-11-20.
 */
@Service("EvcarUserService")
public class EvcarUsrServiceImpl implements EvcarUsrService {

    private static final Logger logger = LoggerFactory.getLogger(EvcarUsrServiceImpl.class);

    @Resource(name="EvcarUsrDAO")
    private EvcarUsrDAO evcarUserDAO;

    @Resource(name="CardDAO")
    private CardDAO cardDAO;

    @Resource(name="userAthKeyIdGnrService")
    private EgovIdGnrService userAthKey;

    @Override
    public EvcarUsrVO loginAction(EvcarUsrVO evcarUsrVO) throws Exception {

        // 입력한 비밀번호를 암호화한다.
        String enpassword = EgovFileScrty.encryptPassword(evcarUsrVO.getPwdNo(), evcarUsrVO.getUserId());
        evcarUsrVO.setPwdNo(enpassword);

        evcarUsrVO = evcarUserDAO.loginAction(evcarUsrVO);

        return evcarUsrVO;
    }

    @Override
    public EvcarUsrVO joinUserData(EvcarUsrVO evcarUsrVO) throws Exception {

        evcarUsrVO.setAcrdCrdNo(evcarUsrVO.getAcrdCrdNo().replace("-", ""));

        // 입력한 비밀번호를 암호화한다.
        String enpassword = EgovFileScrty.encryptPassword(evcarUsrVO.getPwdNo(), evcarUsrVO.getUserId());
        evcarUsrVO.setPwdNo(enpassword);

        evcarUserDAO.joinUserData(evcarUsrVO);
        return evcarUsrVO;
    }


    //idCheck
    @Override
    public int IdCheck(EvcarUsrVO vo) throws Exception{
        int cnt =evcarUserDAO.IdCheck(vo);
        return cnt;
    }
    //CardCheck
    @Override
    public int CardCheck(EvcarUsrVO vo) throws Exception{
        vo.setAcrdCrdNo(vo.getAcrdCrdNo().replace("-", ""));
        int cnt =evcarUserDAO.CardCheck(vo);
        return cnt;
    }
}
