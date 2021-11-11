package tn.esprit.spring.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotEquals;

import java.text.ParseException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.EntrepriseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
 class EntrepriseServiceImplTest {
	@Autowired
	IEntrepriseService es;
	@Autowired
	EntrepriseRepository er;
	
	
	@Test
	 void ajouterEntrepriseTest()  {
	Entreprise ent=new Entreprise(1,"SSII Consulting","Cite El Ghazela");
	int a=es.ajouterEntreprise(ent);
	assertEquals(ent.getId(),a);
	
	}

		@Test
		 void ajouterEntrepriseTest2()  {
		Entreprise ent=new Entreprise(1,null,null);
		int a=es.ajouterEntreprise(ent);
		assertEquals(0,a);
		
		}

	

	

	@Test
	 void ajouterDepartementTest()  {
		Departement dep =new Departement(1,"info");
	int a=es.ajouterDepartement(dep);
	assertEquals(dep.getId(),a);
	
	}
	
	
	@Test
	 void getEntrepriseByIdTest1()  {
	Entreprise ent=es.getEntrepriseById(1996);
	assertNull(ent);
	}
	

	@Test
	 void getNombreEntrepriseJPQLTest() {
		assertNotEquals(3, es.getNombreEntrepriseJPQL());
	}

	

	@Test
	 void getEntrepriseNameByIdTest() {
		assertNotEquals("bernar2", es.getEntrepriseNameById(1));
	}
	

	@Test
	 void getAllDepartementsNamesByEntrepriseTest1()  {
	List<String> names=es.getAllDepartementsNamesByEntreprise(7000);
	assertNotNull(names);
	}
	
	
	@Test
	
	 void deleteEntrepriseTest() throws ParseException {
		
	
		int value = es.deleteById(1);
		
		assertNotEquals(1, value);	
		
	}
	
	
}