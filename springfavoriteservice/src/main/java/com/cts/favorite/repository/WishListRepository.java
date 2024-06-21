package com.cts.favorite.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.favorite.model.Wishlist;

@Repository
public interface WishListRepository extends JpaRepository<Wishlist,Long> { 

    public List<Wishlist> findByUsername(String username);

}