package com.example.Validation.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfanityDto {
    private Long id;
    private String wordValue;
}
