package com.jpdictionary.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "kanji")
public class Kanji {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(nullable = false, unique = true) 
    private String kanji;

    private String onyomi;
    private String kunyomi;
    private String meaning;
    private int strokes;
    private int jlptLevel;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
