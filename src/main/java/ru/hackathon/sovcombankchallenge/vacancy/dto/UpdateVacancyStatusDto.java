package ru.hackathon.sovcombankchallenge.vacancy.dto;

import lombok.Data;
import ru.hackathon.sovcombankchallenge.vacancy.enumeration.VacancyStatus;

import java.util.UUID;

@Data
public class UpdateVacancyStatusDto {
    private UUID vacancyId;
    private VacancyStatus vacancyStatus;
}
