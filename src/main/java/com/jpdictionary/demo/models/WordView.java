package com.jpdictionary.demo.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "word_views")
public class WordView {

    @Id
    //@OneToOne
    @JoinColumn(name = "word_id")
    private Long wordId;

    @Column(name = "view_count", nullable = false)
    private int viewCount = 0;

    public WordView() {
    	
    }

    public WordView(Long wordId) {
        this.wordId = wordId;
        this.viewCount = 0;
    }
    
    public Long getWordId() {
		return wordId;
	}
    
    public void setWordId(Long wordId) {
		this.wordId = wordId;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
}
