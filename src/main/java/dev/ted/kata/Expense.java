package dev.ted.kata;

class Expense {
    ExpenseType type;
    int amount;
    public int calculateMealExpenses() {
       if (type == ExpenseType.DINNER || type == ExpenseType.BREAKFAST) {
           return amount;
       }
       return 0;
   }

    public boolean isOverexpensedMeal() {
        boolean dinnerOverExpensed = type == ExpenseType.DINNER && amount > 5000;
        boolean breakfastOverExpensed = type == ExpenseType.BREAKFAST && amount > 1000;
        return dinnerOverExpensed || breakfastOverExpensed;
    }

    public String calculateExpenseString() {
        String expenseName = switch (type) {
            case DINNER -> "Dinner";
            case BREAKFAST -> "Breakfast";
            case CAR_RENTAL -> "Car Rental";
        };

        return expenseName;
    }
}
