package com.appsdeveloperblog.photoapp.api.users.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.appsdeveloperblog.photoapp.api.users.service.UsersService;
import com.appsdeveloperblog.photoapp.api.users.shared.UserDto;
import com.appsdeveloperblog.photoapp.api.users.ui.model.LoginRequestModel;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private UsersService usersService;
	private Environment env;
	
	
	@Autowired
	public AuthenticationFilter(UsersService usersService , 
								Environment env,
								AuthenticationManager authenticationManager) {
		this.usersService = usersService;
		this.env = env;
		super.setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		
		try {
			
		LoginRequestModel creds;
		
			creds = new ObjectMapper()
							.readValue(request.getInputStream(), LoginRequestModel.class);
			
			return this.getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(
						creds.getEmail(),
						creds.getPassword(),
						new ArrayList<>())
					);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} 
					
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request,
											HttpServletResponse response,
											FilterChain chain,
											Authentication authResult) throws IOException, ServletException {
			String userName = ((User)authResult.getPrincipal()).getUsername();
			UserDto userDto = usersService.getUserDetailsByEmail(userName);
	
		
	}

}
