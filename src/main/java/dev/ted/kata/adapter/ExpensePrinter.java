package dev.ted.kata.adapter;

import dev.ted.kata.domain.DisplayExpense;
import dev.ted.kata.domain.Expense;
import dev.ted.kata.domain.Expenses;
import dev.ted.kata.service.DateProvider;
import dev.ted.kata.service.RealDateProvider;
import dev.ted.kata.service.SystemOutProvider;

import java.util.List;

public class ExpensePrinter {

    private final DateProvider dateProvider;
    private final SystemOutProvider systemOutProvider;

    protected ExpensePrinter(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
        this.systemOutProvider = new SystemOutProvider();
    }

    public static ExpensePrinter create() {
        return new ExpensePrinter(new RealDateProvider());
    }

    public void printReport(List<Expense> expenseList) {
        Expenses expenses = new Expenses(expenseList, this.dateProvider);

        ExpenseView expenseView = expenses.viewExpenses();

        print(expenseView.reportTitle());
        for(String expenseMessage: expenseView.displayIndividualExpenses()) {
            print(expenseMessage);
        }
        print(expenseView.mealExpenseTotal());
        print(expenseView.totalExpenses());
    }

    // outside world
    protected void print(String message) {
        this.systemOutProvider.ServicePrint(message);
    }

}
