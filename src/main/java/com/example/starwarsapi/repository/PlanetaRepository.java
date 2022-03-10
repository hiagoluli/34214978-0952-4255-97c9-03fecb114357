package com.example.starwarsapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.starwarsapi.model.Planeta;

@Repository
public interface PlanetaRepository extends MongoRepository<Planeta, String> {
	
	public Planeta findByNome(String nome);

}
