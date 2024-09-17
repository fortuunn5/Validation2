package com.example.Validation.Service;

import com.example.Validation.Dto.Position;
import com.example.Validation.Exception.EntityNotFoundException;
import com.example.Validation.Exception.MessageNotFoundException;
import com.example.Validation.Model.Profanity;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public interface ProfanityService {
    Profanity createProfanity(Profanity newProfanity);

    Profanity getProfanity(Long id);

    Profanity getProfanityByWord(String word);

    List<Profanity> getAllProfanities();

    Profanity updateProfanity(Profanity profanity);

    void deleteProfanity(Long id);

    void deleteProfanityByWord(String word);

    List<Position> getPositionsOfProfanities(String message);
}