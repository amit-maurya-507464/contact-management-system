package com.example.cms.security;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

	  private final JwtTokenProvider jwtTokenProvider;

	  public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
	    this.jwtTokenProvider = jwtTokenProvider;
	  }

	  @Override
	  protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
	    try {
	    	 final String requestTokenHeader = httpServletRequest.getHeader( "Authorization" );
	         String jwtToken = null;
	         // JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
	         if ( requestTokenHeader != null && requestTokenHeader.startsWith( "Bearer " ))
				 jwtToken = requestTokenHeader.substring(7);
	         else logger.warn("JWT Token does not begin with Bearer String");
	    	if (jwtToken != null && jwtTokenProvider.validateToken(jwtToken)) {
	        Authentication auth = jwtTokenProvider.getAuthentication(jwtToken);
	        SecurityContextHolder.getContext().setAuthentication(auth);
	      }
	    } catch (Exception ex) {
	      //this is very important, since it guarantees the user is not authenticated at all
	      SecurityContextHolder.clearContext();
	      httpServletResponse.sendError(401, ex.getMessage());
	      return;
	    }
	    filterChain.doFilter(httpServletRequest, httpServletResponse);
	  }

	}