package com.example.cms.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtTokenFilterConfigure extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	  private final JwtTokenProvider jwtTokenProvider;

	  public JwtTokenFilterConfigure(JwtTokenProvider jwtTokenProvider) {
	    this.jwtTokenProvider = jwtTokenProvider;
	  }

	  @Override
	  public void configure(HttpSecurity http) throws Exception {
	    JwtTokenFilter customFilter = new JwtTokenFilter(jwtTokenProvider);
	    http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
	  }

}
