package dev.ted.kata;

import dev.ted.kata.adapter.ExpensePrinter;
import dev.ted.kata.domain.Expense;
import dev.ted.kata.domain.ExpenseType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ExpenseReportTest {

    @Test
    public void emptyExpenseReportDoesNotThrowException() {
        ExpensePrinter expenseReport = ExpensePrinter.create();

        assertThatCode(() -> expenseReport.printReport(Collections.emptyList()))
                .doesNotThrowAnyException();
    }

    @Test
    public void emptyExpenseReportShowsEmptyReceipt() {
        TestableExpenseReport expenseReport = new TestableExpenseReport(
                () -> LocalDate.parse("2023-04-05"));

        expenseReport.printReport(Collections.emptyList());

        assertThat(expenseReport.report())
                .containsExactly(
                        "Expenses 2023-04-05",
                        "Meal expenses: 0",
                        "Total expenses: 0"
                        );
    }

    @Test
    public void oneBreakfastExpenseReportShowsMealExpense() {
        TestableExpenseReport expenseReport = new TestableExpenseReport(
                () -> LocalDate.parse("2023-04-05"));

        Expense expense = new Expense(ExpenseType.BREAKFAST, 10);

        expenseReport.printReport(List.of(expense));

        assertThat(expenseReport.report())
                .containsExactly(
                        "Expenses 2023-04-05",
                        "Breakfast	10	 ",
                        "Meal expenses: 10",
                        "Total expenses: 10"
                        );
    }
    @Test
    public void oneDinnerExpenseReportShowsMealExpense() {
        TestableExpenseReport expenseReport = new TestableExpenseReport(
                () -> LocalDate.parse("2023-04-05"));

        Expense expense = new Expense(ExpenseType.DINNER, 10);

        expenseReport.printReport(List.of(expense));

        assertThat(expenseReport.report())
                .containsExactly(
                        "Expenses 2023-04-05",
                        "Dinner\t10\t ",
                        "Meal expenses: 10",
                        "Total expenses: 10"
                        );
    }
    @Test
    public void oneCarRentalExpenseReportShowsMealExpense() {
        TestableExpenseReport expenseReport = new TestableExpenseReport(
                () -> LocalDate.parse("2023-04-05"));
        Expense expense = new Expense(ExpenseType.CAR_RENTAL, 10);

        expenseReport.printReport(List.of(expense));

        assertThat(expenseReport.report())
                .containsExactly(
                        "Expenses 2023-04-05",
                        "Car Rental\t10\t ",
                        "Meal expenses: 0",
                        "Total expenses: 10"
                        );
    }
    @Test
    public void oneDinnerExpenseOverMaximumReportShowsMealExpenseAndMarker() {
        TestableExpenseReport expenseReport = new TestableExpenseReport(
                () -> LocalDate.parse("2023-04-05"));
        Expense expense = new Expense(ExpenseType.DINNER, 5010);

        expenseReport.printReport(List.of(expense));

        assertThat(expenseReport.report())
                .containsExactly(
                        "Expenses 2023-04-05",
                        "Dinner\t5010\tX",
                        "Meal expenses: 5010",
                        "Total expenses: 5010"
                        );
    }
    @Test
    public void oneBreakfastExpenseOverMaximumReportShowsMealExpenseAndMarker() {
        TestableExpenseReport expenseReport = new TestableExpenseReport(
                () -> LocalDate.parse("2023-04-05"));
        Expense expense = new Expense(ExpenseType.BREAKFAST, 1010);

        expenseReport.printReport(List.of(expense));

        assertThat(expenseReport.report())
                .containsExactly(
                        "Expenses 2023-04-05",
                        "Breakfast\t1010\tX",
                        "Meal expenses: 1010",
                        "Total expenses: 1010"
                        );
    }
    @Test
    public void multipleMealsReportShowsAllExpenses() {
        TestableExpenseReport expenseReport = new TestableExpenseReport(
                () -> LocalDate.parse("2023-04-05"));
        Expense firstExpense = new Expense(ExpenseType.BREAKFAST, 500);
        Expense secondExpense = new Expense(ExpenseType.DINNER, 5010);
        Expense thirdExpense = new Expense(ExpenseType.CAR_RENTAL, 1010);

        expenseReport.printReport(List.of(firstExpense, secondExpense, thirdExpense));

        assertThat(expenseReport.report())
                .containsExactly(
                        "Expenses 2023-04-05",
                        "Breakfast	500	 ",
                        "Dinner	5010	X",
                        "Car Rental	1010	 ",
                        "Meal expenses: 5510",
                        "Total expenses: 6520"
                        );
    }

}
