package com.cts.favorite.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Favorite {


	@JsonProperty("key")
	private String key;
	@JsonProperty("name")
	private String name;
	@JsonProperty("type")
	private String type;	
	@JsonProperty("top_subjects")
	private String topSubjects[];
	@JsonProperty("top_work")
	private String topWork;
	@JsonProperty("work_count")
	private Integer workCount;
	@JsonProperty("birth_date")
	private String birth_date;
	@JsonProperty("date")
	private String date;
	@JsonProperty("death_date")
	private String death_date;
	@JsonProperty("_version_")
	private String _version_;
}
