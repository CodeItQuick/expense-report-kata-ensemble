package dev.ted.kata;

import java.util.ArrayList;
import java.util.List;

class TestableExpenseReport extends ExpenseReport {
    private final List<String> message = new ArrayList<>();

    TestableExpenseReport(ExpenseReport.DateProvider dateProvider) {
        super(dateProvider);
    }

    @Override
    protected void print(String message) {
        this.message.add(message);
    }

    public List<String> report() {
        return message;
    }
}
