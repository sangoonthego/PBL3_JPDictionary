package com.jpdictionary.demo.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "flashcard_words")
public class FlashcardWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flashcard_id", nullable = false)
    private Flashcard flashcard;

    @ManyToOne
    @JoinColumn(name = "word_id", nullable = false)
    private Word word;
    
    public FlashcardWord() {
		
	}
    
    public FlashcardWord(Flashcard flashcard, Word word) {
		this.flashcard = flashcard;
		this.word = word;
	}
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		// TODO Auto-generated method stub
		this.id = id;
	}
	
	public Flashcard getFlashcard() {
		return flashcard;
	}
	
	public void setFlashcard(Flashcard flashcard) {
		this.flashcard = flashcard;
	}
}
