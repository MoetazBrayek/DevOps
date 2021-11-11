package tn.esprit.spring.converter;

import org.springframework.stereotype.Component;

import tn.esprit.spring.dto.EntrepriseDto;
import tn.esprit.spring.entities.Entreprise;

@Component
public class EntrepriseConverter {
	public EntrepriseDto entityToDto(Entreprise e) {
		return new EntrepriseDto(e.getId(),e.getName(),e.getRaisonSocial());
	}
	
	public static Entreprise dtoToEntity(EntrepriseDto e) {
		return new Entreprise(e.getId(),e.getName(),e.getRaisonSocial());
	}

}
