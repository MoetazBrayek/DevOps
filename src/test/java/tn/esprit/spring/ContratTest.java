package tn.esprit.spring;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.services.IContratService;
import java.util.concurrent.ThreadLocalRandom;

@RunWith(value=SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ContratTest {
	
	@Autowired
	IContratService cs;
	
	
	@Test
	@Order(1)
	public void testRetrieveAllContrats() {
		List<Contrat> listContrats = cs.retrieveAllContrats();
		boolean test = false;
		if (listContrats.size()>2){
			test= true;
		}
		Assert.assertTrue(test);
	}
	
	@Test
	@Order(2)
	public void testAddContrat() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d = dateFormat.parse("2018-03-23");
		Contrat c = new Contrat(d, "CDI",62);
		Contrat ContratAdded = cs.addContrat(c);
		Assert.assertEquals(c.getTypeContrat(), ContratAdded.getTypeContrat());
	}
 
	@Test
	@Order(3)
	public void testModifyContrat() throws ParseException   {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		int testid =2;
		Date d = dateFormat.parse("2020-03-23");
		int randomsalaire = ThreadLocalRandom.current().nextInt(200, 8000 + 1);
		
		
    	Contrat AncienContrat = cs.retrieveContrat(testid);

		Contrat Contratmodifie = new Contrat(testid ,d, "CDD",randomsalaire);
		cs.updateContrat(Contratmodifie);
		Assert.assertNotEquals((int)AncienContrat.getSalaire(), randomsalaire);

	}

	@Test
	@Order(4)
	public void testRetrieveContrat() {
		int testid = 2;
        	Contrat ContratRetrieved = cs.retrieveContrat(testid);
        	Assert.assertEquals(testid, ContratRetrieved.getReference());
	}
	
	@Test
	@Order(5)
	public void testDeleteContrat() throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d = dateFormat.parse("2018-03-23");
		Contrat c = new Contrat(d, "CDI",68);
		Contrat ContratAdded = cs.addContrat(c);
		
		int value = cs.deleteContrat(ContratAdded.getReference());
		Assert.assertEquals(1, value);	
		
	}

}