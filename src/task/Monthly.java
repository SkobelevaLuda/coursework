package task;

import java.time.LocalDateTime;

public class Monthly implements Repiatability{

    @Override
    public LocalDateTime nexTime(LocalDateTime currentDateTime) {
        return currentDateTime.plusMonths(1);
    }

    @Override
    public String titel() {
        return "ежемесячная";
    }
}
