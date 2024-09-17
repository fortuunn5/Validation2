package com.example.Validation.Repository.noSql;

import com.example.Validation.Model.Profanity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProfanityRepositoryMongoDB extends MongoRepository<Profanity, Long> {
    Optional<Profanity> findByWordValue(String word);
}
