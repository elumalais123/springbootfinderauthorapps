package com.cts.authorfinder.controller;


import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cts.authorfinder.dto.Author;
import com.cts.authorfinder.dto.AuthorResponse;
import com.cts.authorfinder.dto.FavoriteResponse;
import com.cts.authorfinder.dto.ResponsePojo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/v1")
public class FavoritebServiceController {

	 //private static final Log log = LogFactory.getLog(FavoritebServiceController.class);
	
	//fetch data based 
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/searchauthors")
	public ResponseEntity<FavoriteResponse> searchAuthors() throws JsonMappingException, JsonProcessingException{
		System.out.println("Starts here searchAuthors ");
		
		FavoriteResponse resultJson=restTemplate.getForObject("https://openlibrary.org/search/authors.json?q=twain", FavoriteResponse.class);
		
		System.out.println("Ends here searchAuthors ");
		
		return new ResponseEntity<FavoriteResponse>(resultJson, HttpStatus.OK);
	}
	
	@PostMapping("/savemyfavorite")
	public ResponseEntity<String> saveFavoriteAuthor(@RequestParam("favoriteAuthor") String favoriteAuthor) throws JsonMappingException, JsonProcessingException{
		System.out.println("Starts here saveFavoriteAuthor() ");
		ResponsePojo resultJson=restTemplate.getForObject("https://openlibrary.org/search/authors.json?q="+favoriteAuthor, ResponsePojo.class);
		ObjectMapper objectMapper = new ObjectMapper();
		/*
		 * String str=objectMapper.writeValueAsString(resultJson); Map<String, String>
		 * map1 = objectMapper.readValue(str, new TypeReference<Map<String, String>>()
		 * {}); String numFound = map1.get("numFound"); String numFoundExact =
		 * map1.get("numFoundExact"); String docs = map1.get("docs");
		 */
		//List list = java.util.Arrays.asList(resultJson);
		
		 System.out.println("LIST ::::"+objectMapper);
		 System.out.println("result :::::"+resultJson.getDocs());
		 List<Author> docs = resultJson.getDocs();
		 for(Author author: docs) {
			 System.out.println(author);
		 }
		//System.out.println("Ends here saveFavoriteAuthor() resultJson :::"+resultJson);
		return new ResponseEntity<String>("Added the favorite List",HttpStatus.CREATED);
	}
}
