package dev.ted.kata;

import java.util.ArrayList;
import java.util.List;

public class ExpenseDisplayLayer {

    public ExpenseDisplayLayer() {

    }

    public String printIndividualExpenses(DisplayExpense expense) {
        return expense.type + "\t" + expense.amount + "\t" + expense.isOverExpensed;
    }

    public String printReportTitle(ExpenseReport.DateProvider dateProvider) {
        return "Expenses " + dateProvider.currentDate();
    }

    public String printMealExpenseTotal(int mealExpenses) {
        return "Meal expenses: " + mealExpenses;
    }

    public String printTotalExpenses(int total) {
        return "Total expenses: " + total;
    }

}
