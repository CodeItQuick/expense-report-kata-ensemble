package dev.ted.kata;

import java.time.LocalDate;
import java.util.List;

public class ExpenseReport {

    private final DateProvider dateProvider;
    private final ExpenseDisplayLayer expenseDisplay;

    protected ExpenseReport(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
        expenseDisplay = new ExpenseDisplayLayer(this);
    }

    public static ExpenseReport create() {
        final RealDateProvider dateProvider1 = new RealDateProvider();
        return new ExpenseReport(dateProvider1);
    }

    public void printReport(List<Expense> expenses) {
        expenseDisplay.printReportTitle(this.dateProvider);
        expenseDisplay.printIndividualExpense(ExpenseEngine.calculateIndividualExpenses(expenses));
        expenseDisplay.printMealExpenseTotal(ExpenseEngine.calculateMealExpenses(expenses));
        expenseDisplay.printTotalExpenses(ExpenseEngine.calculateTotalExpenses(expenses));
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
