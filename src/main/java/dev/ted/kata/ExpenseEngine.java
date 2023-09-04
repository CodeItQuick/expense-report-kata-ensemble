package dev.ted.kata;

import java.util.ArrayList;
import java.util.List;

public class ExpenseEngine {
    private List<Expense> expenses;

    public ExpenseEngine(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public String calculateExpenseString(Expense expense) {
        String expenseName = switch (expense.type) {
            case DINNER -> "Dinner";
            case BREAKFAST -> "Breakfast";
            case CAR_RENTAL -> "Car Rental";
        };

        return expenseName;
    }

     public int calculateMealExpenses(Expense expense) {
        if (expense.type == ExpenseType.DINNER || expense.type == ExpenseType.BREAKFAST) {
            return expense.amount;
        }
        return 0;
    }

     public boolean isOverexpensedMeal(Expense expense) {
        boolean dinnerOverExpensed = expense.type == ExpenseType.DINNER && expense.amount > 5000;
        boolean breakfastOverExpensed = expense.type == ExpenseType.BREAKFAST && expense.amount > 1000;
        return dinnerOverExpensed || breakfastOverExpensed;
    }

     public List<DisplayExpense> calculateIndividualExpenses(List<Expense> expenses) {
        List<DisplayExpense> displayExpenses = new ArrayList<>();
        for (Expense expense : this.expenses) {
            DisplayExpense singleExpense = new DisplayExpense();
            singleExpense.type = calculateExpenseString(expense);
            singleExpense.isOverExpensed = isOverexpensedMeal(expense) ? "X" : " ";
            singleExpense.amount = expense.amount;
            displayExpenses.add(singleExpense);
        }
        return displayExpenses;
    }

     public int calculateTotalExpenses(List<Expense> expenses) {
        int total = 0;
        for (Expense expense : this.expenses) {
            total += expense.amount;
        }
        return total;
    }

     public int calculateMealExpenses(List<Expense> expenses) {
        int mealExpenses = 0;
        for (Expense expense : this.expenses) {
            mealExpenses += calculateMealExpenses(expense);
        }
        return mealExpenses;
    }
}
