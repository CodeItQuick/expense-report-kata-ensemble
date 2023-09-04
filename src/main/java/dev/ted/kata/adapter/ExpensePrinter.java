package dev.ted.kata.adapter;

import dev.ted.kata.domain.Expense;
import dev.ted.kata.domain.Expenses;
import dev.ted.kata.service.DateProvider;
import dev.ted.kata.service.ExpensesService;
import dev.ted.kata.service.RealDateProvider;
import dev.ted.kata.service.SystemOutProvider;

import java.util.List;

public class ExpensePrinter {

    private final DateProvider dateProvider;
    private List<Expense> expenseList;
    private final SystemOutProvider systemOutProvider;
    private final ExpensesService expensesService;

    protected ExpensePrinter(DateProvider dateProvider, List<Expense> expenseList) {
        this.dateProvider = dateProvider;
        this.expenseList = expenseList;
        this.systemOutProvider = new SystemOutProvider();
        expensesService = new ExpensesService(dateProvider);
    }

    public static ExpensePrinter create(List<Expense> expenseList) {
        return new ExpensePrinter(new RealDateProvider(), expenseList);
    }

    public void printReport() {
        ExpenseView expenseView = expensesService.viewExpenses(this.expenseList);

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
