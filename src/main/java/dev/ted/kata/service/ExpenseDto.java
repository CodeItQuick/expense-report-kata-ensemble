package dev.ted.kata.service;

import dev.ted.kata.domain.ExpenseType;

public record ExpenseDto(ExpenseType mealExpenses, int amount) {
}
