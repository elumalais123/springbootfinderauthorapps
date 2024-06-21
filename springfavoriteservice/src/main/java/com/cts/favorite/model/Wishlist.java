package com.cts.favorite.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="wishlist")
public class Wishlist  implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private  String author_key;
    private  String type;
    private String name;
    private String top_work;
    private Integer work_count;
    private  String top_subject;
    
    
    public Wishlist(Long id, String username, String author_key, String type, String name, String top_work,
			Integer work_count, String top_subject) {
		super();
		this.id = id;
		this.username = username;
		this.author_key = author_key;
		this.type = type;
		this.name = name;
		this.top_work = top_work;
		this.work_count = work_count;
		this.top_subject = top_subject;
	}

	public Wishlist(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }





    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor_key() {
		return author_key;
	}

	public void setAuthor_key(String author_key) {
		this.author_key = author_key;
	}

	public void setWork_count(Integer work_count) {
		this.work_count = work_count;
	}

	public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTop_work() {
        return top_work;
    }

    public void setTop_work(String top_work) {
        this.top_work = top_work;
    }

    public int getWork_count() {
        return work_count;
    }

    public void setWork_count(int work_count) {
        this.work_count = work_count;
    }

    public String getTop_subject() {
        return top_subject;
    }

    public void setTop_subject( String top_subject) {
        this.top_subject = top_subject;
    }
}