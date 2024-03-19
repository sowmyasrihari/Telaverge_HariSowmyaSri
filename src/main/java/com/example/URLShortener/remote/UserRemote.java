package com.example.URLShortener.remote;

import com.example.URLShortener.entity.User;

public interface UserRemote 
{
	public String addUser(User user);
	public User getByUsername(String email);
	public boolean isUserAvailable(String email);
}
