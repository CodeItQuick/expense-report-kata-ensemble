package dev.ted.kata;

import dev.ted.kata.adapter.ExpensePrinter;
import dev.ted.kata.domain.Expense;
import dev.ted.kata.domain.ExpenseType;

import java.util.List;


public class ConsoleRun {
    public static void main(String[] args){
        ExpensePrinter expenseReport = ExpensePrinter.create();
        Expense firstExpense = new Expense(ExpenseType.BREAKFAST, 500);
        Expense secondExpense = new Expense(ExpenseType.DINNER, 5010);
        Expense thirdExpense = new Expense(ExpenseType.CAR_RENTAL, 1010);

        List<Expense> expenses = List.of(firstExpense, secondExpense, thirdExpense);

        expenseReport.printReport(expenses);

    }
}
