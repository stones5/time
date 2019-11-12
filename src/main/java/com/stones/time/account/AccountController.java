package com.stones.time.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/users")
public class AccountController {

	@Autowired
    AccountService accountService;

	@PostMapping
	public ResponseEntity<AccountDto.Response> addUser(@RequestBody AccountDto.Create dto){
		log.debug("{}", dto);
		AccountDto.Response account = accountService.addUser(dto);
		return new ResponseEntity<>(account, HttpStatus.CREATED);
	}
	
    @GetMapping
    public List<AccountDto.Response> getUsers() {
        return accountService.getUsers();
    }
}
