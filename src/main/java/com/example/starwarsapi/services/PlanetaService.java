package com.example.starwarsapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.starwarsapi.model.Planeta;
import com.example.starwarsapi.model.SearchPlanet;
import com.example.starwarsapi.repository.PlanetaRepository;
import com.example.starwarsapi.services.exception.ObjectNotFoundException;

@Service
public class PlanetaService {
	
	@Autowired
	private PlanetaRepository planetaRepository;
	
	public List<Planeta> findAll(){
		return planetaRepository.findAll();
	}
	
	public Planeta findById(String id) {
		Optional<Planeta> planeta = planetaRepository.findById(id);
		return planeta.orElseThrow(() -> new ObjectNotFoundException("Planeta não encontrado no banco de dados"));
	}
	
	public Planeta findByNome(String nome) {
		Planeta planeta = planetaRepository.findByNome(nome);
		if(planeta == null) {
			throw new ObjectNotFoundException("Planeta não encontrado no banco de dados");
		}
		return planeta;
	}	
	
	public Planeta insert(Planeta planeta) {
		int aparicoes = buscarAparicoesPlaneta(planeta.getNome());
		planeta.setAparicoes(Integer.toString(aparicoes));
		return planetaRepository.insert(planeta);
	}
	
	public Planeta update(Planeta planeta) {
		Planeta newPlaneta = findById(planeta.getId());
		updateData(newPlaneta, planeta);
		return planetaRepository.save(newPlaneta);
	}
	
	private void updateData(Planeta newPlaneta, Planeta planeta) {
		newPlaneta.setNome(planeta.getNome());
		newPlaneta.setClima(planeta.getClima());
		newPlaneta.setTerreno(planeta.getTerreno());	
		int aparicoes = buscarAparicoesPlaneta(planeta.getNome());
		newPlaneta.setAparicoes(Integer.toString(aparicoes));
	}
	
	public void delete(String id) {
		findById(id);
		planetaRepository.deleteById(id);
	}
	
	public Integer buscarAparicoesPlaneta(String nome) {
		int quantidadeDeAparicoes = 0;
		SearchPlanet searchPlanet = new RestTemplate().getForObject("https://swapi.dev/api/planets/?search=" + nome, SearchPlanet.class);	
		if(searchPlanet.getCount() == 0) {	
			throw new ObjectNotFoundException("Planeta não reconhecido no universo Star Wars");
		}else {		
			quantidadeDeAparicoes = searchPlanet.getResults().get(0).getFilms().size();
		}
		
		return quantidadeDeAparicoes;			
	}

}
