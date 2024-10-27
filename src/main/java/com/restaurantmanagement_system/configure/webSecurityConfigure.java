package com.restaurantmanagement_system.configure;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.restaurantmanagement_system.service.userDetailServiceImp;

@Configuration
@EnableWebSecurity
public class webSecurityConfigure {
	
	@Autowired
	private userDetailServiceImp userDetailServiceImp;
		
	@Autowired
	public void configureGlobal( AuthenticationManagerBuilder authenticationManagerBuilder ) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailServiceImp);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.
		     csrf().disable() 
			.authorizeRequests()
			.requestMatchers("/login").anonymous() // allow any anonynous users
			.requestMatchers("/admin/**").hasRole("ADMIN")
			.requestMatchers("/users/**").hasRole("USER")
			.requestMatchers("/css/**","/img/**","/js/**","/file/**").permitAll() 
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/loginProcess")
			.defaultSuccessUrl("/")
			.failureUrl("/login?error=true")
			
			.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout=success") // like active btn that convey where we will rumble down after we log out
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID" , "remember-me");
			
		return http.build();
	}
	

	
}
