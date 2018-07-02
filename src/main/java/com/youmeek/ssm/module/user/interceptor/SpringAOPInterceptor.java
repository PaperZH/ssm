package com.youmeek.ssm.module.user.interceptor;


import com.youmeek.ssm.module.user.controller.TbUserController;
import com.youmeek.ssm.module.user.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SpringAOPInterceptor implements HandlerInterceptor {

    private static final Logger log =  LoggerFactory.getLogger(TbUserController.class);
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        log.debug("---------------------------------->interceptor");
        if(WhiteData.getWhiteApi().contains(httpServletRequest.getRequestURI()))
        {
            return true;
        }
        String auth = httpServletRequest.getHeader("Authorization");
        log.info(auth);
        if(!StringUtils.hasText(auth)){
            return false;
        }

        JwtUtil.JWTResult jwt = JwtUtil.checkToken(auth);
        log.info("-------->"+jwt);
        if(jwt.isStatus()){
            return true;
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
