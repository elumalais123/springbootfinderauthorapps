package com.cts.favorite.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cts.favorite.exceptions.ResourceNotFoundException;
import com.cts.favorite.model.Wishlist;
import com.cts.favorite.repository.WishListRepository;

import lombok.extern.java.Log;
@Service
public class WishListServiceImpl implements WishListService{
    private Logger log= LoggerFactory.getLogger(WishListServiceImpl.class);
    @Autowired
    WishListRepository wishListRepository;

    @Cacheable(cacheNames="wishlistCache", key="#p0")
    
    @Override
    public List<Wishlist> getUserWishlist(String username) {
    	log.info("Getting WhishList from DB");
        List<Wishlist> usrWishList= wishListRepository.findByUsername(username) ;
                if(usrWishList!=null){
                    return  usrWishList;

                }else {
                    throw new ResourceNotFoundException("Entity not found with userProfile ID: " + username);

                }
                


    }
    
    public Wishlist saveWishList(String username, Wishlist auther) {
        System.out.println(" User Name :"+ username +" ===" + "WishList ::"+auther.toString());
        log.info(" User Name :"+ username +" ===" + "WishList ::"+auther.toString());
        Wishlist wl=new Wishlist();
        wl.setUsername(username);
        wl.setAuthor_key(auther.getAuthor_key());
        wl.setType(auther.getType());
        wl.setName(auther.getName());
        wl.setTop_work(auther.getTop_work());
        wl.setWork_count(auther.getWork_count());
        wl.setTop_subject(auther.getTop_subject());

        Wishlist result=null;
        if (username!=null){
              result  =wishListRepository.save(wl);
        }

        return result;
    }
    @Override
    public Integer deleteWishlist(String username, Long id) {
        log.info("Service deleteByUsername: " + username);

        wishListRepository.deleteById(id);

        return 1;
    }    
}
