package com.example.Validation.Repository;

import com.example.Validation.Model.Profanity;

import java.util.List;

public interface ProfanityRepository {
    Profanity createProfanity(Profanity newProfanity);

    Profanity getProfanity(Long profanityId);

    Profanity getProfanityByWord(String word);

    List<Profanity> getAll();

    Profanity updateProfanity(Profanity profanity);

    void deleteProfanity(Long id);

    void deleteProfanityByWord(String word);
}
