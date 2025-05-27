package com.jpdictionary.demo.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "flashcards")
public class Flashcard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "flashcard_name", nullable = false, length = 100)
    private String name;
    
    public Flashcard() {
		
	}
    
    public Flashcard(User user, String name) {
		this.user = user;
		this.name = name;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
