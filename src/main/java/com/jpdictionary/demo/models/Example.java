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

    @Column(nullable = false)
    private String sentenceJp;
    
    public Example() {
    	
    }
    
    public Example(Word word, String sentenceJp) {
		this.word = word;
		this.sentenceJp = sentenceJp;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		// TODO Auto-generated method stub
		this.id = id;
	}  

	public void setWord(Word word) {
		this.word = word;
	}

	public String getSentenceJp() {
		return sentenceJp;
	}

	public void setSentenceJp(String sentenceJp) {
		this.sentenceJp = sentenceJp;
	}
}
