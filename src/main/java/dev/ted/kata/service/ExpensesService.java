package dev.ted.kata.service;

import dev.ted.kata.adapter.ExpenseView;
import dev.ted.kata.domain.Expense;
import dev.ted.kata.domain.Expenses;
import dev.ted.kata.service.testableProviderInterfaces.DateProvider;

import java.util.List;
import java.util.stream.Collectors;

public class ExpensesService {
    private final ExistingExpensesRepository expenseRepository;
    private DateProvider dateProvider;

    public ExpensesService(DateProvider dateProvider, List<ExpenseDto> expenseList) {
        this.dateProvider = dateProvider;
        this.expenseRepository = new ExistingExpensesRepository();
        this.expenseRepository.AddExpenses(expenseList);
    }

    public ExpensesService(DateProvider dateProvider) {
        this.dateProvider = dateProvider != null ? dateProvider : new RealDateProvider();
        this.expenseRepository = new ExistingExpensesRepository();
    }

    public ExpenseView viewExpenses() {
        List<ExpenseDto> allExpenses = this.expenseRepository.AllExpenses();
        Expenses expenses = new Expenses(allExpenses.stream().map(x -> new Expense(x.mealExpenses(), x.amount())).collect(Collectors.toList()));
        int mealExpenses = expenses.calculateMealExpenses();
        int total = expenses.calculateTotalExpenses();
        List<String> individualExpenses = expenses.calculateIndividualExpenses();
        return new ExpenseView(mealExpenses, total, this.dateProvider.currentDate().toString(), individualExpenses);
    }
}
