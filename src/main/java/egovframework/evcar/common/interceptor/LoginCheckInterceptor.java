package egovframework.evcar.common.interceptor;

import egovframework.evcar.common.annotation.LoginCheck;
import egovframework.evcar.user.vo.EvcarUsrVO;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by dongguk on 2017-06-02.
 */
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // TODO Auto-generated method stub
        HandlerMethod method = (HandlerMethod)handler;

        LoginCheck loginCheck = method.getMethodAnnotation(LoginCheck.class);
        //어노테이션이 없거나 있는데 value를 false로 준경우.
        if(loginCheck == null || loginCheck.value() == false){
            return true;
        }

        HttpSession session = request.getSession();

        EvcarUsrVO loginVO = (EvcarUsrVO)session.getAttribute("loginVO");

        if(loginVO == null){
            response.sendRedirect("/user/login.mdo");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }
}
