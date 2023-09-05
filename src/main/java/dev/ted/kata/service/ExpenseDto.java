package dev.ted.kata.service;

import dev.ted.kata.domain.ExpenseType;

import java.util.List;

public record ExpenseDto(ExpenseType mealExpenses, int amount) {
}
