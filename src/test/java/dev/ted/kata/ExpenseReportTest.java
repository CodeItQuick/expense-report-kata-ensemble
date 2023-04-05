package dev.ted.kata;

import com.github.larseckart.tcr.TestCommitRevertExtension;
import com.github.larseckart.tcr.TestCommitRevertMainExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(TestCommitRevertMainExtension.class)
public class ExpenseReportTest {

    @Test
    public void emptyExpenseReportDoesNotThrowException() {
        ExpenseReport expenseReport = new TestableExpenseReport();

        assertThatCode(() -> expenseReport.printReport(Collections.emptyList()))
                .doesNotThrowAnyException();
    }

    @Test
    public void emptyExpenseReportShowsEmptyReceipt() {
        TestableExpenseReport expenseReport = new TestableExpenseReport();

        expenseReport.printReport(Collections.emptyList());

        assertThat(expenseReport.report())
                .isEqualTo("");
    }

    private class TestableExpenseReport extends ExpenseReport {
        private String message;

        @Override
        protected void print(String message) {
            this.message = message;
            super.print(message);
        }

        public String report() {
            return "";
        }
    }
}
