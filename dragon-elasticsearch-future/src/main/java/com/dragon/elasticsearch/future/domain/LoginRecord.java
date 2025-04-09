package com.dragon.elasticsearch.future.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LoginRecord {
	private long recordID;
	private long userID;
	private long loginTime;
	private byte deviceType;
	private byte clientType;
	private String deviceInfo;
	private String macCode;
	private String loginIP;
	private String loginCity;
	@JsonProperty("SDKID")
	private long SDKID;
	private byte status;
	private String clientVersion;
}
