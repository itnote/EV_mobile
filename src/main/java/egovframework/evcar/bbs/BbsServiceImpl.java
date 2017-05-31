package egovframework.evcar.bbs;

import egovframework.evcar.bbs.dao.BbsDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Doum on 2017-05-31.
 */
@Service("BbsService")
public class BbsServiceImpl implements BbsService{

    @Resource(name = "BbsDAO")
    private BbsDAO bbsDAO;

    @Override
    public int selectBbsCount(BbsVO vo) {
        return bbsDAO.selectBbsCount(vo);
    }

    @Override
    public List<BbsVO> selectBbsList(BbsVO vo) {
        return bbsDAO.selectBbsList(vo);
    }
}
