package tn.esprit.spring.services;
import java.util.List;

import tn.esprit.spring.entities.Contrat;

public interface IContratService {
	List<Contrat> retrieveAllContrats();
	Contrat addContrat(Contrat c);
	Contrat updateContrat(Contrat c);
	int deleteContrat(int id);
	Contrat retrieveContrat(int id);
	
	
	
}