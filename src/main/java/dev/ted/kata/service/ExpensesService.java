package dev.ted.kata.service;

import dev.ted.kata.adapter.ExpenseView;
import dev.ted.kata.domain.Expense;
import dev.ted.kata.domain.Expenses;

import java.util.List;

public class ExpensesService {
    private DateProvider dateProvider;
    private List<Expense> expenseList;

    public ExpensesService(DateProvider dateProvider, List<Expense> expenseList) {
        this.dateProvider = dateProvider;
        this.expenseList = expenseList;
    }

    public ExpensesService(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
        this.expenseList = new ExistingExpensesProvider().expenses;
    }

    public ExpenseView viewExpenses() {
        Expenses expenses = new Expenses(this.expenseList);
        int mealExpenses = expenses.calculateMealExpenses();
        int total = expenses.calculateTotalExpenses();
        List<String> individualExpenses = expenses.calculateIndividualExpenses();
        return new ExpenseView(mealExpenses, total, this.dateProvider.currentDate().toString(), individualExpenses);
    }
}
