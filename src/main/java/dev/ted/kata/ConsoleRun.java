package dev.ted.kata;

import dev.ted.kata.adapter.ExpensePrinter;

public class ConsoleRun {
    public static void main(String[] args){
        ExpensePrinter expenseReport = ExpensePrinter.create();
        expenseReport.printExistingReport();
    }
}
