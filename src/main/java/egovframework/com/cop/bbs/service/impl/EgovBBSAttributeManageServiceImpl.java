package egovframework.com.cop.bbs.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import egovframework.com.cmm.EgovComponentChecker;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.BoardUseInf;
import egovframework.com.cop.bbs.service.EgovBBSAttributeManageService;
import egovframework.com.cop.com.service.EgovUserInfManageService;
import egovframework.com.cop.com.service.UserInfVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 게시판 속성관리를 위한 서비스 구현 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.3.24  이삼섭          최초 생성
 *   2009.06.26	한성곤		2단계 기능 추가 (댓글관리, 만족도조사)
 *   2011.09.15 서준식       댓글, 만족도 조사 적용 방법 변경
 * </pre>
 */
@Service("EgovBBSAttributeManageService")
public class EgovBBSAttributeManageServiceImpl extends EgovAbstractServiceImpl implements EgovBBSAttributeManageService {

    @Resource(name = "BBSAttributeManageDAO")
    private BBSAttributeManageDAO attrbMngDAO;

    @Resource(name = "BBSUseInfoManageDAO")
    private BBSUseInfoManageDAO bbsUseDAO;

    @Resource(name = "EgovUserInfManageService")
    private EgovUserInfManageService userService;

    @Resource(name = "egovBBSMstrIdGnrService")
    private EgovIdGnrService idgenService;
    
    //---------------------------------
    // 2009.06.26 : 2단계 기능 추가
    //---------------------------------
    @Resource(name = "BBSAddedOptionsDAO")
    private BBSAddedOptionsDAO addedOptionsDAO;
    ////-------------------------------
		
    /**
     * 등록된 게시판 속성정보를 삭제한다.
     * 
     * @see egovframework.com.cop.bbs.brd.service.EgovBBSAttributeManageService#deleteBBSMasterInf(egovframework.com.cop.bbs.brd.service.BoardMaster)
     */
    public void deleteBBSMasterInf(BoardMaster boardMaster) throws Exception {
	attrbMngDAO.deleteBBSMasterInf(boardMaster);
	
	BoardUseInf bdUseInf = new BoardUseInf();
	
	bdUseInf.setBbsId(boardMaster.getBbsId());
	bdUseInf.setLastUpdusrId(boardMaster.getLastUpdusrId());
	
	bbsUseDAO.deleteBBSUseInfByBoardId(bdUseInf);
    }

    /**
     * 신규 게시판 속성정보를 생성한다.
     * 
     * @see egovframework.com.cop.bbs.brd.service.EgovBBSAttributeManageService#insertBBSMastetInf(egovframework.com.cop.bbs.brd.service.BoardMaster)
     */
    public String insertBBSMastetInf(BoardMaster boardMaster) throws Exception {
	String bbsId = idgenService.getNextStringId();
	
	boardMaster.setBbsId(bbsId);
	
	attrbMngDAO.insertBBSMasterInf(boardMaster);
	
	//---------------------------------
	// 2009.06.26 : 2단계 기능 추가
	//---------------------------------
	if (boardMaster.getOption().equals("comment") || boardMaster.getOption().equals("stsfdg")) {
	    addedOptionsDAO.insertAddedOptionsInf(boardMaster);
	}
	////-------------------------------

	if ("Y".equals(boardMaster.getBbsUseFlag())) {
	    BoardUseInf bdUseInf = new BoardUseInf();

	    bdUseInf.setBbsId(bbsId);
	    bdUseInf.setTrgetId(boardMaster.getTrgetId());
	    bdUseInf.setRegistSeCode(boardMaster.getRegistSeCode());
	    bdUseInf.setFrstRegisterId(boardMaster.getFrstRegisterId());
	    bdUseInf.setUseAt("Y");

	    bbsUseDAO.insertBBSUseInf(bdUseInf);

	    UserInfVO userVO = new UserInfVO();
	    userVO.setTrgetId(boardMaster.getTrgetId());

	    List<UserInfVO> tmpList = null;
	    Iterator<UserInfVO> iter = null;

	    if ("REGC05".equals(boardMaster.getRegistSeCode())) {
		tmpList = userService.selectAllClubUser(userVO);
		iter = tmpList.iterator();
		while (iter.hasNext()) {
		    bdUseInf = new BoardUseInf();
		    
		    bdUseInf.setBbsId(bbsId);
		    bdUseInf.setTrgetId(((UserInfVO)iter.next()).getUniqId());
		    bdUseInf.setRegistSeCode("REGC07");
		    bdUseInf.setUseAt("Y");
		    bdUseInf.setFrstRegisterId(boardMaster.getFrstRegisterId());
		    
		    bbsUseDAO.insertBBSUseInf(bdUseInf);
		}
	    } else if ("REGC06".equals(boardMaster.getRegistSeCode())) {
		tmpList = userService.selectAllCmmntyUser(userVO);
		iter = tmpList.iterator();
		while (iter.hasNext()) {
		    bdUseInf = new BoardUseInf();
		    
		    bdUseInf.setBbsId(bbsId);
		    bdUseInf.setTrgetId(((UserInfVO)iter.next()).getUniqId());
		    bdUseInf.setRegistSeCode("REGC07");
		    bdUseInf.setUseAt("Y");
		    bdUseInf.setFrstRegisterId(boardMaster.getFrstRegisterId());
		    
		    bbsUseDAO.insertBBSUseInf(bdUseInf);
		}
	    }
	}
	return bbsId;
    }

    /**
     * 게시판 속성 정보의 목록을 조회 한다.
     * 
     * @see egovframework.com.cop.bbs.brd.service.EgovBBSAttributeManageService#selectAllBBSMasteInf(egovframework.com.cop.bbs.brd.service.BoardMasterVO)
     */
    public List<BoardMasterVO> selectAllBBSMasteInf(BoardMasterVO vo) throws Exception {
	return attrbMngDAO.selectAllBBSMasteInf(vo);
    }

    /**
     * 게시판 속성정보 한 건을 상세조회한다.
     * 
     * @see egovframework.com.cop.bbs.brd.service.EgovBBSAttributeManageService#selectBBSMasterInf(egovframework.com.cop.bbs.brd.service.BoardMasterVO)
     */
    public BoardMasterVO selectBBSMasterInf(BoardMaster searchVO) throws Exception {
	//---------------------------------
	// 2009.06.26 : 2단계 기능 추가
	//---------------------------------
	//return attrbMngDAO.selectBBSMasterInf(searchVO);
	
	BoardMasterVO result = attrbMngDAO.selectBBSMasterInf(searchVO);

	//String flag = EgovProperties.getProperty("Globals.addedOptions");
	//if (flag != null && flag.trim().equalsIgnoreCase("true")) {
	if(EgovComponentChecker.hasComponent("EgovBBSCommentService") || EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){//2011.09.15
	    BoardMasterVO options = addedOptionsDAO.selectAddedOptionsInf(searchVO);
	    
	    if (options != null) {
		if (options.getCommentAt().equals("Y")) {
		    result.setOption("comment");
		}

		if (options.getStsfdgAt().equals("Y")) {
		    result.setOption("stsfdg");
		}
	    } else {
		result.setOption("na");	// 미지정 상태로 수정 가능 (이미 지정된 경우는 수정 불가로 처리)
	    }
	}

	return result;
	////-------------------------------
	
    }

    /**
     * 게시판 속성 정보의 목록을 조회 한다.
     * 
     * @see egovframework.com.cop.bbs.brd.service.EgovBBSAttributeManageService#selectBBSMasterInfs(egovframework.com.cop.bbs.brd.service.BoardMasterVO)
     */
    public Map<String, Object> selectBBSMasterInfs(BoardMasterVO searchVO) throws Exception {
	List<BoardMasterVO> result = attrbMngDAO.selectBBSMasterInfs(searchVO);
	int cnt = attrbMngDAO.selectBBSMasterInfsCnt(searchVO);
	
	Map<String, Object> map = new HashMap<String, Object>();
	
	map.put("resultList", result);
	map.put("resultCnt", Integer.toString(cnt));

	return map;
    }

    /**
     * 게시판 속성정보를 수정한다.
     * 
     * @see egovframework.com.cop.bbs.brd.service.EgovBBSAttributeManageService#updateBBSMasterInf(egovframework.com.cop.bbs.brd.service.BoardMaster)
     */
    public void updateBBSMasterInf(BoardMaster boardMaster) throws Exception {
	attrbMngDAO.updateBBSMasterInf(boardMaster);
	
	//---------------------------------
	// 2009.06.26 : 2단계 기능 추가
	//---------------------------------

	if (boardMaster.getOption().equals("comment") || boardMaster.getOption().equals("stsfdg")) {
	    if (boardMaster.getOption().equals("na")) {
		return;
	    }
	    BoardMasterVO options = addedOptionsDAO.selectAddedOptionsInf(boardMaster);
	    
	    if (options == null) {
		boardMaster.setFrstRegisterId(boardMaster.getLastUpdusrId());
		addedOptionsDAO.insertAddedOptionsInf(boardMaster);
	    } else {
		//수정 기능 제외 (새롭게 선택사항을 지정한 insert만 처리함)
		//addedOptionsDAO.updateAddedOptionsInf(boardMaster);
		egovLogger.debug("BBS Master update ignored...");
	    }
	}
	////-------------------------------
    }

    /**
     * 템플릿의 유효여부를 점검한다.
     * 
     * @see egovframework.com.cop.bbs.brd.service.EgovBBSAttributeManageService#validateTemplate(egovframework.com.cop.bbs.brd.service.BoardMasterVO)
     */
    public void validateTemplate(BoardMasterVO searchVO) throws Exception {
    	egovLogger.debug("validateTemplate method ignored...");
    }

    /**
     * 사용중인 게시판 속성 정보의 목록을 조회 한다.
     */
    public Map<String, Object> selectBdMstrListByTrget(BoardMasterVO vo) throws Exception {
	List<BoardMasterVO> result = attrbMngDAO.selectBdMstrListByTrget(vo);
	int cnt = attrbMngDAO.selectBdMstrListCntByTrget(vo);
	
	Map<String, Object> map = new HashMap<String, Object>();
	
	map.put("resultList", result);
	map.put("resultCnt", Integer.toString(cnt));

	return map;
    }

    /**
     * 커뮤니티, 동호회에서 사용중인 게시판 속성 정보의 목록을 전체조회 한다.
     */
    public List<BoardMasterVO> selectAllBdMstrByTrget(BoardMasterVO vo) throws Exception {
	return attrbMngDAO.selectAllBdMstrByTrget(vo);
    }

    /**
     * 사용중이지 않은 게시판 속성 정보의 목록을 조회 한다.
     */
    public Map<String, Object> selectNotUsedBdMstrList(BoardMasterVO searchVO) throws Exception {
	List<BoardMasterVO> result = attrbMngDAO.selectNotUsedBdMstrList(searchVO);
	int cnt = attrbMngDAO.selectNotUsedBdMstrListCnt(searchVO);
	
	Map<String, Object> map = new HashMap<String, Object>();
	
	map.put("resultList", result);
	map.put("resultCnt", Integer.toString(cnt));

	return map;
    }
}
