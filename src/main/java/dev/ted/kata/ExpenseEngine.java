package dev.ted.kata;

import java.util.ArrayList;
import java.util.List;

public class ExpenseEngine {
    private List<Expense> expenses;

    public ExpenseEngine(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public List<DisplayExpense> calculateIndividualExpenses() {
        List<DisplayExpense> displayExpenses = new ArrayList<>();
        for (Expense expense : this.expenses) {
            DisplayExpense singleExpense = new DisplayExpense();
            singleExpense.type = expense.calculateExpenseString();
            singleExpense.isOverExpensed = expense.isOverexpensedMeal() ? "X" : " ";
            singleExpense.amount = expense.amount;
            displayExpenses.add(singleExpense);
        }
        return displayExpenses;
    }

     public int calculateTotalExpenses() {
        int total = 0;
        for (Expense expense : this.expenses) {
            total += expense.amount;
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
