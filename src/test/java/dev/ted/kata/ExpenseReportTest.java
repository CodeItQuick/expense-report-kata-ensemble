package dev.ted.kata;

import com.github.larseckart.tcr.TestCommitRevertExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(TestCommitRevertExtension.class)
public class ExpenseReportTest {

    @Test
    public void emptyExpenseReportDoesNotThrowException() {
        ExpenseReport expenseReport = new ExpenseReport();

        assertThatCode(() -> expenseReport.printReport(Collections.emptyList()))
                .doesNotThrowAnyException();
    }

}
