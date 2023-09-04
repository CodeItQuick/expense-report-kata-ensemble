package dev.ted.kata.adapter;

import dev.ted.kata.domain.DisplayExpense;
import dev.ted.kata.domain.Expense;

import java.util.List;

public class ExpenseView {

    private DisplayExpense expense;
    private int mealExpenses;
    private int totalExpenses;

    public ExpenseView(int mealExpenses, int totalExpenses) {
        this.mealExpenses = mealExpenses;
        this.totalExpenses = totalExpenses;
    }

    public String individualExpenses(DisplayExpense expense) {
        return expense.type + "\t" + expense.amount + "\t" + expense.isOverExpensed;
    }

    public String reportTitle(ExpensePrinter.DateProvider dateProvider) {
        return "Expenses " + dateProvider.currentDate();
    }

    public String mealExpenseTotal() {
        return "Meal expenses: " + this.mealExpenses;
    }

    public String totalExpenses() {
        return "Total expenses: " + this.totalExpenses;
    }

}
