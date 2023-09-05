package dev.ted.kata;

import dev.ted.kata.domain.Expense;
import dev.ted.kata.service.ExpenseDto;
import dev.ted.kata.service.testableProviderInterfaces.DateProvider;
import dev.ted.kata.adapter.ExpensePrinter;

import java.util.ArrayList;
import java.util.List;

class TestableExpenseReport extends ExpensePrinter {
    private final List<String> message = new ArrayList<>();

    TestableExpenseReport(DateProvider dateProvider, List<ExpenseDto> expenseList) {
        super(dateProvider, expenseList);
    }

    @Override
    protected void print(String message) {
        this.message.add(message);
    }

    public List<String> report() {
        return message;
    }
}
