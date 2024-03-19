package com.example.URLShortener.data;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.URLShortener.entity.User;
import com.example.URLShortener.remote.UserRemote;
import com.example.URLShortener.repository.UserRepository;

@Service
public class UserData implements UserRemote
{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public String addUser(User user) 
	{
		if(!isUserAvailable(user.getEmail()))
		{
			userRepository.save(user);
			return "Registration Successfully, please login!";
		}
		return "User with this E-mail already exists";
	}

	@Override
	public User getByUsername(String email) {
		if(isUserAvailable(email))
		{
			return userRepository.findById(email).get();
		}
		return null;
	}
	
	public boolean isUserAvailable(String email)
	{
		try
		{
			userRepository.findById(email).get();
		}
		catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}
	
}
