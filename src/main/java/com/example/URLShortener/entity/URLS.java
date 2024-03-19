package com.example.URLShortener.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "urlDetails")
public class URLS 
{
	@Id
	@Column(length = 255)
	private String shorturl;
	@Column(nullable = false, unique = false, length = 255)
	private String url;
	@Column(nullable = false, unique = false, length = 255)
	private String shortenedBy;
	@Column(nullable = false, unique = false)
	private int accessedCount;
	public String getShorturl() {
		return shorturl;
	}
	public void setShorturl(String shorturl) {
		this.shorturl = shorturl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getShortenedBy() {
		return shortenedBy;
	}
	public void setShortenedBy(String shortenedBy) {
		this.shortenedBy = shortenedBy;
	}
	public int getAccessedCount() {
		return accessedCount;
	}
	public void setAccessedCount(int accessedCount) {
		this.accessedCount = accessedCount;
	}
	
}
