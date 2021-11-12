package tn.esprit.spring.services;

import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.exceptions.InvalidDatabaseOperationException;

import java.util.List;
import java.util.Optional;

public interface IMissionService {
    Mission add(Mission mission) throws InvalidDatabaseOperationException;
    Mission update(Mission mission) throws InvalidDatabaseOperationException;
    void delete(int missionId);
    Optional<Mission> getById(int missionId);
    List<Mission> getPaginated(final int page, final int size, final String name, final String departement);
    Iterable<Mission> getAll();
}
