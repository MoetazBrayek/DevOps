package tn.esprit.spring.converter;

import tn.esprit.spring.dto.MissionDto;
import tn.esprit.spring.entities.Mission;

public class MissionConverter {
    public MissionDto entityToDto(Mission e) {
        return new MissionDto(e.getId(),e.getName(), e.getDescription(),e.getDepartement(),e.getTimesheets());
    }

    public static Mission dtoToEntity(MissionDto e) {
        return new Mission(e.getName(),e.getDescription());
    }
}
