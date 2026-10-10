package com.example.gakuboschedules.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component 
public class LoginInterceptor implements HandlerInterceptor {
    @Override 
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String path = request.getRequestURI();
        
        if(path.equals("/") || path.equals("/login") || path.equals("/signup") || path.equals("/index")) {
            return true;
        }

        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute("loginUser") != null) {
            return true;
        }

        response.sendRedirect("/login");
        return false;
    }
}
