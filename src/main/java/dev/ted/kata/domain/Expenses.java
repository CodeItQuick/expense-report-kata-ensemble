package dev.ted.kata.domain;

import dev.ted.kata.service.DateProvider;
import dev.ted.kata.adapter.ExpenseView;

import java.util.ArrayList;
import java.util.List;

public class Expenses {
    private List<Expense> expenses;
    private DateProvider dateProvider;

    public Expenses(List<Expense> expenses, DateProvider dateProvider) {
        this.expenses = expenses;
        this.dateProvider = dateProvider;
    }

    public List<DisplayExpense> calculateIndividualExpenses() {
        List<DisplayExpense> displayExpenses = new ArrayList<>();
        for (Expense expense : this.expenses) {
            DisplayExpense singleExpense = new DisplayExpense();
            singleExpense.type = expense.calculateExpenseString();
            singleExpense.isOverExpensed = expense.isOverexpensedMeal() ? "X" : " ";
            singleExpense.amount = expense.amount();
            displayExpenses.add(singleExpense);
        }
        return displayExpenses;
    }

     public int calculateTotalExpenses() {
        int total = 0;
        for (Expense expense : this.expenses) {
            total += expense.amount();
        }
        return total;
    }

     public int calculateMealExpenses() {
        int mealExpenses = 0;
        for (Expense expense : this.expenses) {
            mealExpenses += expense.calculateMealExpenses();
        }
        return mealExpenses;
    }

    public ExpenseView viewExpenses() {
        int mealExpenses = calculateMealExpenses();
        int total = calculateTotalExpenses();
        List<DisplayExpense> individualExpenses = calculateIndividualExpenses();
        return new ExpenseView(mealExpenses, total, dateProvider.currentDate().toString(), individualExpenses);
    }
}
