package com.jpdictionary.demo.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class DailyLookupUserId implements Serializable {

    private LocalDate lookupDate;
    private Long user;

    public DailyLookupUserId() {}

    public DailyLookupUserId(LocalDate lookupDate, Long user) {
        this.lookupDate = lookupDate;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DailyLookupUserId)) return false;
        DailyLookupUserId that = (DailyLookupUserId) o;
        return Objects.equals(lookupDate, that.lookupDate) &&
               Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lookupDate, user);
    }
}
