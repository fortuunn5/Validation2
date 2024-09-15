package com.example.Validation.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Position {
    private Long start;
    private Long end;
    private String wordProfanity;

    @Override
    public String toString() {
        return "Слово " + wordProfanity + "найдено на следующей позиции:\n" +
                "Начало: " + start + ";\n"
                + "Конец: " + end + ".";
    }
}
