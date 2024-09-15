package com.example.Validation.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class ValidationResponse {
    //todo read about message.properties
    @JsonIgnore
    private final static String PROFANITIES_DOESNT_EXIST = "Валидация пройдена";
    @JsonIgnore
    private final static String PROFANITIES_EXIST = "Валидация не пройдена";
    private List<Position> positions = new ArrayList<>();

    public boolean getHasProfanities() {
        return !CollectionUtils.isEmpty(positions);
    }

    public String getMessage() {
        if(!getHasProfanities()) {
            return PROFANITIES_DOESNT_EXIST;
        }
        return PROFANITIES_EXIST;
    }
}
