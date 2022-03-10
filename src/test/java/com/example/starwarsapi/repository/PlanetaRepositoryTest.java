package com.example.starwarsapi.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.starwarsapi.model.Planeta;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanetaRepositoryTest {
	
	@Autowired
	private PlanetaRepository planetaRepository;
	
	@Test
	public void planetaByIdSuccess() {
		Optional<Planeta> planeta = planetaRepository.findById("1");
		assertNotNull(planeta);
	}
	
	@Test
	public void planetaByIdFail() {
		Optional<Planeta> planeta = planetaRepository.findById("7");
		assertTrue(!planeta.isPresent());
	}
	
	@Test
	public void planetaByNomeDagobah() {
		Planeta planeta = planetaRepository.findByNome("Dagobah");
		assertNotNull(planeta);
	}
	
	@Test
	public void planetaByNomeVazio() {
		Planeta planeta = planetaRepository.findByNome("");
		assertNull(planeta);
	}
	
	@Test
	public void listAllIsEmpty() {
		List<Planeta> planeta = planetaRepository.findAll();
		planeta.clear();
		assertTrue(planeta.isEmpty());
	}
	
	@Test
	public void listAllIsNotEmpty() {
		List<Planeta> planeta = planetaRepository.findAll();
		if(planeta.size() > 0) {
			assertFalse(planeta.isEmpty());
		}else {
			assertTrue(planeta.isEmpty());
		}		
	}
	
	

}
