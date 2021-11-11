package tn.esprit.spring.converter;

import org.springframework.stereotype.Component;

import tn.esprit.spring.dto.ContratDto;
import tn.esprit.spring.entities.Contrat;

@Component
public class ContratConverter {
	public ContratDto entityToDto(Contrat contrat)
	{
		return new ContratDto(contrat.getReference(),contrat.getDateDebut(),
				contrat.getTypeContrat(), contrat.getSalaire());
	}
	
	public Contrat dtoToEntity(ContratDto contratDto)
	{
		return new Contrat(contratDto.getDateDebut(),contratDto.getType(),contratDto.getSalaire());
	}
}
