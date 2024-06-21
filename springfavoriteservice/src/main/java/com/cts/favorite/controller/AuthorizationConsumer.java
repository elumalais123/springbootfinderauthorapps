package com.cts.favorite.controller;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="SPRINGSECURITYJWT")
public interface AuthorizationConsumer {
	
	@GetMapping("/api/v1/auth/extractUsername")
	public String extractUserName(@RequestHeader("Authorization") String token) ;

}
