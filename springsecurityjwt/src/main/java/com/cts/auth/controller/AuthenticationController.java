package com.cts.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.auth.dto.JwtAuthenticationResponse;
import com.cts.auth.dto.RefreshTokenRequest;
import com.cts.auth.dto.SignInRequest;
import com.cts.auth.dto.SignUpRequest;
import com.cts.auth.dto.UserRegistration;
import com.cts.auth.entity.User;
import com.cts.auth.service.AuthenticationServiceImpl;
import com.cts.auth.service.JWTServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private Logger log = LoggerFactory.getLogger(AuthenticationController.class);
	
	@Autowired
    private  AuthenticationServiceImpl authenticationService;

    @Autowired
    JWTServiceImpl jwtServiceImpl;
    
    @Autowired
    UserDetailsService userDetailsService;
	
    /*@Autowired
	private  KafkaJsonProducer jsonProducer;*/
    
    @PostMapping("/registration")
    public ResponseEntity<User> registration(@RequestBody @Validated SignUpRequest signUpRequest){
    	//UserRegistration userRegistration=new UserRegistration();
    	//userRegistration.setEmail(signUpRequest.getEmail());
    	//userRegistration.setPassword(signUpRequest.getPassword());
    	
    	//jsonProducer.sendMessage(userRegistration);
        return ResponseEntity.ok(authenticationService.registration(signUpRequest));
    }
    

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.login(signInRequest));
    }
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }


	@GetMapping("/extractUsername")
	public String extractUserName(@RequestHeader("Authorization") String token) {
		return jwtServiceImpl.extractUserName(token.substring(7));
	}
	@GetMapping("/isTokenValid")
	public boolean isTokenValid(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		return jwtServiceImpl.isTokenValid(token, loadUserByUsername(token));
	}
	   @GetMapping("/loadUserByUsername/{username}")
		public User loadUserByUsername(@PathVariable String username) {
	    	return (User) userDetailsService.loadUserByUsername(username);
	    }
	   
   
}