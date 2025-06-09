package com.jpdictionary.demo.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "parts_of_speech")
public class PartOfSpeech {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String pos_name;
    
    public PartOfSpeech() {
		// Default constructor
	}
    
    public PartOfSpeech(Long id, String pos_name) {
		this.id = id;
		this.pos_name = pos_name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPosName() {
		return pos_name;
	}

	public void setPosName(String pos_name) {
		this.pos_name = pos_name;
	}
}
