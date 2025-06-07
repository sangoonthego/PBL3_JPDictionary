package com.jpdictionary.demo.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "daily_lookup_users")
@IdClass(DailyLookupUserId.class)
public class DailyLookupUser {

    @Id
    @Column(name = "lookup_date")
    private LocalDate lookupDate;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Constructors
    public DailyLookupUser() {}

    public DailyLookupUser(LocalDate lookupDate, User user) {
        this.lookupDate = lookupDate;
        this.user = user;
    }

    // Getters and Setters
    public LocalDate getLookupDate() {
        return lookupDate;
    }

    public void setLookupDate(LocalDate lookupDate) {
        this.lookupDate = lookupDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
