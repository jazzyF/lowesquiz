package com.lowes.config;

import com.lowes.handler.QuizHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AppConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }

    @Bean
    RouterFunction<ServerResponse> routerFunction(QuizHandler quizHandler) {
        return route()
                .GET("/coding/exercise/quiz/single", quizHandler::getQuiz)
                .GET("/coding/exercise/quiz", quizHandler::getQuizzes)
                .build();
    }
}
