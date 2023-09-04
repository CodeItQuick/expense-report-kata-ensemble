package dev.ted.kata.domain;

import dev.ted.kata.service.DateProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Expenses {
    private List<Expense> expenses;

    public Expenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public List<String> calculateIndividualExpenses() {
        List<DisplayExpense> displayExpenses = new ArrayList<>();
        for (Expense expense : this.expenses) {
            String type = expense.calculateExpenseString();
            String isOverExpensed = expense.isOverexpensedMeal();
            int amount = expense.amount();
            String expenseLabel = expense.calculateExpenseString() + "\t" + expense.amount()  + "\t" + expense.isOverexpensedMeal();
            DisplayExpense singleExpense = new DisplayExpense(amount, type, isOverExpensed, expenseLabel);
            displayExpenses.add(singleExpense);
        }
        return displayExpenses.stream().map(x -> x.expenseLabel()).collect(Collectors.toList());
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
