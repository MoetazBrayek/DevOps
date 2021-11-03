package tn.esprit.spring.services;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.entities.TimesheetPK;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class TimesheetServiceImpl implements ITimesheetService {
	

	@Autowired
	MissionRepository missionRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;
	@Autowired
	EmployeRepository employeRepository;
	
	
	private static final Logger l = Logger.getLogger(EntrepriseServiceImpl.class);
	
	public int ajouterMission(Mission mission) {
		l.info("lancer la methode ajouterMission");
		l.debug("je veux faire le save de la nouvelle mission ");
		missionRepository.save(mission);
		l.debug("je viens de finir l'ajout d'une nouvelle mission");
		l.info("fin de  la methode ajouterMission");
		return mission.getId();
	}
    
	

	public void ajouterTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin) {
		l.info("lancer la methode ajouterTimesheet");
		
		TimesheetPK timesheetPK = new TimesheetPK();
		timesheetPK.setDateDebut(dateDebut);
		timesheetPK.setDateFin(dateFin);
		timesheetPK.setIdEmploye(employeId);
		timesheetPK.setIdMission(missionId);
		
		Timesheet timesheet = new Timesheet();
		timesheet.setTimesheetPK(timesheetPK);
		timesheet.setValide(false); //par defaut non valide
		
		l.debug("je veux faire le save de la nouvelle timesheet ");
		timesheetRepository.save(timesheet);
		l.debug("je viens de finir l'ajout d'une nouvelle timesheet");
		l.info("fin de  la methode ajouterTimesheet");
		
	}

	public void affecterMissionADepartement(int missionId, int depId) {
		
		l.info("lancer la methode affecter mission à une département");
		l.debug("maintenant je vais faire l'affectation");
		Optional<Mission> value = missionRepository.findById(missionId);
		if (value.isPresent()) {
			Mission mission=value.get();
		
	
			Optional<Departement> value1 = deptRepoistory.findById(depId);
			if (value1.isPresent()) {
				Departement dep=value1.get();
			
			
		mission.setDepartement(dep);
		missionRepository.save(mission);
		
	}}
		
		l.debug("je viens d'affecter une mission à un departement");
		l.info("fin de l'affectation de mission");
		
	}

public void validerTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin, int validateurId) {
		

		l.info("lancer la methode validerTimesheet");
		l.debug("je vais faire la validation de timesheet par date debut et date fin");
		
		
		Optional<Employe> value = employeRepository.findById(validateurId);
		Optional<Mission> value1 = missionRepository.findById(missionId);
		if (value.isPresent() && value1.isPresent()) {
			Employe validateur=value.get();
			Mission mission=value1.get();
			
			
				
			
			
		if(!validateur.getRole().equals(Role.CHEF_DEPARTEMENT)){
			
			
			l.debug("verification qu'il a le role d'un chef de departement est éfectué");
			l.info("l'employé à le role d'un chef de departement");
			
			return;
		}
		
		boolean chefDeLaMission = false;
		for(Departement dep : validateur.getDepartements()){
			if(dep.getId() == mission.getDepartement().getId()){
				chefDeLaMission = true;
				
				l.debug("verification qu'il est le chef de departement de la mission en question est éfectué");
				l.info("l'employé est le chef de departement de la mission en question");
				
				break;
			}
		}
		if(!chefDeLaMission){
		
			
			l.debug("verification qu'il est le chef de departement de mission en question n'est éfectué");
			l.info("l'employé n'est pas le chef de departement de la mission en question");
			
			return;
		}

		TimesheetPK timesheetPK = new TimesheetPK(missionId, employeId, dateDebut, dateFin);
		Timesheet timesheet =timesheetRepository.findBytimesheetPK(timesheetPK);
		timesheet.setValide(true);
		
		
		l.debug("validation de timesheet est éfectué");
		l.info("fin de la méthode valider timesheet");
		
	}}
	
	
	public List<Mission> findAllMissionByEmployeJPQL(int employeId) {
		l.info("lancer la methode findAllMissionByEmployeIdJPQL");
		l.debug("je vais récupérer les missions du employe by id");
		return timesheetRepository.findAllMissionByEmployeJPQL(employeId);
	}

	
	public List<Employe> getAllEmployeByMission(int missionId) {
		l.info("lancer la methode getAllEmployeByMission");
		l.debug("je vais récupérer les employées dans la meme mission  by missionId");
		return timesheetRepository.getAllEmployeByMission(missionId);
	}
	
	
	
	
}
