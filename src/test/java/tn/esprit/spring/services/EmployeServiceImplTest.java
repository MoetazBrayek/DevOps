package tn.esprit.spring.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.Contrat;

import java.util.Date;

import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeServiceImplTest {

    @Autowired
    IEmployeService iemployeservice;
    @Test
    public void testajouterContrat2() {
        Contrat contrat = new Contrat(new Date() ,"svp",1500);
        int i = iemployeservice.ajouterContrat(contrat);
        assertNotEquals(3, i);
    }


    @Test
    public void getEmployePrenomById() {
        assertNotEquals("bernar115", iemployeservice.getEmployePrenomById(1));
    }


    @Test
    public void getEmployePrenomById2() {
        assertNotEquals("bernar2", iemployeservice.getEmployePrenomById(1));
    }



    @Test
    public void getNombreEmployeJPQL2() {
        assertNotEquals(3, iemployeservice.getNombreEmployeJPQL());
    }



}