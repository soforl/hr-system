package ru.hackathon.sovcombankchallenge.vacancy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.SphereType;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.VacancyStatus;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.WorkExperience;

@Data
@Builder
@AllArgsConstructor
public class VacancySpecificDto {
    private String vacancyName;
    private String description;
    private VacancyStatus vacancyStatus;
    private WorkExperience workExperience;
    private SphereType sphereType;

}
