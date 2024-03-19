package com.example.URLShortener.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.URLShortener.entity.URLS;

@Repository
public interface URLSRepository extends CrudRepository<URLS, String>
{
	@Query("select u from URLS u where u.shortenedBy = ?1")
	public List<URLS> getURLSByEmail(String email);
}
