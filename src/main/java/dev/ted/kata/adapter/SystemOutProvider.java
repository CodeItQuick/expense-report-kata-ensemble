package dev.ted.kata.adapter;

// Wrapper around the outside World (In this case System.out)
public class SystemOutProvider {
    public void ServicePrint(String message) {
        System.out.println(message);
    }
}
