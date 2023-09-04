package dev.ted.kata.adapter;

import dev.ted.kata.domain.DisplayExpense;

import java.util.ArrayList;
import java.util.List;

public class ExpenseView {

    private int mealExpenses;
    private int totalExpenses;
    private String expenseDate;
    private List<DisplayExpense> individualExpenses;

    public ExpenseView(int mealExpenses, int totalExpenses, String expenseDate, List<DisplayExpense> individualExpenses) {
        this.mealExpenses = mealExpenses;
        this.totalExpenses = totalExpenses;
        this.expenseDate = expenseDate;
        this.individualExpenses = individualExpenses;
    }

    List<String> displayIndividualExpenses() {
        List<String> messages = new ArrayList<>();
        for (DisplayExpense individualExpense : individualExpenses) {
            String message = individualExpense.expenseLabel;
            messages.add(message);
        }
        return messages;
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
