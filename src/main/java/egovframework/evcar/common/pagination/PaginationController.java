package egovframework.evcar.common.pagination;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * Created by Doum on 2017-05-31.
 */
public class PaginationController extends AbstractPaginationRenderer implements ServletContextAware {

    private ServletContext servletContext;

    public PaginationController() {

    }

    public void initVariables(){
        firstPageLabel    = "";
        previousPageLabel = "<a class=\"arrow\" href=\"javascript:;\" onclick=\"{0}({1});return false; \">이전 <i>◀</i></a>&#160;";
        currentPageLabel  = "<strong>{0}</strong>&#160;";
        otherPageLabel    = "<a href=\"javascript:;\" onclick=\"{0}({1});return false; \">{2}</a>&#160;";
        nextPageLabel     = "<a class=\"arrow\" href=\"javascript:;\" onclick=\"{0}({1});return false; \">다음 <i>▶</i></a>&#160;";
        lastPageLabel     = "";
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
        initVariables();
    }
}
