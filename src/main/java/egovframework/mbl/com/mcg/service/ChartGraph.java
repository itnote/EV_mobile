package egovframework.mbl.com.mcg.service;

import java.io.Serializable;

/**
 * 개요
 * - 차트/그래프 데이터에 대한 Model 클래스를 정의한다.
 *
 * 상세내용
 * - 차트/그래프 데이터를 관리한다.
 * @author 정홍규
 * @since 2011.08.16
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2011.08.16  정홍규          최초 생성
 *
 * </pre>
 */

public class ChartGraph implements Serializable {

	private static final long serialVersionUID = -3882546722495194867L;
	/**
     * 순번
     */
    int sn;
    /**
     * 회원 ID
     */
    String mberId;
    /**
     * x축 값
     */
    String xaxis;
    /**
     * y축 값
     */
    int yaxis;
    /**
     * 범례명
     */
    String lgdNm;
    /**
     * 생성 일시
     */
    String creatDt;
    /**
     * 수정 일시
     */
    String updtDt;

    /**
     * 순번을 가져온다.
     * @return int 순번
     */
    public int getSn() {
        return sn;
    }

    /**
     * 순번을 저장한다.
     * @param sn
     */
    public void setSn(int sn) {
        this.sn = sn;
    }

    /**
     * 회원 ID를 가져온다.
     * @return String 회원 ID
     */
    public String getMberId() {
        return mberId;
    }

    /**
     * 회원 ID를 저장한다.
     * @param mberId
     */
    public void setMberId(String mberId) {
        this.mberId = mberId;
    }

    /**
     * x축 값을 가져온다.
     * @return int x축 값
     */
    public String getXaxis() {
        return xaxis;
    }

    /**
     * x축 값을 저장한다.
     * @param xaxis
     */
    public void setXaxis(String xaxis) {
        this.xaxis = xaxis;
    }

    /**
     * y축 값을 가져온다.
     * @return int y축 값
     */
    public int getYaxis() {
        return yaxis;
    }

    /**
     * y축 값을 저장한다.
     * @param yAxis
     */
    public void setYaxis(int yaxis) {
        this.yaxis = yaxis;
    }

    /**
     * 범례명을 가져온다.
     * @return String 범례명
     */
    public String getLgdNm() {
        return lgdNm;
    }

    /**
     * 범례명을 저장한다.
     * @param lgdNm
     */
    public void setLgdNm(String lgdNm) {
        this.lgdNm = lgdNm;
    }

    /**
     * 생성 일시를 가져온다.
     * @return String 생성 일시
     */
    public String getCreatDt() {
        return creatDt;
    }

    /**
     * 생성 일시를 저장한다.
     * @param createDt
     */
    public void setCreatDt(String creatDt) {
        this.creatDt = creatDt;
    }

    /**
     * 수정 일시를 가져온다.
     * @return String 수정 일시
     */
    public String getUpdtDt() {
        return updtDt;
    }

    /**
     * 수정 일시를 저장한다.
     * @param updtDt
     */
    public void setUpdtDt(String updtDt) {
        this.updtDt = updtDt;
    }
}
