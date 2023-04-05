package dev.ted.kata;

import com.github.larseckart.tcr.TestCommitRevertMainExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(TestCommitRevertMainExtension.class)
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

    private class TestableExpenseReport extends ExpenseReport {
        private final List<String> message = new ArrayList<>();

        private TestableExpenseReport(DateProvider dateProvider) {
            super(dateProvider);
        }

        @Override
        protected void print(String message) {
            this.message.add(message);
            super.print(message);
        }

        public List<String> report() {
            return message;
        }
    }
}
