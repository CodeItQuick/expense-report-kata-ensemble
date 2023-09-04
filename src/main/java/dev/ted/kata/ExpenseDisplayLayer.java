package dev.ted.kata;

import java.util.List;

public class ExpenseDisplayLayer {

    private ExpenseReport expenseReport;

    public ExpenseDisplayLayer(ExpenseReport expenseReport) {

        this.expenseReport = expenseReport;
    }

    public void printReportTitle(ExpenseReport.DateProvider dateProvider) {
        this.expenseReport.print("Expenses " + dateProvider.currentDate());
    }

    public void printMealExpenseTotal(int mealExpenses) {
        this.expenseReport.print("Meal expenses: " + mealExpenses);
    }

    public void printTotalExpenses(int total) {
        this.expenseReport.print("Total expenses: " + total);
    }

    public void printIndividualExpense(List<DisplayExpense> expenses) {
        for (DisplayExpense expense : expenses) {
            if (expense.isOverExpensed) {
                this.expenseReport.print(expense.type + "\t" + expense.amount + "\t" + "X");
            }
            else {
                this.expenseReport.print(expense.type + "\t" + expense.amount + "\t" + " ");
            }
        }
    }
}
