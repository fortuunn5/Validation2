package com.example.Validation.Repository;

import com.example.Validation.Model.Profanity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProfanityRepositorySQL implements ProfanityRepository {
    private final SessionFactory sessionFactory;

    @Override
    public Profanity createProfanity(String word) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
//            session.persist(new Profanity(word));

            MutationQuery query = session.createMutationQuery("INSERT INTO Profanity(wordValue) VALUES (:word_value)");
            query.setParameter("word_value", word);
            query.executeUpdate();

            transaction.commit();
        }
        return getProfanityByWord(word);
    }

    @Override
    public Profanity getProfanity(Long profanityId) {
        try (Session session = sessionFactory.openSession()) {
            Profanity profanity = session.get(Profanity.class, profanityId);
            session.close();
            return profanity;
        }
    }

    //TODO: переписать, это нечитаемо
    @Override
    public Profanity getProfanityByWord(String word) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery criteria = criteriaBuilder.createQuery(Profanity.class);
            Root<Profanity> root = criteria.from(Profanity.class);

            criteria.where(criteriaBuilder.equal(root.get("wordValue"), word));
            Profanity profanity = (Profanity) session.createQuery(criteria).uniqueResult();
            session.close();
            return profanity;
        }
    }

    @Override
    public List<Profanity> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery criteria = criteriaBuilder.createQuery(Profanity.class);

            criteria.from(Profanity.class);
            List<Profanity> profanities = session.createQuery(criteria).getResultList();
            return profanities;
        }
    }

    @Override
    public Profanity updateProfanity(Profanity profanity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            MutationQuery query = session.createMutationQuery("UPDATE Profanity SET wordValue =: word_value WHERE id =: p_id");
            query.setParameter("word_value", profanity.getWordValue());
            query.setParameter("p_id", profanity.getId());
            query.executeUpdate();
            transaction.commit();
            session.close();
            return getProfanity(profanity.getId());
        }
    }

    @Override
    public void deleteProfanity(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Profanity profanity = session.get(Profanity.class, id);
            session.remove(profanity);
            transaction.commit();
        }
    }

    @Override
    public void deleteProfanityByWord(String word) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            MutationQuery query = session.createMutationQuery("DELETE Profanity WHERE wordValue =: word_value");
            query.setParameter("word_value", word);
            query.executeUpdate();

            transaction.commit();
        }
    }
}
