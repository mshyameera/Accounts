package com.assignment.accounts.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.assignment.accounts.exceptionHandlers.CustomJWTException;
import com.assignment.accounts.services.AppUserDetailsService;
import com.assignment.accounts.utils.JwtUtil;

import io.jsonwebtoken.JwtException;



@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private AppUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	/**
	 *check authorizationHeader to start with "Bearer ", extract jwt, getUserDetails and validate token 
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String authorizationHeader = request.getHeader("Authorization");
		String username = null;
		String jwt = null;

		try {
			if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
				jwt = authorizationHeader.substring(7); //extract jwt
				username = jwtUtil.extractUsername(jwt);
			}
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

				if (jwtUtil.validateToken(jwt, userDetails)) {

					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
			filterChain.doFilter(request, response);
		} catch (JwtException e) {
			throw new CustomJWTException("801", "Login Time limit Expired, Please login again"+ e.getMessage());
		}
		catch (Exception e) {
			throw new CustomJWTException("802", "Something went wrong, Please login again"+e.getMessage());
		}
	}

}
