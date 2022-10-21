
import java.time.LocalDateTime;

public class Interval {
    private LocalDateTime initialTime;
    private LocalDateTime finalTime;
    private LocalDateTime timeInterval;
    private Clock clock;

    private Task father;

    public Interval(Task father){
        this.father=father;
        this.initialTime = LocalDateTime.now();
        this.finalTime = null;
        this.timeInterval = null;
        this.clock = Clock.getTime();
    }

    public Task getFather(){return this.father;}

    public LocalDateTime getInitialTime(){return this.initialTime;}

    public LocalDateTime getFinalTime(){return this.finalTime;}

    public LocalDateTime getTimeInterval(){return this.timeInterval;}
    public void setFinalTime(){
        this.finalTime = LocalDateTime.now();
    }

}
