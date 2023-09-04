package dev.ted.kata;

public class ExpenseView {

    public ExpenseView() {

    }

    public String individualExpenses(DisplayExpense expense) {
        return expense.type + "\t" + expense.amount + "\t" + expense.isOverExpensed;
    }

    public String reportTitle(ExpenseReport.DateProvider dateProvider) {
        return "Expenses " + dateProvider.currentDate();
    }

    public String mealExpenseTotal(int mealExpenses) {
        return "Meal expenses: " + mealExpenses;
    }

    public String totalExpenses(int total) {
        return "Total expenses: " + total;
    }

}
