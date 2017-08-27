package com.zieg.petcare.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.core.Ordered;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@Configuration
@EnableSpringConfigured
public class WebConfig extends WebMvcConfigurerAdapter {

	
	public WebConfig() {
        super();
    }
	
	@Bean
	public BCryptPasswordEncoder passwordEnconder(){
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
		
	}
	
	@Bean
	public LocaleResolver localeResolver(){
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry){
		registry.addViewController("/").setViewName("login");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}
	
	@Bean
	public HandlerInterceptor getHandlerInterceptor(){
		return new HandlerInterceptor();
	}
	
	
	@Bean(name = "AuthCustom")
	public AuthenticationManagerCustom getAuthenticationCustom(){
		return new AuthenticationManagerCustom();
	}
		
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(getHandlerInterceptor());
	}
	
	
}
