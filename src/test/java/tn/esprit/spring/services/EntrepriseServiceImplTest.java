package tn.esprit.spring.services;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.EntrepriseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntrepriseServiceImplTest {
		
	@Autowired
	IEntrepriseService es;
	@Autowired
	EntrepriseRepository er;

	@Test
	public void ajouterEntrepriseTest()  {
	Entreprise ent=new Entreprise(1,"SSII Consulting","Cite El Ghazela");
	int a=es.ajouterEntreprise(ent);
	assertEquals(ent.getId(),a);
	
	}

		@Test
		public void ajouterEntrepriseTest2()  {
		Entreprise ent=new Entreprise(1,null,null);
		int a=es.ajouterEntreprise(ent);
		assertEquals(0,a);
		
		}


	
	
	@Test
	public void getEntrepriseByIdTest1()  {
	Entreprise ent=es.getEntrepriseById(1996);
	assertNull(ent);
	}
	
	
	
	
	@Test
	public void getAllDepartementsNamesByEntrepriseTest1()  {
	List<String> names=es.getAllDepartementsNamesByEntreprise(7000);
	assertNotNull(names);
	}
	
	
	
	
}
