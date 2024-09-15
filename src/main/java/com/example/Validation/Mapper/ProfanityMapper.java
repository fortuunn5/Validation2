package com.example.Validation.Mapper;

import com.example.Validation.Dto.ProfanityDto;
import com.example.Validation.Model.Profanity;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProfanityMapper {
    public static Profanity map(ProfanityDto profanityDto) {
        if (profanityDto == null)
            return null;
        return Profanity.builder()
                .id(profanityDto.getId())
                .wordValue(profanityDto.getWordValue())
                .build();
    }

    public static ProfanityDto map(Profanity profanity) {
        if (profanity == null)
            return null;
        return ProfanityDto.builder()
                .id(profanity.getId())
                .wordValue(profanity.getWordValue())
                .build();
    }

    public static Set<Profanity> mapToSet(Set<ProfanityDto> profanityDtoSet) {
        return profanityDtoSet.stream().map(ProfanityMapper::map).collect(Collectors.toSet());
    }

    public static Set<ProfanityDto> mapToDtoSet(Set<Profanity> profanitySet) {
        return profanitySet.stream().map(ProfanityMapper::map).collect(Collectors.toSet());
    }
}
