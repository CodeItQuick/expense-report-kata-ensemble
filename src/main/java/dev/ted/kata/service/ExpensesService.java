package dev.ted.kata.service;

import dev.ted.kata.adapter.ExpenseView;
import dev.ted.kata.domain.Expense;
import dev.ted.kata.domain.Expenses;

import java.util.List;

public class ExpensesService {
    private DateProvider dateProvider;

    public ExpensesService(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
    }

    public ExpenseView viewExpenses(List<Expense> expenseList) {
        Expenses expenses = new Expenses(expenseList);
        int mealExpenses = expenses.calculateMealExpenses();
        int total = expenses.calculateTotalExpenses();
        List<String> individualExpenses = expenses.calculateIndividualExpenses();
        return new ExpenseView(mealExpenses, total, this.dateProvider.currentDate().toString(), individualExpenses);
    }
}
