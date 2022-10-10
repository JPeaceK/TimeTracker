import java.time.*;
public class Interval {
    private LocalDateTime initialDate;
    private LocalDateTime finalDate;
    private LocalDateTime timeInterval;

    public void calculateInterval(){

    }

    public void setFinalDate() {
        this.finalDate = LocalDateTime.now();
    }

    public LocalDateTime getFinalDate() { return this.finalDate;}

}
