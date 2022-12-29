package task;

import java.time.LocalDateTime;

public class Annually implements Repiatability {

    @Override
    public LocalDateTime nexTime(LocalDateTime currentDateTime) {
        return currentDateTime.plusYears(1);
    }

    @Override
    public String titel() {
        return "ежегодная";
    }
}
