package egovframework.evcar.bbs;

import java.util.List;

/**
 * Created by Doum on 2017-05-31.
 */
public interface BbsService {

    /** 게시판 화면 카운터  **/
    public int selectBbsCount(BbsVO vo);

    /** 게시판 화면 리스트 **/
    public List<BbsVO> selectBbsList(BbsVO vo);
}
