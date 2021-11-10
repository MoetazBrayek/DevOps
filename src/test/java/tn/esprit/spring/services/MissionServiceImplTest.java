package tn.esprit.spring.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.repository.DepartementRepository;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MissionServiceImplTest {
    @Autowired IMissionService missionService;
    @Autowired IDepartementService departementService;
    @Autowired DepartementRepository departementRepository;
    private List<Departement> departementList;
    @Order(1)
    @Test
    void testTruthy() throws Exception {
        departementList = departementService.getAllDepartements();
        if(departementList.isEmpty()) {
            log.info("no departments in DB");
            throw new Exception("Please insert a new departement");
        }
        Departement departement = departementList.get(0);
        Mission mission = new Mission(generateRandomString(15), generateRandomString(60), departement);
        Mission fromDB = null;
        try {
            fromDB = missionService.add(mission);
        } catch (Exception e){
            log.info(e.getMessage());
            Assertions.fail("Truthy add failed");
        }
        Assertions.assertNotNull(fromDB, "Is added mission null ?");
        Assertions.assertTrue(fromDB.getId()>0,"Id has been created");
        Assertions.assertEquals(fromDB,mission, "Are they equal using the equals that doesnt check by ID");
        log.info("Truthy get by id");
        Optional<Mission> byId = missionService.getById(fromDB.getId());
        Assertions.assertTrue(byId.isPresent(), "Is fetch by id working after insert");
        Assertions.assertNotNull(byId.get(), "Is fetch by id fetching entity");
        Assertions.assertEquals(byId.get().getId(), fromDB.getId(), "is id the same");
        log.info("Truthy paginated");
        List<Mission> paginated = missionService.getPaginated(0,1, fromDB.getName(), departement.getName());
        int size = paginated.size();
        Assertions.assertNotNull(paginated);
        Assertions.assertFalse(paginated.isEmpty());
        log.info("Truthy update ");
        Mission copy = mission;
        String name = generateRandomString(11), description = generateRandomString(11);
        mission.setName(name);
        mission.setDescription(description);
        try {
            mission = missionService.update(mission);
        } catch (Exception e){
            log.info(e.getMessage());
            Assertions.fail("Truthy update failed!");
        }
        Assertions.assertNotNull(mission, "Did it get updated ?");
        Assertions.assertEquals(copy.getId(), mission.getId(), "Is copy's ID same as Update's ID ? : {}");
        Assertions.assertNotEquals(copy, mission, "Are they the same after update ?");
        log.info("Falsy update");
        mission.setName(generateRandomString(12));
        mission.setDescription(generateRandomString(20));
        mission.setDepartement(new Departement());
        Mission poop = null;
        try {
            poop = missionService.update(mission);
            Assertions.fail("Non valid mission update passed!");
        } catch (Exception e){
            log.info(e.getMessage());
            Assertions.assertTrue(true, "Update failed as it should");
        }
        Assertions.assertNull(poop, "Did it get updated or did the @Validated reject the request?");
        log.info("Truthy delete");
        missionService.delete(mission.getId());
        Optional<Mission> afterDelete = missionService.getById(mission.getId());
        Assertions.assertFalse(afterDelete.isPresent(),"Succesfully deleted");
        Assertions.assertTrue(size > missionService.getPaginated(0,5,mission.getName(),mission.getDepartement().getName()).size(), "Deleted from paginated");
    }

    @Order(2)
    @Test
    void testFalsy(){
        String name = generateRandomString(6), description = generateRandomString(255);
        Departement departement = departementService.getAllDepartements().get(0);
        try {
            missionService.add(new Mission(name, description, departement));
            Assertions.fail("Validation failed on add, should have been rejected due to length constraints!");
        } catch (Exception e) {
            log.info(e.getMessage());
            Assertions.assertTrue(true);
        }
        // Transient department test, should be falsy
        Mission newMission = new Mission(generateRandomString(50), generateRandomString(20),new Departement());
        try{
            missionService.add(newMission);
            Assertions.fail("Falsy add passed");
        } catch (Exception e){
            log.info("Save failed as it should");
            Assertions.assertTrue(true, "Exception caught because Add doesnt pass validation");
        }
    }
    private String generateRandomString(int length){
        byte[] array = new byte[length];
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }
}

// mvn -Dtest=MissionServiceImplTest test