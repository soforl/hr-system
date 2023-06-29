package ru.hackathon.sovcombankchallenge.response.dto;

import lombok.Data;
import ru.hackathon.sovcombankchallenge.response.enumeration.ResponseStatus;

import java.util.UUID;

@Data
public class UpdateResponseStatusDto {
    private UUID responseId;
    private ResponseStatus responseStatus;
}
