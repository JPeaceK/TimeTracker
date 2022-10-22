
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;

public class Interval implements Observer{
    private LocalDateTime initialTime;
    private LocalDateTime finalTime;
    private long timeInterval;
    private Clock clock;

    private Task father;

    private Printer printer;

    public Interval(Task father){
        this.father=father;
        this.initialTime = LocalDateTime.now();
        this.finalTime = null;
        this.timeInterval = 0;
        this.clock = Clock.getInstance();
        this.printer = new Printer();
    }

    public Task getFather(){return this.father;}

    public LocalDateTime getInitialTime(){return this.initialTime;}

    public LocalDateTime getFinalTime(){return this.finalTime;}

    public long getTimeInterval(){return this.timeInterval;}
    public void setFinalTime(){
        this.finalTime = LocalDateTime.now();
    }

    public void acceptVisitor(Visitor visitor){
        visitor.visitInterval(this);
    }

    @Override
    public void update(Observable observable, Object arg) {
        this.timeInterval = Duration.between(this.initialTime.toLocalTime(), clock.getActualTime().toLocalTime()).getSeconds();

        long timeIncremented = 0;
        if (this.finalTime == null){
            timeIncremented = this.timeInterval;
        }else{
            timeIncremented = Duration.between(this.finalTime.toLocalTime(), clock.getActualTime().toLocalTime()).getSeconds();
        }

        this.finalTime = this.initialTime.plusSeconds(timeInterval);
        this.father.setFinalAndTotalTime(this.finalTime, timeIncremented);
        acceptVisitor(printer);
    }
}
