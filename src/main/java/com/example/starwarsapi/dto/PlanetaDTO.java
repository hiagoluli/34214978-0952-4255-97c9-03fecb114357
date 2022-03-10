package com.example.starwarsapi.dto;

import java.io.Serializable;

import com.example.starwarsapi.model.Planeta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanetaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String nome;
	private String clima;
	private String terreno;
	private String aparicoes;
	
	public Planeta convertToDTO(PlanetaDTO planetaDto) {
		return new Planeta(planetaDto.getId(), planetaDto.getNome(), planetaDto.getClima(), planetaDto.getTerreno(), planetaDto.getAparicoes());
	}
	
	public PlanetaDTO(Planeta planeta) {
		id = planeta.getId();
		nome = planeta.getNome();
		clima = planeta.getClima();
		terreno = planeta.getTerreno();
		aparicoes = planeta.getAparicoes();
	}	

}
