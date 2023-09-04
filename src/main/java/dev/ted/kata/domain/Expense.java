package dev.ted.kata.domain;

public class Expense {
    private ExpenseType type;
    private int amount;

    public Expense(ExpenseType type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    public int calculateMealExpenses() {
       if (type == ExpenseType.DINNER || type == ExpenseType.BREAKFAST) {
           return amount;
       }
       return 0;
   }

    public String isOverexpensedMeal() {
        boolean dinnerOverExpensed = type == ExpenseType.DINNER && amount > 5000;
        boolean breakfastOverExpensed = type == ExpenseType.BREAKFAST && amount > 1000;
        return dinnerOverExpensed || breakfastOverExpensed ? "X" : " ";
    }

    public String calculateExpenseString() {
        String expenseName = switch (type) {
            case DINNER -> "Dinner";
            case BREAKFAST -> "Breakfast";
            case CAR_RENTAL -> "Car Rental";
        };

        return expenseName;
    }

    public int amount() {
        return amount;
    }
}
