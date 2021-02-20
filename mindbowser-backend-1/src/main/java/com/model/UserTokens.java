package com.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user_tokens")
public class UserTokens implements Serializable {

	private static final long serialVersionUID = 7083838528525974420L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "token_id")
	private Long tokenId;

	@Column(name = "user_no", length = 100)
	private long userNo;

	@Column(name = "token", columnDefinition = "LONGTEXT DEFAULT NULL")
	private String token;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "generated_time")
	private Date generatedTime;

	public Long getTokenId() {
		return tokenId;
	}

	public void setTokenId(Long tokenId) {
		this.tokenId = tokenId;
	}

	public long getUserNo() {
		return userNo;
	}

	public void setUserNo(long l) {
		this.userNo = l;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getGeneratedTime() {
		return generatedTime;
	}

	public void setGeneratedTime(Date generatedTime) {
		this.generatedTime = generatedTime;
	}
}