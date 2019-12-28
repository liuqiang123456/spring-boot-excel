package com.example.springbootexcel.dao;

import com.example.springbootexcel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
