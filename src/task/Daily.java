package task;

import java.time.LocalDateTime;

public class Daily implements Repiatability {

    @Override
    public LocalDateTime nexTime(LocalDateTime currentDateTime) {
        return currentDateTime.plusDays(1);
    }

    @Override
    public String titel() {
        return " ежедневная";
    }
}
