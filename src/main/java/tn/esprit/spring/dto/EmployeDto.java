package tn.esprit.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import tn.esprit.spring.entities.Role;

@Data
@AllArgsConstructor
public class EmployeDto {
	private int id;
	private String prenom;
	private String nom;
	private String email;
	private boolean isActif;
	private Role role;
	
}
