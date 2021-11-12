package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.exceptions.InvalidDatabaseOperationException;
import tn.esprit.spring.services.MissionServiceImpl;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/missions")
public class RestControlMission implements IRestControlMission{
    private MissionServiceImpl service;
    @Autowired
    public RestControlMission(MissionServiceImpl service){
        this.service = service;
    }
    @Override
    public Optional<Mission> getById(int id) {return this.service.getById(id);}
    @Override
    public List<Mission> paginated(int page, int size, String name, String departement) {
        return this.service.getPaginated(page,size,name,departement);
    }
    @Override
    public Mission add(@Validated  Mission user) throws InvalidDatabaseOperationException {return this.service.add(user);}
    @Override
    public Mission update(@Validated Mission user) throws InvalidDatabaseOperationException {return this.service.update(user);}

    @Override
    public void delete(int missionId) {this.service.delete(missionId);}
}
