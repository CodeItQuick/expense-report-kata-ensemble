package dev.ted.kata.adapter;

import dev.ted.kata.service.ExpenseDto;
import dev.ted.kata.service.testableProviderInterfaces.DateProvider;
import dev.ted.kata.service.ExpensesService;

import java.util.List;

public class ExpensePrinter {

    private final SystemOutProvider systemOutProvider;
    private final ExpensesService expensesService;

    public ExpensePrinter(DateProvider dateProvider, List<ExpenseDto> expenseList, SystemOutProvider systemOutProvider) {
        this.systemOutProvider = systemOutProvider;
        this.expensesService = new ExpensesService(dateProvider, expenseList);
    }

    public ExpensePrinter(DateProvider dateProvider) {
        this.systemOutProvider = new SystemOutProvider();
        this.expensesService = new ExpensesService(dateProvider);
    }

    public static ExpensePrinter create() {
        return new ExpensePrinter(null);
    }

    public void printExistingReport() {
        ExpenseView expenseView = expensesService.viewExpenses();

        print(expenseView.reportTitle());
        for(String expenseMessage: expenseView.displayIndividualExpenses()) {
            print(expenseMessage);
        }
        print(expenseView.mealExpenseTotal());
        print(expenseView.totalExpenses());
    }

    // outside world
    private void print(String message) {
        this.systemOutProvider.ServicePrint(message);
    }

}
