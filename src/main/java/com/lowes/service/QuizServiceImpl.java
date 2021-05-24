package com.lowes.service;

import com.lowes.dto.QuizApiResponse;
import com.lowes.dto.QuizDto;
import com.lowes.dto.mapper.DtoMapper;
import com.lowes.model.Quiz;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {

    @Value("${quiz.endpoint}")
    public String endpoint;
    private final WebClient webClient;
    private final DtoMapper dtoMapper;

    public QuizServiceImpl(WebClient webClient, DtoMapper dtoMapper) {
        this.webClient = webClient;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public Mono<Quiz> getQuiz(MultiValueMap<String, String> queryParams) {
        final String uri = buildURI(queryParams);
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Quiz.class)
                .log();
    }

    @Override
    public Mono<QuizApiResponse> getQuizApiResponse(List<MultiValueMap<String, String>> queryParams) {
        final List<Mono<QuizDto>> quizzes = queryParams.stream()
                .map(this::getQuizDto)
                .collect(Collectors.toList());
        final Mono<List<QuizDto>> mono = Flux.fromIterable(quizzes)
                .flatMap(Function.identity())
                .collectList();
        return mono.map(x -> {
            QuizApiResponse q = new QuizApiResponse();
            q.setQuiz(x);
            return q;
        });
    }

    @Override
    public Mono<QuizDto> getQuizDto(MultiValueMap<String, String> queryParams) {
        final Mono<Quiz> quiz = getQuiz(queryParams);
        return quiz.map(dtoMapper::quizToQuizDto);
    }

    private String buildURI(MultiValueMap<String, String> queryParams) {
        final UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(endpoint);
        return uriComponentsBuilder.queryParams(queryParams)
                .build()
                .toUri()
                .toString();
    }
}
