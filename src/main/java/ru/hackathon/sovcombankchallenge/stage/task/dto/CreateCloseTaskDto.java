package ru.hackathon.sovcombankchallenge.stage.task.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateCloseTaskDto {
    private String question;
    private String var1;
    private String var2;
    private String var3;
    private String var4;
    private int rightChoose;
    private UUID stageId;
}
