package com.stones.time.account;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

	@Autowired
    AccountService accountService;

	@Test
	public void addUser() {
		final AccountDto.Create newAccount = AccountDto.Create.builder().name("stones").email("test@stones.com")
				.password("stones").build();
		AccountDto.Response account = accountService.addUser(newAccount);
		assertEquals(newAccount.getName(), account.getName());
		assertEquals(newAccount.getEmail(), account.getEmail());
	}

}
