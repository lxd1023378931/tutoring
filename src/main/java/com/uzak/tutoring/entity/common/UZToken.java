package com.uzak.tutoring.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.uzak.tutoring.common.dao.Dao;

@SuppressWarnings("serial")
@Entity
@Table(name = "UZToken")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class UZToken extends Dao {
	@Id
	@Column(length = 200)
	private String type;
	@Id
	@Column(length = 200)
	private String token;
	@Column
	private long expireTime;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}
	
	public void getValue() {
		
	}
}
