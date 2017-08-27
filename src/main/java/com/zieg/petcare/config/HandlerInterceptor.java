package com.zieg.petcare.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zieg.petcare.services.UserService;

public class HandlerInterceptor extends HandlerInterceptorAdapter{

    private static Logger log = LoggerFactory.getLogger(HandlerInterceptor.class);
	private static final long MAX_INACTIVE_SESSION_TIME = 5 * 100000;
	
	@Autowired
    private HttpSession session;
	
	@Autowired
	private UserService userService;
	
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, 
    						 final Object handler) throws Exception {
    	
        long startTime = System.currentTimeMillis();
        request.setAttribute("executionTime", startTime);
        if (userService.isUserLogged()) {
            session = request.getSession();
            
            log.info("The last access, request session time: {} ms", System.currentTimeMillis() - request.getSession().getLastAccessedTime());
            if(System.currentTimeMillis() - session.getLastAccessedTime() > MAX_INACTIVE_SESSION_TIME) {
                log.warn("Logging out, to inactive session");
                SecurityContextHolder.clearContext();
                request.logout();
                response.sendRedirect("/logout");
                
                return false;
            }
            
            if(userService.getUserNameLoged() == null){
            	log.warn("Logging out, to user no most valid!");
                SecurityContextHolder.clearContext();
                request.logout();
                response.sendRedirect("/logout");
                
                return false;
            }
            
        }
        else return false;
        
        return true;
    }

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
						   Object handler, ModelAndView modelAndView) throws Exception {
		
		log.info("Request after handler execute - URL :: {} Sent to Handler :: Current Time = {} ",
				request.getRequestURL().toString(), System.currentTimeMillis());

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
								HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
		log.info("Request after complete handler execution -  URL:: {} Sent to Handler :: Sent to Handler :: Current Time= {} ",
				request.getRequestURL().toString(), System.currentTimeMillis());
	}

}

