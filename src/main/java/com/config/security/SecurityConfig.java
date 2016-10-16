package com.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;

import com.security.SecurityPackage;
import com.security.exception.AuthenticationEntryPointImpl;
import com.security.filter.AuthenticationFilter;
import com.security.filter.CsrfHeaderFilter;
import com.security.filter.LoginFilter;
import com.security.service.TokenAuthenticationService;
import com.security.service.UserDetailsService;
import com.security.voter.UserRoleVoter;
import com.util.StaticURL;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = SecurityPackage.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public SecurityConfig() {
		super(true);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.anonymous().and().authorizeRequests().antMatchers(StaticURL.PUBLIC_ALL).permitAll().anyRequest()
				.authenticated().and()
				.addFilterBefore(new LoginFilter(StaticURL.LOGIN, tokenAuthenticationService(), userDetailsService(),
						authenticationManager()), UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(new AuthenticationFilter(tokenAuthenticationService()),
						UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPointImpl()).and()
				.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		return new UserDetailsService();
	}

	@Bean
	public TokenAuthenticationService tokenAuthenticationService() {
		return new TokenAuthenticationService();
	}

	@Bean
	public RoleVoter roleVoter() {
		return new UserRoleVoter();
	}
}