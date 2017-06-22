package egovframework.evcar.card.service.impl;

import egovframework.evcar.card.dao.CardDAO;
import egovframework.evcar.card.service.CardService;
import egovframework.evcar.card.vo.UsrCardVO;
import egovframework.evcar.user.dao.EvcarUsrDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by dongguk on 2017-06-02.
 */
@Service("CardService")
public class CardServiceImpl implements CardService {

    @Resource(name = "CardDAO")
    private CardDAO cardDAO;

    @Resource(name = "EvcarUsrDAO")
    private EvcarUsrDAO evcarUsrDAO;


    @Override
    public void changeUsrCard(UsrCardVO usrCardVO) throws Exception {

        if(cardDAO.selectUserCardCheck(usrCardVO) > 0) {
            cardDAO.updateUserCard(usrCardVO);
        }else{
            cardDAO.insertUserCard(usrCardVO);
        }

        // 사용자 카드 정보 변경
        evcarUsrDAO.updateUsrCard(usrCardVO);

    }

}
