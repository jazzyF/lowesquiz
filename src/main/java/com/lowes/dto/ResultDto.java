package com.lowes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonPropertyOrder({"type", "difficulty", "question", "all_answers", "correct_answer"})
public class ResultDto {
    private String type;
    private String difficulty;
    private String question;
    @JsonProperty("all_answers")
    private List<String> allAnswers;
    @JsonProperty("correct_answer")
    private String correctAnswer;
}
