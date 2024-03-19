package com.example.URLShortener.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.URLShortener.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String>
{
	
}
