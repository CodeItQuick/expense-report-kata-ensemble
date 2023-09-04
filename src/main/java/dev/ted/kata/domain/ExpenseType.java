package dev.ted.kata.domain;

public enum ExpenseType {
    DINNER("Dinner"), BREAKFAST("Breakfast"), CAR_RENTAL("Car Rental");
    private final String expenseType;
    ExpenseType(String dinner) {
        this.expenseType = dinner;
    }

    public String getString() { return expenseType; }
}
