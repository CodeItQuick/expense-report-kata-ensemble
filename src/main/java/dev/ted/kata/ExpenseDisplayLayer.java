package dev.ted.kata;

import java.util.List;

public class ExpenseDisplayLayer {

    protected ExpenseReport.DateProvider dateProvider;

    public void printReportTitle(ExpenseReport expenseReport, ExpenseReport.DateProvider dateProvider) {
        expenseReport.print("Expenses " + dateProvider.currentDate());
    }

    public void printIndividualExpense(ExpenseReport expenseReport, int expenseAmount, String expenseName, boolean isOverexpensed) {
        if (isOverexpensed) {
            expenseReport.print(expenseName + "\t" + expenseAmount + "\t" + "X");
        }
        else {
            expenseReport.print(expenseName + "\t" + expenseAmount + "\t" + " ");
        }
    }

    public void printMealExpenseTotal(int mealExpenses, ExpenseReport expenseReport) {
        expenseReport.print("Meal expenses: " + mealExpenses);
    }

    public void printTotalExpenses(int total, ExpenseReport expenseReport) {
        expenseReport.print("Total expenses: " + total);
    }

    public void printIndividualExpense(ExpenseReport expenseReport, List<DisplayExpense> expenses) {
        for (DisplayExpense expense : expenses) {
            printIndividualExpense(expenseReport, expense.amount, expense.type, expense.isOverExpensed);
        }
    }
}
