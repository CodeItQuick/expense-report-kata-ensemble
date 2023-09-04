package dev.ted.kata.domain;

import java.util.ArrayList;
import java.util.List;

public class Expenses {
    private List<Expense> expenses;

    public Expenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public List<String> calculateIndividualExpenses() {
        List<String> displayExpenses = new ArrayList<>();
        for (Expense expense : this.expenses) {
            String label = expense.expenseType() + "\t" + expense.amount() + "\t" + expense.isOverexpensedMeal();
            displayExpenses.add(label);
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

}
