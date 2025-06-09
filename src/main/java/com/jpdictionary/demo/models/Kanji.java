package com.jpdictionary.demo.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "kanji")
public class Kanji {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String kanji;
    private String onyomi;
    private String kunyomi;
    private String meaning;
    private int strokes;

    @Column(name = "jlpt_level")
    private int jlptLevel;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    public Kanji() {
    	
    }
    
    public Kanji(String kanji, String onyomi, String kunyomi, String meaning, int strokes, int jlptLevel) {
		this.kanji = kanji;
		this.onyomi = onyomi;
		this.kunyomi = kunyomi;
		this.meaning = meaning;
		this.strokes = strokes;
		this.jlptLevel = jlptLevel;
		this.createdAt = new Date();
	}

    // Getters and Setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getKanji() { return kanji; }
    public void setKanji(String kanji) { this.kanji = kanji; }

    public String getOnyomi() { return onyomi; }
    public void setOnyomi(String onyomi) { this.onyomi = onyomi; }

    public String getKunyomi() { return kunyomi; }
    public void setKunyomi(String kunyomi) { this.kunyomi = kunyomi; }

    public String getMeaning() { return meaning; }
    public void setMeaning(String meaning) { this.meaning = meaning; }

    public int getStrokes() { return strokes; }
    public void setStrokes(int strokes) { this.strokes = strokes; }

    public int getJlptLevel() { return jlptLevel; }
    public void setJlptLevel(int jlptLevel) { this.jlptLevel = jlptLevel; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
