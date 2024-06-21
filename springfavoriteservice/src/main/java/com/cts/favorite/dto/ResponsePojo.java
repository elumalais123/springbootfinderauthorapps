package com.cts.favorite.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponsePojo {
   @JsonProperty("docs") // only required, if fieldName != jsonFieldName
   private List<Author> docs;

public List<Author> getDocs() {
	return docs;
}

public void setDocs(List<Author> docs) {
	this.docs = docs;
} 

/* getter & setter ommitted */
   
   
}