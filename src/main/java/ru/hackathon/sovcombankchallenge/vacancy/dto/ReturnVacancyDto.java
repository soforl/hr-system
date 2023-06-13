package ru.hackathon.sovcombankchallenge.vacancy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.SphereType;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.VacancyStatus;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.WorkExperience;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class ReturnVacancyDto {
    private UUID vacancyId;
    private String vacancyName;
    private VacancyStatus vacancyStatus;
    private WorkExperience workExperience;
    private SphereType sphere;
}
