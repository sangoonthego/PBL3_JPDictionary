package com.jpdictionary.demo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "saved_words")
public class SavedWord {

    @EmbeddedId
    private SavedWordId id;

    @ManyToOne
    @MapsId("userId")  // tên trường trong SavedWordId
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("wordId")  // tên trường trong SavedWordId
    @JoinColumn(name = "word_id")
    private Word word;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "saved_at")
    private Date savedAt = new Date();

    public SavedWord() {}

    public SavedWord(SavedWordId id) {
        this.id = id;
    }
    
    public SavedWord(SavedWordId id, User user, Word word) {
		this.id = id;
		this.user = user;
		this.word = word;
		this.savedAt = new Date(); // Set the saved date to now
	}
    
    public SavedWordId getId() {
		return id;
	}

	public void setId(SavedWordId id) {
		this.id = id;
	}
}
