package dev.ted.kata;

import java.time.LocalDate;
import java.util.List;

public class ExpenseReport {

    private final DateProvider dateProvider;
    private final ExpenseDisplayLayer expenseDisplayLayer;

    protected ExpenseReport(DateProvider dateProvider, ExpenseDisplayLayer expenseDisplayLayer) {
        this.dateProvider = dateProvider;
        this.expenseDisplayLayer = expenseDisplayLayer;
    }

    public static ExpenseReport create() {
        final RealDateProvider dateProvider1 = new RealDateProvider();
        return new ExpenseReport(dateProvider1, new ExpenseDisplayLayer());
    }

    public void printReport(List<Expense> expenses) {

        expenseDisplayLayer.printReportTitle(this, dateProvider);

        List<DisplayExpense> displayExpenses = ExpenseEngine.calculateIndividualExpenses(expenses);
        expenseDisplayLayer.printIndividualExpense(this, displayExpenses);

        int mealExpenses = ExpenseEngine.calculateMealExpenses(expenses);
        expenseDisplayLayer.printMealExpenseTotal(mealExpenses, this);

        int total = ExpenseEngine.calculateTotalExpenses(expenses);
        expenseDisplayLayer.printTotalExpenses(total, this);
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
