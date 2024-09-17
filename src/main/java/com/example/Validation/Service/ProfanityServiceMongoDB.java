package com.example.Validation.Service;

import com.example.Validation.Dto.Position;
import com.example.Validation.Exception.EntityNotFoundException;
import com.example.Validation.Exception.MessageNotFoundException;
import com.example.Validation.Exception.NotUniqueConstraintException;
import com.example.Validation.Model.Profanity;
import com.example.Validation.Repository.noSql.ProfanityRepositoryMongoDB;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfanityServiceMongoDB implements ProfanityService {

    @Autowired
    private ProfanityRepositoryMongoDB profanityRepository;

    //TODO: вынести текст ошибки в message.properties
    public Profanity createProfanity(Profanity newProfanity) {
        if (getProfanity(newProfanity.getId()) != null || getProfanityByWord(newProfanity.getWordValue()) != null) {
            throw new NotUniqueConstraintException("Некорректные данные");
        }
        return profanityRepository.save(newProfanity);
    }

    public Profanity getProfanity(Long id) {
        return profanityRepository.findById(id).orElse(null);
    }

    public Profanity getProfanityByWord(String word) {
        return profanityRepository.findByWordValue(word).orElse(null);
    }

    public List<Profanity> getAllProfanities() {
        return profanityRepository.findAll();
    }

    public Profanity updateProfanity(Profanity profanity) {
        if (getProfanity(profanity.getId()) == null) {
            throw new EntityNotFoundException("Записи с таким id нет в базе данных");
        }
        if (getProfanityByWord(profanity.getWordValue()) != null) {
            throw new NotUniqueConstraintException("Некорректные данные");
        }
        return profanityRepository.save(profanity);
    }

    public void deleteProfanity(Long id) {
        if (getProfanity(id) == null) {
            throw new EntityNotFoundException("Записи с таким id нет в базе данных");
        }
        profanityRepository.deleteById(id);
    }

    public void deleteProfanityByWord(String word) {
        Profanity profanity = getProfanityByWord(word);
        if (profanity == null) {
            throw new EntityNotFoundException("Записи с таким словом нет в базе данных");
        }
        profanityRepository.deleteById(profanity.getId());
    }

    public List<Position> getPositionsOfProfanities(String message) {
        if (StringUtils.isBlank(message))
            throw new MessageNotFoundException("Пустое сообщение");
        List<Profanity> profanities = getAllProfanities();
        List<Position> positions = new ArrayList<>();
        long value = 0;
        int count = 0;

        for (Profanity profanity : profanities) {
            count = 0;
            while (count < message.length() && value != -1) {
                value = StringUtils.indexOfIgnoreCase(message, profanity.getWordValue(), count);

                positions.add(new Position(value + 1,
                        value + profanity.getWordValue().length(),
                        profanity.getWordValue()));
                count = (int) (count + value + profanity.getWordValue().length() + 1);

            }
        }
        return positions;
    }
}
