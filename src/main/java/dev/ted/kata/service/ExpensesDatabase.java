package dev.ted.kata.service;

import dev.ted.kata.domain.Expense;
import dev.ted.kata.domain.ExpenseType;

import java.util.List;

public class ExpensesDatabase {
    public List<ExpenseDto> expenses = List.of(
            new ExpenseDto(ExpenseType.BREAKFAST, 500),
            new ExpenseDto(ExpenseType.DINNER, 5010),
            new ExpenseDto(ExpenseType.CAR_RENTAL, 1010)
    );
}
