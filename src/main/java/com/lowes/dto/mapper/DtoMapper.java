package com.lowes.dto.mapper;

import com.lowes.dto.QuizDto;
import com.lowes.dto.ResultDto;
import com.lowes.model.Quiz;
import com.lowes.model.Result;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DtoMapper {

    @Mapping(target = "allAnswers", expression = "java(answersFromCorrectAndIncorrect(result))")
    ResultDto resultToResultDto(Result result);
    @Mappings({
            @Mapping(source = "results", target = "category", qualifiedByName = "categoryFromResults"),
            @Mapping(source = "results", target = "results")
    })
    QuizDto quizToQuizDto(Quiz quiz);

    @Named("categoryFromResults")
    default String categoryFromResults(List<Result> results) {
        if (results == null || results.isEmpty()) {
            return null;
        }
        return results.get(0).getCategory();
    }

    default List<String> answersFromCorrectAndIncorrect(Result result) {
        List<String> list = new ArrayList<>(result.getIncorrectAnswers());
        list.add(result.getCorrectAnswer());
        Collections.shuffle(list);
        return list;
    }
}
