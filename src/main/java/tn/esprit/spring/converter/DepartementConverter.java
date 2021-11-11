package tn.esprit.spring.converter;

import org.springframework.stereotype.Component;

import tn.esprit.spring.dto.DepartementDto;
import tn.esprit.spring.entities.Departement;



@Component
public class DepartementConverter {
	
	public DepartementDto entityToDto(Departement d) {
		return new DepartementDto(d.getId(),d.getName());
	}
	
	public static Departement dtoToEntity(DepartementDto d) {
		return new Departement(d.getId(),d.getName());
	}


}
