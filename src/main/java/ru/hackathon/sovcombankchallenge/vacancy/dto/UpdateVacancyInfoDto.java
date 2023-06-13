package ru.hackathon.sovcombankchallenge.vacancy.dto;

import lombok.Builder;
import lombok.Data;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.SphereType;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.VacancyStatus;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.WorkExperience;

import java.util.UUID;

@Data
@Builder
public class UpdateVacancyInfoDto {
    private UUID vacancyId;
    private String name;
    private String description;
    private VacancyStatus vacancyStatus;
    private WorkExperience workExperience;
    private SphereType sphereType;
}
