package com.stones.time.account;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountRepositoryTest {

	@Autowired
    AccountRepository accountRepository;
	
	@Test
	public void test01() {
		Account newAccount = Account.builder().name("test").email("test@stones.com").password("test").build();
		Account account = accountRepository.save(newAccount);
		assertEquals(newAccount.getName(), account.getName());
		assertEquals(newAccount.getEmail(), account.getEmail());
	}
	
	@Test
	public void test02() {
		Account newAccount = Account.builder().name("stone").email("stone@stones.com").password("test").build();
		accountRepository.save(newAccount);
		Optional<Account> account = accountRepository.findByEmail(newAccount.getEmail());
		assertEquals(newAccount.getEmail(), account.isPresent()?account.get().getEmail():"");
	}

}
