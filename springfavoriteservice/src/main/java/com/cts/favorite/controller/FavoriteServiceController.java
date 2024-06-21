package com.cts.favorite.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cts.favorite.dto.Author;
import com.cts.favorite.dto.FavoriteResponse;
import com.cts.favorite.dto.ResponsePojo;
import com.cts.favorite.exceptions.UnAuthorizedException;
import com.cts.favorite.model.Wishlist;
import com.cts.favorite.service.WishListService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class FavoriteServiceController {
	private Logger log = LoggerFactory.getLogger(FavoriteServiceController.class);
	@Autowired
	AuthorizationConsumer authorizationConsumer;

	@Autowired
	WishListService wishListService;
	
	@Autowired
	private RestTemplate restTemplate;

	  @Autowired
	  private CacheManager cacheManager;

	@PostMapping("/searchauthors")
	public ResponseEntity<FavoriteResponse> searchauthors(@RequestHeader("Authorization") String token,
			@RequestParam("favoriteAuthor") String favoriteAuthor) {
		System.out.println("Starts here searchAuthors ");
		String userToken = getUsername(token);
		FavoriteResponse resultJson=restTemplate.getForObject("https://openlibrary.org/search/authors.json?q=twain", FavoriteResponse.class);
		
		System.out.println("Ends here searchAuthors ");
		
		return new ResponseEntity<FavoriteResponse>(resultJson, HttpStatus.OK);
	}
	@PostMapping("/savemyfavorite")
	public ResponseEntity<List<Author>> savemyfavorite(@RequestHeader("Authorization") String token,
			@RequestParam("favoriteAuthor") String favoriteAuthor) {
		System.out.println("Starts here saveFavoriteAuthor() ");
		ResponsePojo resultJson=restTemplate.getForObject("https://openlibrary.org/search/authors.json?q="+favoriteAuthor, ResponsePojo.class);
		
		 
		 System.out.println("result :::::"+resultJson.getDocs());
		 List<Author> docs = resultJson.getDocs();
	
		 return new ResponseEntity<List<Author>>(docs,HttpStatus.CREATED);
		

	}
	@PostMapping("/addWishlist")
	public ResponseEntity<String> saveWishList(@RequestHeader("Authorization") String token,
			@RequestParam("username") String username, @RequestBody Wishlist wishlist) {
		log.trace("Controller saveTrackToWishList: " + username);
		System.out.println("token First    ::" + token);
		String userToken = getUsername(token);
		System.out.println(" userToken ::" + userToken);
		Wishlist lists = wishListService.saveWishList(username, wishlist);
		if (userToken.equalsIgnoreCase(username)) {
			log.error(token + "inside method get all-----__---");
			return ResponseEntity.ok("Wishlist added successfully");
		}
		throw new UnAuthorizedException("Please  check User Id: " + username);

	}

	@PostMapping("/userWishList")
	public ResponseEntity<List<Wishlist>> getUserWishlist(@RequestHeader("Authorization") String token,
			@RequestParam("username") String username) {
		System.out.println("\n\n\ngetUserWishlist    ::" + token);
		Cache cache = cacheManager.getCache("wishlistCache");
		
		String value = cache.getName();
		System.out.println("VALUE :::"+value);
		return new ResponseEntity<List<Wishlist>>(wishListService.getUserWishlist(username), HttpStatus.OK);

	}

	@DeleteMapping("/deletewhishlist")
	public ResponseEntity<Object> deleteWishlist(@RequestHeader("Authorization") String token,
			@RequestParam String username, @RequestParam Long id) {
		log.trace("Controller Delete WishList: " + username);
		String userDele=new String();
		String userToken = getUsername(token);
		log.info("------inside method Delelte WishList-----");
		if (userToken.equalsIgnoreCase(username)) {
			log.error(token + "inside method get all-----__---");
			Integer deleteWishlist = wishListService.deleteWishlist(username, id);
			if(deleteWishlist>=0) {
				userDele="User deleted successfully";
			}else {
				userDele="User Not deleted successfully";
			}
			return new ResponseEntity<>(userDele, HttpStatus.ACCEPTED);
		}
		throw new UnAuthorizedException("Please  check User Id: " + username);

	}

	private String getUsername(String token) {
		return authorizationConsumer.extractUserName(token);
	}
}
