package dev.ted.kata.service;

import dev.ted.kata.service.testableProviderInterfaces.DateProvider;

import java.time.LocalDate;

public class RealDateProvider implements DateProvider {
        public RealDateProvider() {
        }

        @Override
        public LocalDate currentDate() {
            return LocalDate.now();
        }
}

