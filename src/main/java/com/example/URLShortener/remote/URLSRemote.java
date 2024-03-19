package com.example.URLShortener.remote;

import java.util.List;

import com.example.URLShortener.entity.URLS;

public interface URLSRemote 
{
	public boolean checkIfAvailable(String shortURLIdentifier);
	public URLS getByURL(String shortURLIdentifier);
	public String addNewURL(URLS urls);
	public List<URLS> getShortenedURLsByUser(String email);
	public void updateNewURL(URLS urls);
}
