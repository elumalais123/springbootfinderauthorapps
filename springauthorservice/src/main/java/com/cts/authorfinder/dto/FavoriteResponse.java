package com.cts.authorfinder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FavoriteResponse {


	@JsonProperty("numFound")
	private int numFound;
	@JsonProperty("start")
	private int start;
	@JsonProperty("numFoundExact")
	private boolean numFoundExact;
	@JsonProperty("docs")
	private Favorite[] docs;
}
