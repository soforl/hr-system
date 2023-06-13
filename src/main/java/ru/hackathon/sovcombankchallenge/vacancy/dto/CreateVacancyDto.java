package ru.hackathon.sovcombankchallenge.vacancy.dto;

import lombok.Data;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.SphereType;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.VacancyStatus;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.WorkExperience;

@Data
public class CreateVacancyDto {
    private String name;
    private String description;
    private WorkExperience workExperience;
    private VacancyStatus vacancyStatus;
    private SphereType sphere;
}
