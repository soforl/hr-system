package ru.hackathon.sovcombankchallenge.vacancy.dto;

import lombok.Data;

@Data
public class CreateVacancyDto {
    private String name;
    private String description;
    private String workExperience;
    private String vacansyStatus;
}
