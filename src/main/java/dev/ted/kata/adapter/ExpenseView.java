package dev.ted.kata.adapter;

import java.util.ArrayList;
import java.util.List;

public class ExpenseView {

    private int mealExpenses;
    private int totalExpenses;
    private String expenseDate;
    private List<String> individualExpenses;

    public ExpenseView(int mealExpenses, int total, String expenseDate, List<String> individualExpensesTwo) {
        this.mealExpenses = mealExpenses;
        this.totalExpenses = total;
        this.expenseDate = expenseDate;
        this.individualExpenses = individualExpensesTwo;
    }

    List<String> displayIndividualExpenses() {
        List<String> individualExpenses = new ArrayList<>();
        for (String individualExpense : this.individualExpenses) {
            String message = individualExpense;
            individualExpenses.add(message);
        }
        return individualExpenses;
    }

    public String reportTitle() {
        return "Expenses " + this.expenseDate;
    }

    public String mealExpenseTotal() {
        return "Meal expenses: " + this.mealExpenses;
    }

    public String totalExpenses() {
        return "Total expenses: " + this.totalExpenses;
    }

}
