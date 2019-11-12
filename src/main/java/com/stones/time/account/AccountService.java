package com.stones.time.account;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccountService {

	@Autowired
    AccountRepository accountRepository;

	@Autowired
    private ModelMapper modelMapper;
	
	public AccountDto.Response addUser(AccountDto.Create dto){
		log.debug("dto:{}", dto);
		Account newAccount = modelMapper.map(dto, Account.class);
		Account account = accountRepository.save(newAccount);
		AccountDto.Response result = modelMapper.map(account, AccountDto.Response.class);
		log.debug("result:{}", result);
		return result;
	}
	
	public List<AccountDto.Response> getUsers(){
		List<Account> userList = accountRepository.findAll();
		List<AccountDto.Response> users = modelMapper.map(userList, new TypeToken<List<AccountDto.Response>>(){}.getType());
		return users; 
	}
}
