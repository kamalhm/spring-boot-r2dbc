package dev.kamalhm.reactivepostgres.entity;

import org.springframework.data.annotation.Id;

public record Member(@Id Long id, String name) {
    public Member(String name) {
        this(null, name); // `id` is null, it will be auto-generated when persisted
    }

}
