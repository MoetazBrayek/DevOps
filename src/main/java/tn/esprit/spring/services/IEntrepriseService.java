package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Departement;

import tn.esprit.spring.entities.Entreprise;

public interface IEntrepriseService {
	
	public int ajouterEntreprise(Entreprise entreprise);
	public int ajouterDepartement(Departement dep);
	void affecterDepartementAEntreprise(int depId, int entrepriseId);
	List<String> getAllDepartementsNamesByEntreprise(int entrepriseId);
	public void deleteEntrepriseById(int entrepriseId);
	public int getNombreEntrepriseJPQL();
	public void deleteDepartementById(int depId);
	public List<Entreprise> getAllEntreprise();
	public Entreprise getEntrepriseById(int entrepriseId);
	public String getEntrepriseNameById(int entrepriseId);
}
