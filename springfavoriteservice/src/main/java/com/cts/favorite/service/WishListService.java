package com.cts.favorite.service;

import java.util.List;

import com.cts.favorite.model.Wishlist;

public interface WishListService {

    List<Wishlist> getUserWishlist(String username);
	Wishlist saveWishList(String username, Wishlist auther);//String username,
    Integer deleteWishlist(String username, Long id);
}
