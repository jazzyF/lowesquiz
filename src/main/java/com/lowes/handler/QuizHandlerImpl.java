package com.lowes.handler;

import com.lowes.dto.QuizApiResponse;
import com.lowes.dto.QuizDto;
import com.lowes.service.QuizService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class QuizHandlerImpl implements QuizHandler {

    private final QuizService quizService;
    @Value("${quiz.amount}")
    private String amount;
    @Value("${quiz.categories}")
    private List<String> categories;

    public QuizHandlerImpl(QuizService quizService) {
        this.quizService = quizService;
    }

    @Override
    public Mono<ServerResponse> getQuiz(ServerRequest serverRequest) {
        final Mono<QuizDto> mono = quizService.getQuizDto(serverRequest.queryParams());
        return ServerResponse.ok().body(mono, QuizDto.class);
    }

    @Override
    public Mono<ServerResponse> getQuizzes(ServerRequest serverRequest) {
        final List<MultiValueMap<String, String>> queryParams = categories.stream()
                .map(x -> {
                    Map<String, List<String>> map = new HashMap<>();
                    map.put("amount", Collections.singletonList(amount));
                    map.put("category", Collections.singletonList(x));
                    return new MultiValueMapAdapter<>(map);
                })
                .collect(Collectors.toList());
        final Mono<QuizApiResponse> mono = quizService.getQuizApiResponse(queryParams);
        return ServerResponse.ok().body(mono, QuizApiResponse.class);
    }
}
