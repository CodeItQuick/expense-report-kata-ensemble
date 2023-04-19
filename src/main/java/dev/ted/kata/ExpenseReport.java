package dev.ted.kata;

import java.time.LocalDate;
import java.util.List;

public class ExpenseReport {

    private final DateProvider dateProvider;

    protected ExpenseReport(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
    }

    public static ExpenseReport create() {
        return new ExpenseReport(new RealDateProvider());
    }

    public void printReport(List<Expense> expenses) {
        int total = 0;
        int mealExpenses = 0;

        print("Expenses " + dateProvider.currentDate());

        for (Expense expense : expenses) {
            if (expense.type == ExpenseType.DINNER || expense.type == ExpenseType.BREAKFAST) {
                mealExpenses += expense.amount;
            }

            String expenseName = switch (expense.type) {
                case DINNER -> "Dinner";
                case BREAKFAST -> "Breakfast";
                case CAR_RENTAL -> "Car Rental";
            };

            boolean flagMeal = isOverexpensedMeal(expense);
            if (flagMeal) {
                print(expenseName + "\t" + expense.amount + "\t" + "X");
            }
            else {
                print(expenseName + "\t" + expense.amount + "\t" + " ");
            }


            total += expense.amount;
        }

        print("Meal expenses: " + mealExpenses);
        print("Total expenses: " + total);
    }

    private boolean isOverexpensedMeal(Expense expense) {
        boolean dinnerOverExpensed = expense.type == ExpenseType.DINNER && expense.amount > 5000;
        boolean breakfastOverExpensed = expense.type == ExpenseType.BREAKFAST && expense.amount > 1000;
        return dinnerOverExpensed || breakfastOverExpensed;
    }

    protected void print(String message) {
        System.out.println(message);
    }

    public static class RealDateProvider implements DateProvider {
        public RealDateProvider() {
        }

        @Override
        public LocalDate currentDate() {
            return LocalDate.now();
        }
    }

    public interface DateProvider {
        LocalDate currentDate();
    }
}
