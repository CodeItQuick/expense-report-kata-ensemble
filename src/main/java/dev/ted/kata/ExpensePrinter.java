package dev.ted.kata;

import java.time.LocalDate;
import java.util.List;

public class ExpensePrinter {

    private final DateProvider dateProvider;
    private final ExpenseView expenseView;

    protected ExpensePrinter(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
        expenseView = new ExpenseView();
    }

    public static ExpensePrinter create() {
        final RealDateProvider dateProvider1 = new RealDateProvider();
        return new ExpensePrinter(dateProvider1);
    }

    public void printReport(List<Expense> expenses) {
        print(expenseView.reportTitle(this.dateProvider));
        ExpenseEngine expenseEngine = new ExpenseEngine(expenses);
        for (DisplayExpense individualExpense : expenseEngine.calculateIndividualExpenses()) {
            print(expenseView.individualExpenses(individualExpense));
        }
        print(expenseView.mealExpenseTotal(expenseEngine.calculateMealExpenses()));
        print(expenseView.totalExpenses(expenseEngine.calculateTotalExpenses()));
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
