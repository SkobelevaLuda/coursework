package task;

import java.time.LocalDateTime;

public interface Repiatability {
    LocalDateTime nexTime (LocalDateTime currentDateTime);
    String titel();

}
