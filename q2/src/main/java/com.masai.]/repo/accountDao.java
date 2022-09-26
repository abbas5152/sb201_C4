package com.masai.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Account;


public interface accountdao extends JpaRepository<Account,Integer>{

}
