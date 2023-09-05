package dev.ted.kata.service;

import java.util.List;

class ExistingExpensesRepository {

    private ExpensesDatabase existingExpensesProvider;

    public ExistingExpensesRepository() {
        this.existingExpensesProvider = new ExpensesDatabase();
    }

    public List<ExpenseDto> AllExpenses() {
        return existingExpensesProvider.expenses;
    }

    public void AddExpenses(List<ExpenseDto> expenseList) {
        this.existingExpensesProvider.expenses = expenseList;
    }
}
