package com.example.starwarsapi.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanetaControllerTest {
	
	@Autowired
	public WebApplicationContext context;
	
	private MockMvc mvc;
	
	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Test
	public void RequestFindByIdSuccess() throws Exception {
		String url = "/planetas/1";
		this.mvc.perform(get(url))
		.andExpect(status().isOk());
	}
	
	@Test
	public void RequestFindByNomeSuccess() throws Exception {
		String url = "/planetas/nome/Dagobah";
		this.mvc.perform(get(url))
		.andExpect(status().isOk());
	}
	
	@Test
	public void RequestFindByNomeFail() throws Exception {
		String url = "/planetas/nome/test";
		this.mvc.perform(get(url))
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void RequestFindByIdFail() throws Exception {
		String url = "/planetas/1000";
		this.mvc.perform(get(url))
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void RequestDeleteSuccess() throws Exception {
		String url = "/planetas/1";
		this.mvc.perform(delete(url))
		.andExpect(status().isNoContent());	
	}
	
	@Test
	public void RequestDeleteFail() throws Exception {
		String url = "/planetas/10000";
		this.mvc.perform(delete(url))
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void RequestInsert() throws Exception {
		String url = "/planetas";
		this.mvc.perform(post(url)
		.content("{\"nome\":\"Tatooine\",\"clima\":\"Arid\",\"terreno\":\"desert\"}")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());	
	}
	
	@Test
	public void RequestUpdateSuccess() throws Exception {
		String url = "/planetas/2";
		this.mvc.perform(put(url)
		.content("{\"nome\":\"Tatooine\",\"clima\":\"Arid\",\"terreno\":\"desert\"}")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNoContent());	
	}
	
	@Test
	public void RequestUpdateFail() throws Exception {
		String url = "/planetas/1000";
		this.mvc.perform(put(url)
		.content("{\"nome\":\"Tatooine\",\"clima\":\"Arid\",\"terreno\":\"desert\"}")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());	
	}

}
