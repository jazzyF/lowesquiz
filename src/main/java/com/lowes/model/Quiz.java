package com.lowes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({"response_code", "results"})
public class Quiz {
    @JsonProperty("response_code")
    private int responseCode;
    private List<Result> results;
}
