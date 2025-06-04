package com.jpdictionary.demo.models;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class SavedWordId implements Serializable {
    private Long userId;
    private Long wordId;

    public SavedWordId() {}

    public SavedWordId(Long userId, Long wordId) {
        this.userId = userId;
        this.wordId = wordId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SavedWordId that)) return false;
        return Objects.equals(userId, that.userId) && Objects.equals(wordId, that.wordId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, wordId);
    }
}
