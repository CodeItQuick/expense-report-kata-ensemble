package dev.ted.kata;

import java.time.LocalDate;
import java.util.List;

public class ExpenseReport {

    private final DateProvider dateProvider;
    private final ExpenseView expenseView;

    protected ExpenseReport(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
        expenseView = new ExpenseView();
    }

    public static ExpenseReport create() {
        final RealDateProvider dateProvider1 = new RealDateProvider();
        return new ExpenseReport(dateProvider1);
    }

    public void printReport(List<Expense> expenses) {
        print(expenseView.reportTitle(this.dateProvider));
        for (DisplayExpense individualExpense : ExpenseEngine.calculateIndividualExpenses(expenses)) {
            print(expenseView.individualExpenses(individualExpense));
        }
        print(expenseView.mealExpenseTotal(ExpenseEngine.calculateMealExpenses(expenses)));
        print(expenseView.totalExpenses(ExpenseEngine.calculateTotalExpenses(expenses)));
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
