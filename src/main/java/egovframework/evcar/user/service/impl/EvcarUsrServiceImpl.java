package egovframework.evcar.user.service.impl;

import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.evcar.card.dao.CardDAO;
import egovframework.evcar.user.dao.EvcarUsrDAO;
import egovframework.evcar.user.service.EvcarUsrService;
import egovframework.evcar.user.vo.EvcarUsrVO;
import egovframework.evcar.card.vo.UsrCardVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import org.apache.commons.lang3.StringUtils;
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

    @Resource(name="CardDAO")
    private CardDAO cardDAO;

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
        String userUniqKey = userAthKey.getNextStringId();
        evcarUsrVO.setUsrSno(userUniqKey);

        // 입력한 비밀번호를 암호화한다.
        String enpassword = EgovFileScrty.encryptPassword(evcarUsrVO.getUsrPwd(), evcarUsrVO.getUsrId());
        evcarUsrVO.setUsrPwd(enpassword);

        evcarUserDAO.joinUserData(evcarUsrVO);

        for (UsrCardVO vo : evcarUsrVO.getUsrCardList()) {
            if(StringUtils.isEmpty(vo.getCardSno())){
                logger.debug("카드번호가 없습니다.");
                break;
            }
            vo.setUsrSno(userUniqKey);
            cardDAO.insertUserCard(vo);
        }

        return evcarUsrVO;
    }
}