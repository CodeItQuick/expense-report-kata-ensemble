package dev.ted.kata.service;

import dev.ted.kata.domain.Expense;
import dev.ted.kata.domain.ExpenseType;

import java.util.List;

public class ExistingExpensesProvider {
    public List<Expense> expenses = List.of(
            new Expense(ExpenseType.BREAKFAST, 500),
            new Expense(ExpenseType.DINNER, 5010),
            new Expense(ExpenseType.CAR_RENTAL, 1010)
    );
}
