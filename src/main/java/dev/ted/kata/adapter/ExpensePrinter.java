package dev.ted.kata.adapter;

import dev.ted.kata.domain.Expense;
import dev.ted.kata.service.DateProvider;
import dev.ted.kata.service.ExpensesService;
import dev.ted.kata.service.RealDateProvider;
import dev.ted.kata.service.SystemOutProvider;

import java.util.List;

public class ExpensePrinter {

    private final SystemOutProvider systemOutProvider;
    private final ExpensesService expensesService;

    protected ExpensePrinter(DateProvider dateProvider, List<Expense> expenseList) {
        this.systemOutProvider = new SystemOutProvider();
        this.expensesService = new ExpensesService(dateProvider, expenseList);
    }

    public static ExpensePrinter create(List<Expense> expenseList) {
        return new ExpensePrinter(new RealDateProvider(), expenseList);
    }

    public void printReport() {
        ExpenseView expenseView = expensesService.viewExpenses();

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
