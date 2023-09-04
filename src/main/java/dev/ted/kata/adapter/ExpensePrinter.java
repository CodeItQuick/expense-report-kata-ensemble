package dev.ted.kata.adapter;

import dev.ted.kata.domain.DisplayExpense;
import dev.ted.kata.domain.Expense;
import dev.ted.kata.domain.Expenses;

import java.time.LocalDate;
import java.util.List;

public class ExpensePrinter {

    private final DateProvider dateProvider;

    protected ExpensePrinter(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
    }

    public static ExpensePrinter create() {
        final RealDateProvider dateProvider1 = new RealDateProvider();
        return new ExpensePrinter(dateProvider1);
    }

    public void printReport(List<Expense> expenseList) {
        Expenses expenses = new Expenses(expenseList);
        int mealExpenses = expenses.calculateMealExpenses();
        int total = expenses.calculateTotalExpenses();
        ExpenseView expenseView = new ExpenseView(mealExpenses, total);

        print(expenseView.reportTitle(this.dateProvider));
        for (DisplayExpense individualExpense : expenses.calculateIndividualExpenses()) {
            print(expenseView.individualExpenses(individualExpense));
        }

        print(expenseView.mealExpenseTotal());
        print(expenseView.totalExpenses());
    }

    // outside world
    protected void print(String message) {
        System.out.println(message);
    }

    public static class RealDateProvider implements DateProvider {
        public RealDateProvider() {
        }

        @Override
        public LocalDate currentDate() {
            return LocalDate.now();
        }
    }

    public interface DateProvider {
        LocalDate currentDate();
    }
}
