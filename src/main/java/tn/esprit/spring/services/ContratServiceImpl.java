package tn.esprit.spring.services;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.repository.ContratRepository;

@Service
public class ContratServiceImpl implements IContratService {

	@Autowired
	ContratRepository contratRepository;
	private static final Logger l = LogManager.getLogger(ContratServiceImpl.class);


	@Override
	public List<Contrat> retrieveAllContrats() { 
		List<Contrat> contrats = null; 
		try {
	
			l.info("In retrieveAllContrats() : ");
			contrats = (List<Contrat>) contratRepository.findAll();  
			for (Contrat contrat : contrats) {
				l.debug(contrat);
			} 
			l.info("Out of retrieveAllContrats() : ");
		}catch (Exception e) {
			l.error(e);
		}

		return contrats;
	}


	@Override
	public Contrat addContrat(Contrat c) {
		l.info("Add Contrat");
		return contratRepository.save(c); 
	}

	@Override 
	public Contrat updateContrat(Contrat c) { 
		l.info("update Contrat");
		return contratRepository.save(c);
	}

	@Override
	public int deleteContrat(int id) {
		l.info("START deleteEntrepriseById ");
		Contrat e =  contratRepository.findById(id).orElse(null);

		try {

			l.trace("Début Test : verifier l'existence du contrat");
			if (e != null) {

				l.debug(e.getReference());

				l.trace("débbut suppression");
				contratRepository.delete(e);
				l.trace("fin suppression");
				l.trace("FIN Test");

				return 1;
			} else {
				l.trace("contrat n'exitse pas");
				l.trace("FIN Test avec fail");
				return -1;
			}

		} catch (Exception err) {
			l.error( err);

		}
	

		return 0;
	}

	

	@Override
	public Contrat retrieveContrat(int id) { 
		Contrat c =  contratRepository.findById(id).orElse(null);
		l.info( c);
		return c; 
	}

}