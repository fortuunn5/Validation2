package com.example.Validation.Service;

import com.example.Validation.Dto.Position;
import com.example.Validation.Exception.EntityNotFoundException;
import com.example.Validation.Model.Profanity;
import com.example.Validation.Repository.ProfanityRepositorySQL;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfanityService {
    private final ProfanityRepositorySQL profanityRepository;

    public Profanity createProfanity(String word) {
        return profanityRepository.createProfanity(word);
    }

    public Profanity getProfanity(Long id) {
        return profanityRepository.getProfanity(id);
    }

    public Profanity getProfanityByWord(String word) {
        return profanityRepository.getProfanityByWord(word);
    }

    public List<Profanity> getAllProfanities() {
        return profanityRepository.getAll();
    }

    public Profanity updateProfanity(Profanity profanity) {
        if (getProfanity(profanity.getId()) == null) {
            throw new EntityNotFoundException("Записи с таким id нет в базе данных");
        }
        return profanityRepository.updateProfanity(profanity);
    }

    public void deleteProfanity(Long id) {
        if (getProfanity(id) == null) {
            throw new EntityNotFoundException("Записи с таким id нет в базе данных");
        }
        profanityRepository.deleteProfanity(id);
    }

    public void deleteProfanityByWord(String word) {
        Profanity profanity = getProfanityByWord(word);
        if (profanity == null) {
            throw new EntityNotFoundException("Записи с таким словом нет в базе данных");
        }
        profanityRepository.deleteProfanityByWord(word);
    }

    public List<Position> getPositionsOfProfanities(String message) {
        List<Profanity> profanities = getAllProfanities();
        List<Position> positions = new ArrayList<>();
        long value;
        for (Profanity profanity : profanities) {
            value = StringUtils.indexOfIgnoreCase(message, profanity.getWordValue());
            if (value != -1) {
                positions.add(new Position(value + 1,
                        value + profanity.getWordValue().length(),
                        profanity.getWordValue())
                );
            }
        }
        return positions;
    }
}
