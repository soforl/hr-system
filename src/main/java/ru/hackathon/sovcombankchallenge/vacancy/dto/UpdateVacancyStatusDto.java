package ru.hackathon.sovcombankchallenge.vacancy.dto;

import lombok.Data;

@Data
public class UpdateVacancyStatusDto {
    private Long vacancyId;
    private String vacancyStatus;
}
