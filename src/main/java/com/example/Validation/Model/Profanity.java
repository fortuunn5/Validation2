package com.example.Validation.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "profanity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Profanity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "word_value")
    private String wordValue;

    public Profanity(String wordValue) {
        this.wordValue = wordValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profanity profanity = (Profanity) o;
        return getId() == profanity.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
