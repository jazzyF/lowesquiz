package com.lowes.service;

import com.lowes.dto.QuizApiResponse;
import com.lowes.dto.QuizDto;
import com.lowes.model.Quiz;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

import java.util.List;

public interface QuizService {
    Mono<Quiz> getQuiz(MultiValueMap<String, String> queryParams);

    Mono<QuizApiResponse> getQuizApiResponse(List<MultiValueMap<String, String>> queryParams);

    Mono<QuizDto> getQuizDto(MultiValueMap<String, String> queryParams);
}
