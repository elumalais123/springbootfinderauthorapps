package com.cts.authorfinder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AuthorResponse {

	@JsonProperty("docs")
	private Author[] docs;
	@JsonProperty("numFound")
	private int numFound;
	@JsonProperty("numFoundExact")
	private boolean numFoundExact;
}
