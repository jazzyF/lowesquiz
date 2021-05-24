package com.lowes.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({"category", "results"})
public class QuizDto {
    private String category;
    private List<ResultDto> results;
}
