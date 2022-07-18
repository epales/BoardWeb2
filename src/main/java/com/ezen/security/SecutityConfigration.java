package com.ezen.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecutityConfigration {

	@Autowired
	private SecurityUserDetailsService securityUserDetailsService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		security.authorizeRequests().antMatchers("/","/system/**").permitAll();  // "/" - 모든 사용자가 접근 가능함.
		security.authorizeRequests().antMatchers("/board/**").authenticated();   // 로그인 성공한 인증사용자만 접근 가능함.
		security.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");  // 'ADMIN'권한을 가진 사용자가 접근 가능함.
		
		security.csrf().disable();  // csrf - Cross Site Request Forgery. 자신의 의도와 상관없이 웹 사이트를 공격하는 행위
		// form() 태그를 가진 로그인 화면을 지원한다는 설정
		// 직접 작성한 로그인 화면 제공 설정
		security.formLogin().loginPage("/login").defaultSuccessUrl("/getBoardList", true);
		// 인증되지 않은 사용자에게 제공하는 URL
		security.exceptionHandling().accessDeniedPage("/accessDenied");
		// 로그아웃 설정
		security.logout().invalidateHttpSession(true).logoutSuccessUrl("/");
		
		// 테이블의 사용자 정보를 Details 타입으로 변환
		security.userDetailsService(securityUserDetailsService);
		
		return security.build();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
