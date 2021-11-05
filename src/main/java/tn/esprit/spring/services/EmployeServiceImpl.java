package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class EmployeServiceImpl implements IEmployeService {

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;
	
	private static final Logger l = Logger.getLogger(EmployeServiceImpl.class);	

	public int ajouterEmploye(Employe employe) {
		l.info("lancer la methode ajouter employe");
		l.debug("je vais lancer save de l'employe");
		employeRepository.save(employe);
		l.debug("je viens de finir save de l'employe");
		l.info("fin de  la methode ajouter employe");
		return employe.getId();
	}

	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		l.info("lancer la methode mettre à jour email by employe id");
		l.debug("je vais mettre à jour email de l'employe");
	Optional<Employe> value = employeRepository.findById(employeId);
	if(value.isPresent()) {
		Employe employe=value.get();
		employe.setEmail(email);
		employeRepository.save(employe);
	}else {
		l.debug("je viens de mettre à jour email de l'employe");
		l.info("fin de  la methode mettre à jour email by employe id");
	}	

	}


	@Transactional
	public void affecterEmployeADepartement(int employeId, int depId) {
		l.info("lancer la methode affecter employe à un departement");
		l.debug("je vais affecter employe à un departement");
		Optional<Departement> value = deptRepoistory.findById(depId);
		if(value.isPresent())
		{
			Departement depManagedEntity=value.get();
			Optional<Employe> value1 = employeRepository.findById(employeId);	
			if(value1.isPresent())
			{	Employe employeManagedEntity =value1.get();

		if(depManagedEntity.getEmployes() == null){

			List<Employe> employes = new ArrayList<>();
			employes.add(employeManagedEntity);
			depManagedEntity.setEmployes(employes);
		}else{

			depManagedEntity.getEmployes().add(employeManagedEntity);

		}}
		}
		l.debug("je viens d'affecter l'employe à un departement");
		l.info("fin de la methode affecter employe à un departement");
	}
	
	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId){
		l.info("lancer la methode desaffecter employe d'un departement");
		l.debug("je vais desaffecter employe d'un departement");
		Optional<Departement> value = deptRepoistory.findById(depId);
		if(value.isPresent())
		{
			Departement dep =value.get();
	
			int employeNb = dep.getEmployes().size();
			for(int index = 0; index < employeNb; index++){
				if(dep.getEmployes().get(index).getId() == employeId){
					dep.getEmployes().remove(index);
					break;
				}
			}
		}
		l.debug("je viens de desaffecter l'employe du departement");
		l.info("fin de la methode desaffecter employe d'un departement");
	}
	

	public int ajouterContrat(Contrat contrat) {
		l.info("lancer la methode ajouter contrat");
		l.debug("je vais lancer save de contrat");
		contratRepoistory.save(contrat);
		l.debug("je viens de finir save de contrat");
		l.info("fin de  la methode ajouter contrat");
		return contrat.getReference();
	}

	public void affecterContratAEmploye(int contratId, int employeId) {
		l.info("lancer la methode affecter contrat à un employe");
		l.debug("je vais affecter un contrat à un employe");
		Optional<Contrat> value = contratRepoistory.findById(contratId);
		if(value.isPresent())
		{
			Contrat contratManagedEntity =value.get();
			Optional<Employe> value1= employeRepository.findById(employeId);
			if(value1.isPresent())
			{	
				
		Employe employeManagedEntity=value1.get();

		contratManagedEntity.setEmploye(employeManagedEntity);
		contratRepoistory.save(contratManagedEntity);
		}
		}
		l.debug("je viens d'affecter un contrat à un employe");
		l.info("fin de la methode affecter contrat à un employet");
	}
	

	public String getEmployePrenomById(int employeId) {
		l.info("lancer la methode get employe prenom by id");
		l.debug("je vais récupéré le prenom d'un employe à travers son id");
	Optional<Employe>	value  = employeRepository.findById(employeId);
	if(value.isPresent()) {
		Employe employeManagedEntity=value.get();
		return employeManagedEntity.getPrenom();
	}else {

		l.debug("je viens de récupéré le prenom d'un employe à travers son id");
		l.info("fin de la methode get employe prenom by id");
		return null;
	}
	}
	public void deleteEmployeById(int employeId)
	{
		l.info("lancer la methode delete employe by id");
		l.debug("je vais effacer un employe à travers son id");
		Optional<Employe> value = employeRepository.findById(employeId);
		if(value.isPresent()) {
			Employe employe=value.get();	
			for(Departement dep : employe.getDepartements()){
				dep.getEmployes().remove(employe);
		}
			employeRepository.delete(employe);
			l.debug("je viens d'effacer un employe à travers son id");
			l.info("fin de la methode delete employe by id");
		}

		
		l.debug("le employee n'existe pas");
		l.info("fin de la methode delete employe by id");
	}

	public void deleteContratById(int contratId) {
		l.info("lancer la methode delete contrat by id");
		l.debug("je vais effacer un contrat à travers son id");
		Optional<Contrat> value = contratRepoistory.findById(contratId);
		if(value.isPresent()) {
			Contrat contratManagedEntity= value.get();
			contratRepoistory.delete(contratManagedEntity);
			l.debug("je viens d'effacer un contrat à travers son id");
			l.info("fin de la methode delete contrat by id");
		}else {
			l.debug("le contrat n'existe pas");
			l.info("fin de la methode delete contrat by id");
		}

	}


	public int getNombreEmployeJPQL() {
		l.info("lancer la methode getNombreEmployeJPQL");
		l.debug("je vais récupérer le nombre de tous les employes");
		int a =  employeRepository.countemp();
		l.debug("je viens récupérer le nombre de tous les employes");
		l.info("fin de  la methode  getNombreEmployeJPQL");
		return a;
		
	}
	
	public List<String> getAllEmployeNamesJPQL() {
		l.info("lancer la methode getAllEmployeNamesJPQL");
		l.debug("je vais récupérer les noms des tous les employes");
		 List<String> a = employeRepository.employeNames();
		l.debug("je viens récupérer les noms des tous les employes");
		l.info("fin de  la methode  getAllEmployeNamesJPQL");
		return a;
		

	}
	
	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		l.info("lancer la methode getAllEmployeByEntreprise");
		l.debug("je vais récupérer les employe par entreprise");
		 List<Employe> a= employeRepository.getAllEmployeByEntreprisec(entreprise);
		l.debug("je viens de récupérer les employe par entreprise");
		l.info("fin de  la methode  getAllEmployeByEntreprise");
		return a;
		
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		l.info("lancer la methode mettreAjourEmailByEmployeIdJPQL");
		l.debug("je vais mettre ajour l'email d'un employe a traver son id");
		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);
		l.debug("je viens de mettre ajour l'email d'un employe a traver son id");
 		l.info("fin de  la methode  mettreAjourEmailByEmployeIdJPQL");

	}
	public void deleteAllContratJPQL() {
		l.info("lancer la methode deleteAllContratJPQL");
		l.debug("je vais supprimer tous les contrats");
         employeRepository.deleteAllContratJPQL();
         l.debug("je viens de supprimer tous les contrats");
 		l.info("fin de  la methode  deleteAllContratJPQL");
	}
	
	public float getSalaireByEmployeIdJPQL(int employeId) {
		l.info("lancer la methode getSalaireByEmployeIdJPQL");
		l.debug("je vais récupérer la salaire du employe by id");
		float a =  employeRepository.getSalaireByEmployeIdJPQL(employeId);
		l.debug("je viens de récupérer la salaire du employe by id");
		l.info("fin de  la methode  getSalaireByEmployeIdJPQL");
		return a;
		
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		l.info("lancer la methode getSalaireMoyenByDepartementId");
		l.debug("je vais récupérer la salaire moyene by departement");
		Double a = employeRepository.getSalaireMoyenByDepartementId(departementId);
		l.debug("je viens de récupérer la salaire moyene by departement");
		l.info("fin de  la methode  getSalaireMoyenByDepartementId");
		return a;
	
	}
	
	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		l.info("lancer la methode getTimesheetsByMissionAndDate");
		l.debug("je vais récupérer la liste du Timesheet by mission and date");
		List<Timesheet> a = timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
		l.debug("je viens de récupérer la liste du Timesheet by mission and date");
		l.info("fin de  la methode  getTimesheetsByMissionAndDate");
		return a;
		
	}

	public List<Employe> getAllEmployes() {
		l.info("lancer la methode get all  employe");
		l.debug("je vais récupérer la liste de tous les employes");
		List<Employe> a = (List<Employe>) employeRepository.findAll();
		l.debug("je viens de récupérer la liste de tous les employes");
		l.info("fin de  la methode  get all  employe");
				return a;
			
	}

	

}
