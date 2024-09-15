package com.example.Validation.Service;

import com.example.Validation.Dto.ProfanityDto;
import com.example.Validation.Exception.EntityNotFoundException;
import com.example.Validation.Model.Profanity;
import com.example.Validation.Repository.ProfanityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfanityService {
    private final ProfanityRepository profanityRepository;

    public Profanity createProfanity(String word) {
        return profanityRepository.createProfanity(word);
    }

    public Profanity getProfanity(Long id)  {
        Profanity profanity = profanityRepository.getProfanity(id);
        return profanity;
    }

    public Profanity getProfanityByWord(String word) {
        Profanity profanity = profanityRepository.getProfanityByWord(word);
        return profanity;
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
}
