package ru.hackathon.sovcombankchallenge.vacancy.enumeration;

public enum VacancyStatus{
    // HR'ы могут видеть эти вакансии, а кандидаты нет
    OnModeration,
    Opened,
    // Заархивированные вакансии
    Closed
}