package com.jpdictionary.demo.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "examples")
public class Example {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "word_id", nullable = false)
    private Word word;

    @Column(name = "sentence_jp", nullable = false)
    private String sentenceJp;

    @Column(name = "sentence_en", nullable = false)
    private String sentenceEn;
    
    public Example() {
		// Default constructor
	}
    
    public Example(Long id, Word word, String sentenceJp, String sentenceEn) {
		this.id = id;
		this.word = word;
		this.sentenceJp = sentenceJp;
		this.sentenceEn = sentenceEn;
	}

    // Explicit getter and setter for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Explicit getter and setter for word
    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    // Explicit getter and setter for sentenceJp
    public String getSentenceJp() {
        return sentenceJp;
    }

    public void setSentenceJp(String sentenceJp) {
        this.sentenceJp = sentenceJp;
    }

    // Explicit getter and setter for sentenceEn
    public String getSentenceEn() {
        return sentenceEn;
    }

    public void setSentenceEn(String sentenceEn) {
        this.sentenceEn = sentenceEn;
    }
}
