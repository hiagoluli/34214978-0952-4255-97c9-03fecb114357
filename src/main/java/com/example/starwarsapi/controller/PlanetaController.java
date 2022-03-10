package com.example.starwarsapi.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.starwarsapi.dto.PlanetaDTO;
import com.example.starwarsapi.model.Planeta;
import com.example.starwarsapi.services.PlanetaService;

@RestController
@RequestMapping(value="/planetas")
public class PlanetaController {
	
	@Autowired
	private PlanetaService planetaService;
	
	@GetMapping
	public ResponseEntity<List<PlanetaDTO>> findAll() {
		List<Planeta> list = planetaService.findAll();
		List<PlanetaDTO> listDTO = list.stream().map(planeta -> new PlanetaDTO(planeta)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<PlanetaDTO> findById(@PathVariable String id) {
		Planeta planeta = planetaService.findById(id);
		return ResponseEntity.ok().body(new PlanetaDTO(planeta));
	}
	
	@GetMapping(value="/nome/{nome}")
	public ResponseEntity<PlanetaDTO> findByNome(@PathVariable String nome) {
		Planeta planeta = planetaService.findByNome(nome);
		return ResponseEntity.ok().body(new PlanetaDTO(planeta));
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody PlanetaDTO planetaDto) {
		PlanetaDTO planetaDtoInstance = new PlanetaDTO();
		Planeta planeta = planetaDtoInstance.convertToDTO(planetaDto);	
		planeta = planetaService.insert(planeta);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(planeta.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@RequestBody PlanetaDTO planetaDto, @PathVariable String id) {
		PlanetaDTO planetaDtoInstance = new PlanetaDTO();
		Planeta planeta = planetaDtoInstance.convertToDTO(planetaDto);
		planeta.setId(id);
		planeta = planetaService.update(planeta);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		planetaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}

	
