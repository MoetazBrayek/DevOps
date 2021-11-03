package tn.esprit.spring.services;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {
	
	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	private static final Logger l = Logger.getLogger(EntrepriseServiceImpl.class);	

	
	public int ajouterEntreprise(Entreprise entreprise) {
		l.info("lancer la methode ajouter entreprise");
		l.debug("je vais lancer save de l'entreprise");
	if(entreprise.getName()==null || entreprise.getRaisonSocial()==null)	
	{
	l.debug("l'ajout n'apas pu etre effectué");
	l.info("fin de  la methode ajouter entreprise");
	return 0;
	}
	else{
		entrepriseRepoistory.save(entreprise);
		l.debug("je viens de finir save de l'entreprise");
		l.info("fin de  la methode ajouter entreprise");
		return entreprise.getId();
		}	
	}

	public int ajouterDepartement(Departement dep) {
		l.info("lancer  la methode ajouter departement");
		l.debug("je vais lancer la methode save du departement");
		
		deptRepoistory.save(dep);
		
		l.debug("je viens de finir save de departement");
		l.info("fin de  la methode ajouter departement");
		return dep.getId();
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		
		l.info("lancer  la methode affectation departement a entreprise");
		l.debug("je vais lancer la recherche de l'entreprise par id ");
		Optional<Entreprise> value = entrepriseRepoistory.findById(entrepriseId);
		if(value.isPresent())
		{Entreprise entrepriseManagedEntity = value.get();
				
		l.debug("je viens de trouver l'entreprise" + entrepriseManagedEntity);
		l.debug("je vais lancer la recherche du departement par id ");
		Optional<Departement> value1 = deptRepoistory.findById(depId);
		if(value1.isPresent())
		{Departement depManagedEntity=value1.get();
			
			
		l.debug("je viens de trouver le departement" + depManagedEntity);
		l.debug("je vais lancer l'update de l'ntreprise et l'enregistré");	
		
				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				deptRepoistory.save(depManagedEntity);
				
		l.debug("je viens de faire l'update l'update de l'ntreprise et l'enregistré");	
		l.info("fin de   la methode affectation departement a entreprise");
		
	}}
		else{l.debug("l'entreprise ou departement n'exite pas");	
		l.info("fin de   la methode affectation departement a entreprise");
			
		}
		}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		l.info("lancer  la methode get all department names by entreprise");
		l.debug("lancer  la recherche de l entreprise par id");
		Optional<Entreprise> value = entrepriseRepoistory.findById(entrepriseId);
		if (value.isPresent()) 
		
		{Entreprise entrepriseManagedEntity= value.get();
			
		l.debug("je viens de trouver l entreprise" +entrepriseManagedEntity);
		List<String> depNames = new ArrayList<>();
		l.debug("je vais lancer  la boucle sur tous les departements et ajouter le nom du departementt au tableau depNames");
		
		for(Departement dep : entrepriseManagedEntity.getDepartements()){
			depNames.add(dep.getName());
		}
		
		l.debug("je viens de remplir le tableau depNames");
		l.info("fin de   la methode get all department names by entreprise");
		return depNames;
		}
		else
		{l.debug("l'entreprisee n'existe pas");
		l.info("fin de   la methode get all department names by entreprise");
		
		return new ArrayList<>();
		}
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		l.info("lancer  la methode delete entreprise by id");
		l.debug("je vais lancer  la methode delete entreprise by id");
		Optional<Entreprise> value = entrepriseRepoistory.findById(entrepriseId);
		if (value.isPresent()) {
			Entreprise ent=value.get();
			entrepriseRepoistory.delete(ent);	
			
			l.debug("je viens de finir la delete entreprise by id");
			l.info("finb de   la methode delete entreprise by id");	
		}
		else {l.debug("l'entreprise n'existe pass");
		l.info("finb de   la methode delete entreprise by id");	
			
		}
		
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		l.info("lancer  la methode delete department by id");
		l.debug("je vais lancer  la methode delete departement by id");
		Optional<Departement> value = deptRepoistory.findById(depId);
		if (value.isPresent()) {
			Departement dep=value.get();
		deptRepoistory.delete(dep);
		
		l.debug("je viens de finir la delete departement by id");
		l.info("fin de  la methode delete department by id");
		}
		else {
			l.debug("le departement n'existe pas");
			l.info("fin de  la methode delete department by id");
		}
	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		l.info("lancer  la methode get entreprise by id");
		l.debug("je vais lancer  la recherche de l'entreprise par id");
		Optional<Entreprise> value = entrepriseRepoistory.findById(entrepriseId);
		if (value.isPresent()) {
			Entreprise ent=value.get();
			
		l.debug("je viens de trouver l'entreprise par id"+ent);
		l.info("fin de   la methode get entreprise by id");
		 return ent;
		}
		else {l.debug("l'entreprise n'existeee pas");
		l.info("fin de   la methode get entreprise by id"); 
		return null;}
		
		
		
		
		
	}

}
