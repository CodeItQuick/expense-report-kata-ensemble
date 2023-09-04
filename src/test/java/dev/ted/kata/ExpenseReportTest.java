package dev.ted.kata;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ExpenseReportTest {

    @Test
    public void emptyExpenseReportDoesNotThrowException() {
        ExpenseReport expenseReport = ExpenseReport.create();

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

        Expense expense = new Expense();
        expense.amount = 10;
        expense.type = ExpenseType.BREAKFAST;

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

        Expense expense = new Expense();
        expense.amount = 10;
        expense.type = ExpenseType.DINNER;

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

        Expense expense = new Expense();
        expense.amount = 10;
        expense.type = ExpenseType.CAR_RENTAL;

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

        Expense expense = new Expense();
        expense.amount = 5010;
        expense.type = ExpenseType.DINNER;

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

        Expense expense = new Expense();
        expense.amount = 1010;
        expense.type = ExpenseType.BREAKFAST;

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

        Expense firstExpense = new Expense();
        firstExpense.amount = 500;
        firstExpense.type = ExpenseType.BREAKFAST;
        Expense secondExpense = new Expense();
        secondExpense.amount = 5010;
        secondExpense.type = ExpenseType.DINNER;
        Expense thirdExpense = new Expense();
        thirdExpense.amount = 1010;
        thirdExpense.type = ExpenseType.CAR_RENTAL;

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

    @Test
    public void expenseDisplayLayerCanPrintNoExpenses() {
        TestableExpenseReport expenseReport = new TestableExpenseReport(() -> LocalDate.parse("2023-04-05"));
        ExpenseDisplayLayer expenseDisplayLayer = new ExpenseDisplayLayer(expenseReport);
        List<DisplayExpense> expenses = new ArrayList<>();

        expenseDisplayLayer.printIndividualExpense(expenses);

        assertThat(expenseReport.report()).hasSize(0);
    }
    @Test
    public void expenseDisplayLayerCanPrintMultipleExpenses() {
        TestableExpenseReport expenseReport = new TestableExpenseReport(() -> LocalDate.parse("2023-04-05"));
        ExpenseDisplayLayer expenseDisplayLayer = new ExpenseDisplayLayer(expenseReport);
        DisplayExpense displayExpense = new DisplayExpense();
        displayExpense.isOverExpensed = true;
        displayExpense.amount = 10;
        displayExpense.type = "Breakfast";
        List<DisplayExpense> expenses = List.of(displayExpense);

        expenseDisplayLayer.printIndividualExpense(expenses);

        assertThat(expenseReport.report()).containsExactly("Breakfast	10	X");
    }

}
