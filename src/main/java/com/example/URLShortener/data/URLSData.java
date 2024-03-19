package com.example.URLShortener.data;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.URLShortener.entity.URLS;
import com.example.URLShortener.remote.URLSRemote;
import com.example.URLShortener.repository.URLSRepository;

@Service
public class URLSData implements URLSRemote
{
	@Autowired
	private URLSRepository urlsRepository;
	
	@Override
	public boolean checkIfAvailable(String shortURLIdentifier) {
		try
		{
			urlsRepository.findById(shortURLIdentifier).get();
		}
		catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}

	String randomStringGenerator()
	{
		Random random = new Random();
		String s = "qwertyuiopasdfghjklzxcvbnm1234567890";
		int n = 7;
		String rand = "";
		while(n-- > 0)
		{
			rand += s.charAt(random.nextInt(s.length()));
		}
		return (!checkIfAvailable(rand)) ? rand : randomStringGenerator();
	}
	
	@Override
	public URLS getByURL(String shortURLIdentifier) {
		if(checkIfAvailable(shortURLIdentifier)) {
			return urlsRepository.findById(shortURLIdentifier).get();
		}
		return null;
	}

	@Override
	public String addNewURL(URLS urls) {
		String s = randomStringGenerator();
		urls.setShorturl(s);
		urlsRepository.save(urls);
		return s;
	}
	
	@Override
	public void updateNewURL(URLS urls) {
		urlsRepository.save(urls);
	}

	@Override
	public List<URLS> getShortenedURLsByUser(String email) {
		return urlsRepository.getURLSByEmail(email);
	}
	
}
