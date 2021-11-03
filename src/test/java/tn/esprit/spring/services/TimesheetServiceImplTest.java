package tn.esprit.spring.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;

import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimesheetServiceImplTest {

	@Autowired
	ITimesheetService tss;
	@Autowired
	TimesheetRepository tsr;
	@Autowired 
	MissionRepository mr ;
	
	@Test 
	public void ajouterMissionTest() {
		Mission mis=new Mission(1,"application de vente","réaliser une application de vente des produits");
		int m=tss.ajouterMission(mis);
		assertEquals(mis.getId(),m);
	}
	
	
	
	
	
	@Test
	public void getAllEmployeByMissionTestSuccees2() {
		List<Employe> emp=tss.getAllEmployeByMission(8);
		assertNotNull(emp);
	}
	
	
}
