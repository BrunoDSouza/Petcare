package com.zieg.petcare.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.zieg.petcare.model.authentication.Acls;
import com.zieg.petcare.repository.UsersRepository;
import com.zieg.petcare.services.UserService;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = UsersRepository.class)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired private UserService userDetailsService;	
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
			
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEnconder());
		
	}
		
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http.csrf().disable()
			.authorizeRequests()
				.antMatchers("/produto").hasAnyRole(Acls.FUNC, Acls.ADMIN)
				.antMatchers("/fornecedor").hasAnyRole(Acls.FUNC, Acls.ADMIN)
				.antMatchers("/tipos").hasAnyRole(Acls.FUNC, Acls.ADMIN)
				.antMatchers("/movimentacao").hasAnyRole(Acls.FUNC, Acls.ADMIN)
				.antMatchers("/setor").hasAnyRole(Acls.FUNC, Acls.ADMIN)
				.antMatchers("/produto/**").hasRole(Acls.ADMIN)
				.antMatchers("/fornecedor/**").hasRole(Acls.ADMIN)
				.antMatchers("/tipos/**").hasRole(Acls.ADMIN)
				.antMatchers("/setor/**").hasRole(Acls.ADMIN)
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/").permitAll()
				.failureUrl("/login/?error=true").permitAll()
				.defaultSuccessUrl("/home").permitAll()
				.loginPage("/").permitAll()
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login/?logout").permitAll()
				.invalidateHttpSession(true).permitAll()
		 		.clearAuthentication(true)
		 		.deleteCookies("SPRING_SECURITY_REMEMBER_MECOOKIE","JSESSIONID");
				
	}

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		
		web.ignoring()
		   .antMatchers("/layout/**",
				   	    "/stylesheets/**", 
				   	    "/javascripts/**",
				   	    "/images/**");
		
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEnconder(){
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
		
	}
	
	
	@Bean
    public PasswordEncoder getPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return true;
            }
        };
    }
	
	
}
