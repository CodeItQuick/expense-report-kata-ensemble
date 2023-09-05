package dev.ted.kata;

import dev.ted.kata.adapter.ExpensePrinter;
import dev.ted.kata.adapter.SystemOutProvider;
import dev.ted.kata.domain.ExpenseType;
import dev.ted.kata.service.ExpenseDto;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ExpenseReportTest {

    @Test
    public void emptyExpenseReportDoesNotThrowException() {
        ExpensePrinter expenseReport = ExpensePrinter.create();

        assertThatCode(() -> expenseReport.printExistingReport())
                .doesNotThrowAnyException();
    }

    @Test
    public void emptyExpenseReportShowsEmptyReceipt() {
        FakeSystemOutProvider systemOutProvider = new FakeSystemOutProvider();
        ExpensePrinter expensePrinter = new ExpensePrinter(
                () -> LocalDate.parse("2023-04-05"), new ArrayList<>(),
                systemOutProvider);

        expensePrinter.printExistingReport();

        assertThat(systemOutProvider.messages())
                .containsExactly(
                        "Expenses 2023-04-05",
                        "Meal expenses: 0",
                        "Total expenses: 0"
                        );
    }

    @Test
    public void oneBreakfastExpenseReportShowsMealExpense() {

        ExpenseDto expense = new ExpenseDto(ExpenseType.BREAKFAST, 10);
        FakeSystemOutProvider systemOutProvider = new FakeSystemOutProvider();
        ExpensePrinter expensePrinter = new ExpensePrinter(
                () -> LocalDate.parse("2023-04-05"), List.of(expense), systemOutProvider);

        expensePrinter.printExistingReport();

        assertThat(systemOutProvider.messages())
                .containsExactly(
                        "Expenses 2023-04-05",
                        "Breakfast	10	 ",
                        "Meal expenses: 10",
                        "Total expenses: 10"
                        );
    }
    @Test
    public void oneDinnerExpenseReportShowsMealExpense() {

        ExpenseDto expense = new ExpenseDto(ExpenseType.DINNER, 10);
        FakeSystemOutProvider systemOutProvider = new FakeSystemOutProvider();
        ExpensePrinter expensePrinter = new ExpensePrinter(
                () -> LocalDate.parse("2023-04-05"), List.of(expense), systemOutProvider);

        expensePrinter.printExistingReport();

        assertThat(systemOutProvider.messages())
                .containsExactly(
                        "Expenses 2023-04-05",
                        "Dinner\t10\t ",
                        "Meal expenses: 10",
                        "Total expenses: 10"
                        );
    }
    @Test
    public void oneCarRentalExpenseReportShowsMealExpense() {
        ExpenseDto expense = new ExpenseDto(ExpenseType.CAR_RENTAL, 10);
        FakeSystemOutProvider systemOutProvider = new FakeSystemOutProvider();
        ExpensePrinter expensePrinter = new ExpensePrinter(
                () -> LocalDate.parse("2023-04-05"), List.of(expense), systemOutProvider);

        expensePrinter.printExistingReport();

        assertThat(systemOutProvider.messages())
                .containsExactly(
                        "Expenses 2023-04-05",
                        "Car Rental\t10\t ",
                        "Meal expenses: 0",
                        "Total expenses: 10"
                        );
    }
    @Test
    public void oneDinnerExpenseOverMaximumReportShowsMealExpenseAndMarker() {
        ExpenseDto expense = new ExpenseDto(ExpenseType.DINNER, 5010);
        FakeSystemOutProvider systemOutProvider = new FakeSystemOutProvider();
        ExpensePrinter expensePrinter = new ExpensePrinter(
                () -> LocalDate.parse("2023-04-05"), List.of(expense), systemOutProvider);

        expensePrinter.printExistingReport();

        assertThat(systemOutProvider.messages())
                .containsExactly(
                        "Expenses 2023-04-05",
                        "Dinner\t5010\tX",
                        "Meal expenses: 5010",
                        "Total expenses: 5010"
                        );
    }
    @Test
    public void oneBreakfastExpenseOverMaximumReportShowsMealExpenseAndMarker() {
        ExpenseDto expense = new ExpenseDto(ExpenseType.BREAKFAST, 1010);
        FakeSystemOutProvider systemOutProvider = new FakeSystemOutProvider();
        ExpensePrinter expensePrinter = new ExpensePrinter(
                () -> LocalDate.parse("2023-04-05"), List.of(expense), systemOutProvider);

        expensePrinter.printExistingReport();

        assertThat(systemOutProvider.messages())
                .containsExactly(
                        "Expenses 2023-04-05",
                        "Breakfast\t1010\tX",
                        "Meal expenses: 1010",
                        "Total expenses: 1010"
                        );
    }
    @Test
    public void multipleMealsReportShowsAllExpenses() {
        ExpenseDto firstExpense = new ExpenseDto(ExpenseType.BREAKFAST, 500);
        ExpenseDto secondExpense = new ExpenseDto(ExpenseType.DINNER, 5010);
        ExpenseDto thirdExpense = new ExpenseDto(ExpenseType.CAR_RENTAL, 1010);
        FakeSystemOutProvider fakeSystemOutProvider = new FakeSystemOutProvider();
        ExpensePrinter expensePrinter = new ExpensePrinter(
                () -> LocalDate.parse("2023-04-05"),
                List.of(firstExpense, secondExpense, thirdExpense),
                fakeSystemOutProvider);

        expensePrinter.printExistingReport();

        assertThat(fakeSystemOutProvider.messages())
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
