package com.cts.authorfinder.entities;

import java.util.ArrayList;

public class FavoriteEntity {

	public Integer id;
	public String name;
    public String key;
    public String birth_date;
    public String title;    
    public String bio;    
    public ArrayList<String> alternate_names;    
    public String wikipedia;
    public String personal_name;
    public String entity_type;
    public ArrayList<String> source_records;
    public String fuller_name;    
    public int latest_revision;
    public int revision;
    public boolean populateWishList;
}
