package com.example.starwarsapi.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.starwarsapi.model.Planeta;
import com.example.starwarsapi.repository.PlanetaRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private PlanetaRepository planetaRepository;
	
	public void run(String... args) throws Exception {
		planetaRepository.deleteAll();
		
		Planeta dagobah = new Planeta("1", "Dagobah", "Murky", "Pantanos", "1");
		Planeta hoth = new Planeta("2", "Hoth", "Gelado", "Neve", "2");
		Planeta endor = new Planeta("3", "Endor", "Murky", "Florestal", "4");
	
		planetaRepository.saveAll(Arrays.asList(dagobah, hoth, endor));
	}
}
