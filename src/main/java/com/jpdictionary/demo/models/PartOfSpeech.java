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
    private String name;
    
    public PartOfSpeech() {
    	
    }
    
    public PartOfSpeech(Long id, String name) {
    			this.id = id;
		this.name = name;
    }
    
    public Long getId() {
    	return id;
    }

	public void setId(Long id) {
		// TODO Auto-generated method stub
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
