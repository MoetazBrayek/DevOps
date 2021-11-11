package tn.esprit.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Timesheet;

import java.util.List;

@Data
@AllArgsConstructor
public class MissionDto {

    private int id;
    private String name;
    private String description;
    private Departement departement;
    private List<Timesheet> timesheets;

}
