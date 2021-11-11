package tn.esprit.spring.converter;

import org.springframework.stereotype.Component;

import tn.esprit.spring.dto.EmployeDto;
import tn.esprit.spring.entities.Employe;

@Component
public class EmployeConverter {
	public EmployeDto entityToDto(Employe employe)
	{
		return new EmployeDto(employe.getId(),employe.getNom(),
				employe.getPrenom(),employe.getEmail(),employe.isActif(),employe.getRole());
	}
	
	public Employe dtoToEntity(EmployeDto employeDto)
	{
		return new Employe(employeDto.getNom(),
				employeDto.getPrenom(),employeDto.getEmail(),employeDto.isActif(),employeDto.getRole());
	}
}
