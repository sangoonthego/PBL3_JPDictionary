package com.jpdictionary.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "words")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String word;

    private String reading;
    private String meaning;
    private Long part_of_speech_id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
    private String jlpt;

    // ✅ NEW: Common word flag
    private Boolean is_common;
    
    public Word() {
		// Default constructor
	}
    
    public Word(Long id, String word, String reading, String meaning, Long partOfSpeechId, Date createdAt, String jlpt, Boolean is_common) {
		this.id = id;
		this.word = word;
		this.reading = reading;
		this.meaning = meaning;
		this.part_of_speech_id = partOfSpeechId;
		this.createdAt = createdAt;
		this.jlpt = jlpt;
		this.is_common = is_common;
	}
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getWord() { return word; }
    public void setWord(String word) { this.word = word; }

    public String getReading() { return reading; }
    public void setReading(String reading) { this.reading = reading; }

    public String getMeaning() { return meaning; }
    public void setMeaning(String meaning) { this.meaning = meaning; }

    public Long getPartOfSpeech() { return part_of_speech_id; }
    public void setPartOfSpeech(Long partOfSpeechId) { this.part_of_speech_id = partOfSpeechId; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    
    public String getJlpt() { return jlpt; }
    public void setJlpt(String jlpt) { this.jlpt = jlpt; }

    public Boolean getIs_common() { return is_common; }
    public void setIs_common(Boolean is_common) { this.is_common = is_common; }
}
