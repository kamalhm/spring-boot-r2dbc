package dev.kamalhm.reactivepostgres.dto;

public record CreateTransactionWebRequest(Long from, Long to, Long amount) {
}