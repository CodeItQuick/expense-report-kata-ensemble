package dev.ted.kata;

import java.time.LocalDate;
import java.util.List;

public class ExpenseReport {

    private final DateProvider dateProvider;
    private final ExpenseDisplayLayer expenseDisplay;

    protected ExpenseReport(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
        expenseDisplay = new ExpenseDisplayLayer();
    }

    public static ExpenseReport create() {
        final RealDateProvider dateProvider1 = new RealDateProvider();
        return new ExpenseReport(dateProvider1);
    }

    public void printReport(List<Expense> expenses) {
        print(expenseDisplay.printReportTitle(this.dateProvider));
        for (DisplayExpense individualExpense : ExpenseEngine.calculateIndividualExpenses(expenses)) {
            print(expenseDisplay.printIndividualExpenses(individualExpense));
        }
        print(expenseDisplay.printMealExpenseTotal(ExpenseEngine.calculateMealExpenses(expenses)));
        print(expenseDisplay.printTotalExpenses(ExpenseEngine.calculateTotalExpenses(expenses)));
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
