package tn.esprit.spring.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Mission;

import java.util.List;

@Repository
public interface MissionRepository extends PagingAndSortingRepository<Mission, Integer>, CrudRepository<Mission, Integer> {
    List<Mission> findAllByNameLikeAndDepartementNameOrderByName(String name, String departementName,final Pageable page);
}
