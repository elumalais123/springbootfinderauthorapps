package com.cts.authorfinder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Author {

	@JsonProperty("name")
	private String name;
	@JsonProperty("top_subjects")
	private String topSubjects[];
	@JsonProperty("key")
	private String key;
	@JsonProperty("top_work")
	private String topWork;
	@JsonProperty("type")
	private String type;
	@JsonProperty("work_count")
	private Integer workCount;
	@JsonProperty("_version_")
	private String _version_;
}
