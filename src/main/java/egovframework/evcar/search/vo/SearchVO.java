package egovframework.evcar.search.vo;

/**
 * Created by Doum on 2017-05-30.
 */

import egovframework.com.cmm.ComDefaultVO;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by Doum on 2017-05-30.
 * 공통 검색 VO
 */
public class SearchVO extends ComDefaultVO {

    /** 코드*/
    private String searchCondition;;
    private String searchType1;
    private String searchType2;
    private String searchType3;

    private String searchKeyword1;
    private String searchKeyword2;
    private String searchKeyword3;

    /** 검색일*/
    private String searchDate = "";

    /** 시작일*/
    private String searchDateFrom = "";

    /** 종료일*/
    private String searchDateto = "";


    private String command = "";
    private String message = "";

    // paging
    private int pageIndex = 1;
    private int pageUnit = 10;
    private int officepageUnit = 1;
    private int pageSize = 5;
    private int firstIndex = 1;
    private int lastIndex = 1;
    private int recordCountPerPage = 10;

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    public String getSearchType1() {
        return searchType1;
    }
    public void setSearchType1(String searchType1) {
        this.searchType1 = searchType1;
    }
    public String getSearchType2() {
        return searchType2;
    }
    public void setSearchType2(String searchType2) {
        this.searchType2 = searchType2;
    }
    public String getSearchType3() {
        return searchType3;
    }
    public void setSearchType3(String searchType3) {
        this.searchType3 = searchType3;
    }
    public String getSearchCondition() {
        return searchCondition;
    }
    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }
    public String getSearchKeyword1() {
        return searchKeyword1;
    }
    public void setSearchKeyword1(String searchKeyword1) {
        this.searchKeyword1 = searchKeyword1;
    }
    public String getSearchKeyword2() {
        return searchKeyword2;
    }
    public void setSearchKeyword2(String searchKeyword2) {
        this.searchKeyword2 = searchKeyword2;
    }
    public String getSearchKeyword3() {
        return searchKeyword3;
    }
    public void setSearchKeyword3(String searchKeyword3) {
        this.searchKeyword3 = searchKeyword3;
    }
    public String getSearchDate() {
        return searchDate;
    }
    public void setSearchDate(String searchDate) {
        this.searchDate = searchDate;
    }
    public String getSearchDateFrom() {
        return searchDateFrom;
    }
    public void setSearchDateFrom(String searchDateFrom) {
        this.searchDateFrom = searchDateFrom;
    }
    public String getSearchDateto() {
        return searchDateto;
    }
    public void setSearchDateto(String searchDateto) {
        this.searchDateto = searchDateto;
    }
    public String getCommand() {
        return command;
    }
    public void setCommand(String command) {
        this.command = command;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public int getPageIndex() {
        return pageIndex;
    }
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
    public int getPageUnit() {
        return pageUnit;
    }
    public void setPageUnit(int pageUnit) {
        this.pageUnit = pageUnit;
    }
    public int getOfficepageUnit() {
        return officepageUnit;
    }
    public void setOfficepageUnit(int officepageUnit) {
        this.officepageUnit = officepageUnit;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getFirstIndex() {
        return firstIndex;
    }
    public void setFirstIndex(int firstIndex) {
        this.firstIndex = firstIndex;
    }
    public int getLastIndex() {
        return lastIndex;
    }
    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }
    public int getRecordCountPerPage() {
        return recordCountPerPage;
    }
    public void setRecordCountPerPage(int recordCountPerPage) {
        this.recordCountPerPage = recordCountPerPage;
    }
}
