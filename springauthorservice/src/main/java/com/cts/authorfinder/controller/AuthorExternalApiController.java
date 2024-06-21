package com.cts.authorfinder.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cts.authorfinder.dto.AuthorDetailsResponse;
import com.cts.authorfinder.dto.AuthorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/api/v1")
public class AuthorExternalApiController {

	//private static final Log log = LogFactory.getLog(AuthorExternalApiController.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/searchbyauthor")
	public ResponseEntity<AuthorResponse> findAuthorByName(@RequestParam("author") String author) throws JsonMappingException, JsonProcessingException{

		System.out.println("findAuthorByName :::::");
		AuthorResponse resultJson=restTemplate.getForObject("https://openlibrary.org/search/authors.json?q="+author, AuthorResponse.class);
		
		System.out.println("resultJson :"+resultJson);
		
		return new ResponseEntity<AuthorResponse>(resultJson, HttpStatus.OK);
	}
	@GetMapping("/findbyauthorkey")
	public ResponseEntity<AuthorDetailsResponse> findAuthorByKey(@RequestParam("authorkey") String authorkey) throws JsonMappingException, JsonProcessingException{

		System.out.println("authorkey ::::"+authorkey);
		AuthorDetailsResponse resultJson=restTemplate.getForObject("https://openlibrary.org/authors/"+authorkey+".json", AuthorDetailsResponse.class);
		
		System.out.println("resultJson :"+resultJson);
		
		return new ResponseEntity<AuthorDetailsResponse>(resultJson, HttpStatus.OK);
	}
	
	
}




