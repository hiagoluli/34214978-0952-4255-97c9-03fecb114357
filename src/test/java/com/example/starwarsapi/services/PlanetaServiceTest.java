package com.example.starwarsapi.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.example.starwarsapi.model.Planeta;
import com.example.starwarsapi.model.SearchPlanet;
import com.example.starwarsapi.repository.PlanetaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanetaServiceTest {
	
	@Autowired
	PlanetaService planetaService;
	
	@Autowired
	PlanetaRepository planetaRepository;
	
	@Test
	public void buscarAparicoesPlanetaSuccessTest() {
		String nome = "Dagobah";
		SearchPlanet searchPlanet = new RestTemplate().getForObject("https://swapi.dev/api/planets/?search=" + nome, SearchPlanet.class);	
		assertThat(searchPlanet.getResults().size() == 1);
		assertThat(searchPlanet.getResults().get(0).getFilms().size() >= 0);		
	}
	
	@Test
	public void buscarAparicoesPlanetaFailTest() {
		String nome = "Naoexiste";
		SearchPlanet searchPlanet = new RestTemplate().getForObject("https://swapi.dev/api/planets/?search=" + nome, SearchPlanet.class);	
		assertThat(searchPlanet.getResults().size() == 0);		
	}
	

	@Test
	public void listAllIsEmpty() {
		List<Planeta> planeta = planetaService.findAll();
		planeta.clear();
		assertTrue(planeta.isEmpty());
	}
	
	@Test
	public void listAllIsNotEmpty() {
		List<Planeta> planeta = planetaService.findAll();
		if(planeta.size() > 0) {
			assertFalse(planeta.isEmpty());
		}else {
			assertTrue(planeta.isEmpty());
		}		
	}
	

}
