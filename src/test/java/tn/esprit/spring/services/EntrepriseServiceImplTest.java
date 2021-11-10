package tn.esprit.spring.services;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.EntrepriseRepository;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntrepriseServiceImplTest {
    private static final Logger l = Logger.getLogger(EntrepriseServiceImplTest.class);
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
    public void ajouterDepartementTest()  {
        Departement dep =new Departement("info");
        int a=es.ajouterDepartement(dep);
        assertNotNull(a);
        assertNotEquals(a,0);
    }
    @Test
    public void getEntrepriseByIdTest()  {
        Entreprise ent=es.getEntrepriseById(1);
        assertNotNull(ent);
        Entreprise entr=er.findById(1).get();
        assertEquals(entr.getName(),ent.getName());
        assertEquals(entr.getRaisonSocial(),ent.getRaisonSocial());
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