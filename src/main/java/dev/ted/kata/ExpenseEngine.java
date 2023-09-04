package dev.ted.kata;

import java.util.ArrayList;
import java.util.List;

public class ExpenseEngine {
    static String printIndividualExpense(Expense expense) {
        String expenseName = switch (expense.type) {
            case DINNER -> "Dinner";
            case BREAKFAST -> "Breakfast";
            case CAR_RENTAL -> "Car Rental";
        };

        return expenseName;
    }

    static int calculateMealExpenses(Expense expense) {
        if (expense.type == ExpenseType.DINNER || expense.type == ExpenseType.BREAKFAST) {
            return expense.amount;
        }
        return 0;
    }

    static boolean isOverexpensedMeal(Expense expense) {
        boolean dinnerOverExpensed = expense.type == ExpenseType.DINNER && expense.amount > 5000;
        boolean breakfastOverExpensed = expense.type == ExpenseType.BREAKFAST && expense.amount > 1000;
        return dinnerOverExpensed || breakfastOverExpensed;
    }

    static List<DisplayExpense> calculateIndividualExpenses(List<Expense> expenses) {
        List<DisplayExpense> displayExpenses = new ArrayList<>();
        for (Expense expense : expenses) {
            DisplayExpense singleExpense = new DisplayExpense();
            singleExpense.type = printIndividualExpense(expense);
            singleExpense.isOverExpensed = isOverexpensedMeal(expense);
            singleExpense.amount = expense.amount;
            displayExpenses.add(singleExpense);
        }
        return displayExpenses;
    }

    static int calculateTotalExpenses(List<Expense> expenses) {
        int total = 0;
        for (Expense expense : expenses) {
            total += expense.amount;
        }
        return total;
    }

    static int calculateMealExpenses(List<Expense> expenses) {
        int mealExpenses = 0;
        for (Expense expense : expenses) {
            mealExpenses += calculateMealExpenses(expense);
        }
        return mealExpenses;
    }
}
