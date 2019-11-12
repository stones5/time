package com.stones.time.account;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	AccountService accountService;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	public void testCreateUser() throws Exception {
		// given
		final AccountDto.Create account = AccountDto.Create.builder().name("stones").email("test@stones.com")
				.password("stones").build();

		BDDMockito.given(accountService.addUser(account))
				.willReturn(AccountDto.Response.builder().name(account.getName()).email(account.getEmail()).build());

		ResultActions result = mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(account)));

		result.andDo(print());
		result.andExpect(status().isCreated());
		result.andExpect(jsonPath("name").value(account.getName()));
		result.andExpect(jsonPath("email").value(account.getEmail()));
	}

}
