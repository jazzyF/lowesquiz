package com.lowes.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface QuizHandler {
    Mono<ServerResponse> getQuiz(ServerRequest serverRequest);

    Mono<ServerResponse> getQuizzes(ServerRequest serverRequest);
}
