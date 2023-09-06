package dev.ted.kata.service;

import java.util.List;

class ExistingExpensesRepository {

    private ExpensesDatabase expensesDatabase;

    public ExistingExpensesRepository() {
        this.expensesDatabase = new ExpensesDatabase();
    }

    public List<ExpenseDto> AllExpenses() {
        return expensesDatabase.expenses;
    }

    public void ReplaceAllExpenses(List<ExpenseDto> expenseList) {
        this.expensesDatabase.expenses = expenseList;
    }
}
