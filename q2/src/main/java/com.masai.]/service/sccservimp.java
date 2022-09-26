package com.masai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.AccountException;
import com.masai.exception.InsufficientFundException;
import com.masai.model.Account;
import com.masai.model.AccountDTO;
import com.masai.repo.accountdao;


@Service
public class sccservimp implements Accserv{

	@Autowired
	private accountdao accdao;
	
	@Override
	public Account openAccount(Account acc) throws AccountException {
		  return accdao.save(acc);
	}

	@Override
	public Account closeAccount(Integer accno) throws AccountException {
		if(accdao.findById(accno).isPresent()) {
			Account em=accdao.findById(accno).get();
			accdao.delete(em);
			
			return em;
		}
		else
			throw new AccountException("Not Avaialable");
	}

	@Override
	public Account depositAmount(Integer accno, Integer amount) throws AccountException {
		if(accdao.findById(accno).isPresent()) {
			Account em=accdao.findById(accno).get();
			 em.setBalance(em.getBalance()+amount);
			
			return em;
		}
		else
			throw new AccountException("Not Avaialable");
		
	}

	@Override
	public Account withdrawAmmount(int accno, Integer amount) throws AccountException, InsufficientFundException {
		if(accdao.findById(accno).isPresent()) {
			Account em=accdao.findById(accno).get();
			 if(em.getBalance()>amount) {
				 em.setBalance(em.getBalance()-amount);
				 return em;
			 }
			 else
				 throw new InsufficientFundException("fund not avaialble");
			
		}
		else
			throw new AccountException("Not Avaialable");
	}

	@Override
	public String transferAmount(AccountDTO dto) throws AccountException, InsufficientFundException {
		if(accdao.findById(dto.getSrcAccno()).isPresent()) {
			Account em=accdao.findById(dto.getSrcAccno()).get();
			 if(em.getBalance()>dto.getAmount() && accdao.findById(dto.getDesAccno()).isPresent()) {
				 em.setBalance(em.getBalance()-dto.getAmount());
				 em.setBalance(em.getBalance()+dto.getAmount());
				 
				 return "Sucessfully Transfer";
			 }
			 else
				 throw new InsufficientFundException("fund not avaialble");
			
		}
		else
			throw new AccountException("Not Avaialable");
	}

}
