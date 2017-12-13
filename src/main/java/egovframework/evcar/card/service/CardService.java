package egovframework.evcar.card.service;

import egovframework.evcar.card.vo.UsrCardVO;

import java.util.List;

/**
 * Created by dongguk on 2017-06-02.
 */
public interface CardService {

    public List<UsrCardVO> selectUserCardList(UsrCardVO usrCardVO) throws Exception;

    public void changeUsrCard(UsrCardVO usrCardVO) throws Exception;
}
