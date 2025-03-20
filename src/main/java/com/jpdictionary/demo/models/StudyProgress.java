package com.jpdictionary.demo.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "study_progress")
public class StudyProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "word_id", nullable = false)
    private Word word;
    
    private int lastReviewed;
    private int reviewCount;
    private int proficiencyLevel;
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Word getWord() { return word; }
    public void setWord(Word word) { this.word = word; }

    public int getLastReviewed() { return lastReviewed; }
    public void setLastReviewed(int lastReviewed) { this.lastReviewed = lastReviewed; }

    public int getReviewCount() { return reviewCount; }
    public void setReviewCount(int reviewCount) { this.reviewCount = reviewCount; }

    public int getProficiencyLevel() { return proficiencyLevel; }
    public void setProficiencyLevel(int proficiencyLevel) { this.proficiencyLevel = proficiencyLevel; }
}
