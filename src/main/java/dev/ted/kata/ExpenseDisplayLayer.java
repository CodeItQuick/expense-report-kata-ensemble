package dev.ted.kata;

import java.util.List;

public class ExpenseDisplayLayer {

    private ExpenseReport expenseReport;

    public ExpenseDisplayLayer(ExpenseReport expenseReport) {

        this.expenseReport = expenseReport;
    }

    public void printFullReport(ExpenseReport.DateProvider dateProvider, List<DisplayExpense> displayExpenses1, int mealExpenses, int total) {
        printReportTitle(dateProvider);
        printIndividualExpense(displayExpenses1);
        printMealExpenseTotal(mealExpenses);
        printTotalExpenses(total);
    }

    public void printReportTitle(ExpenseReport.DateProvider dateProvider) {
        this.expenseReport.print("Expenses " + dateProvider.currentDate());
    }

    public void printIndividualExpense(int expenseAmount, String expenseName, boolean isOverexpensed) {
        if (isOverexpensed) {
            this.expenseReport.print(expenseName + "\t" + expenseAmount + "\t" + "X");
        }
        else {
            this.expenseReport.print(expenseName + "\t" + expenseAmount + "\t" + " ");
        }
    }

    public void printMealExpenseTotal(int mealExpenses) {
        this.expenseReport.print("Meal expenses: " + mealExpenses);
    }

    public void printTotalExpenses(int total) {
        this.expenseReport.print("Total expenses: " + total);
    }

    public void printIndividualExpense(List<DisplayExpense> expenses) {
        for (DisplayExpense expense : expenses) {
            printIndividualExpense(expense.amount, expense.type, expense.isOverExpensed);
        }
    }
}
