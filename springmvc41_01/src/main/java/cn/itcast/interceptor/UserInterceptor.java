package cn.itcast.interceptor;


import org.apache.commons.lang3.StringUtils;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 如果链接中包含了login我们放行
        // 如果session里有 user 我们放行
        HttpSession session = httpServletRequest.getSession();
        String user = (String) session.getAttribute("user");
        String uri = httpServletRequest.getRequestURI();
        System.out.println(uri);
        if (uri.equals("/toLogin.action") || uri.equals("/logout.action") || uri.equals("/login.action") || (user != null && user.trim() != "")){
            return true;
        }else {
            session.setAttribute("error","<font color='red'>您没有权限,请登录后再访问</font>");
            httpServletResponse.sendRedirect("/toLogin.action");
        }

        return false;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}