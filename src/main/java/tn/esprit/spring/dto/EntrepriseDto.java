package tn.esprit.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntrepriseDto {

	private int id;

	private String name;

	private String raisonSocial;

}
