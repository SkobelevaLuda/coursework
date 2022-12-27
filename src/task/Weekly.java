package task;

import java.time.LocalDateTime;

public class Weekly implements Repiatability{

    @Override
    public LocalDateTime nexTime(LocalDateTime currentDateTime) {
        return currentDateTime.plusWeeks(1);
    }
}
