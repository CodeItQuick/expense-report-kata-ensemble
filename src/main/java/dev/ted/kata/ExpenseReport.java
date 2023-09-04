package dev.ted.kata;

import java.time.LocalDate;
import java.util.List;

public class ExpenseReport {

    private final DateProvider dateProvider;
    private final ExpenseDisplayLayer expenseDisplayLayer;

    protected ExpenseReport(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
        this.expenseDisplayLayer = new ExpenseDisplayLayer(this);
    }

    public static ExpenseReport create() {
        final RealDateProvider dateProvider1 = new RealDateProvider();
        return new ExpenseReport(dateProvider1);
    }

    public void printReport(List<Expense> expenses) {

        List<DisplayExpense> displayExpenses = ExpenseEngine.calculateIndividualExpenses(expenses);
        int mealExpenses = ExpenseEngine.calculateMealExpenses(expenses);
        int total = ExpenseEngine.calculateTotalExpenses(expenses);

        ExpenseDisplayLayer expenseDisplay = new ExpenseDisplayLayer(this);
        expenseDisplay.printFullReport(
                this.dateProvider,
                displayExpenses,
                mealExpenses,
                total);
    }

    // outside world
    protected void print(String message) {
        System.out.println();
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
