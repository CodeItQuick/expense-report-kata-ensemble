package dev.ted.kata;

import dev.ted.kata.adapter.SystemOutProvider;

import java.util.ArrayList;
import java.util.List;

public class FakeSystemOutProvider extends SystemOutProvider {
    private List<String> messages = new ArrayList<>();
    @Override
    public void ServicePrint(String message) {
        super.ServicePrint(message);
        this.messages.add(message);
    }
    public List<String> messages() {
        return messages;
    }
}
