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
    public BbsMasterVO selectBbsMaster(String bbsId) throws Exception {
        return bbsDAO.selectBbsMaster(bbsId);
    }

    @Override
    public int selectBbsCount(BbsVO vo) {
        return bbsDAO.selectBbsCount(vo);
    }

    @Override
    public List<BbsVO> selectBbsList(BbsVO vo) {
        return bbsDAO.selectBbsList(vo);
    }

    public BbsVO ViewBbs(BbsVO bbsVO) {
        return bbsDAO.ViewBbs(bbsVO);
    }
}
