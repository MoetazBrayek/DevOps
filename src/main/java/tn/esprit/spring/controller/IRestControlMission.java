package tn.esprit.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.exceptions.InvalidDatabaseOperationException;

import java.util.List;
import java.util.Optional;

public interface IRestControlMission {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Optional<Mission> getById(@RequestParam int id);
    @GetMapping("/paginated")
    @ResponseStatus(HttpStatus.OK)
    List<Mission> paginated(final int page, final int size, final String name, final String departement);
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Mission add(@Validated @RequestBody Mission user) throws InvalidDatabaseOperationException;
    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    Mission update(@Validated @RequestBody Mission user) throws InvalidDatabaseOperationException;
    @DeleteMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    void delete(@RequestParam int missionId);
}
