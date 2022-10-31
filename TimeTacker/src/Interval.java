
import org.json.JSONObject;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Observable;
import java.util.Observer;

public class Interval implements Observer{
    private LocalDateTime initialTime;
    private LocalDateTime finalTime;
    private long timeInterval;
    private Clock clock;

    private Task father;

    private boolean active;


    public Interval(Task father){
        this.father=father;
        this.initialTime = Clock.getInstance().getActualTime();
        this.finalTime = this.initialTime;
        this.timeInterval = 0;
        this.clock = Clock.getInstance();
        this.active = true;
    }

    public Task getFather(){return this.father;}

    public LocalDateTime getInitialTime(){return this.initialTime;}

    public LocalDateTime getFinalTime(){return this.finalTime;}

    public long getTimeInterval(){return this.timeInterval;}

    public void acceptVisitor(Visitor visitor){
        visitor.visitInterval(this);
    }

    public void updateTime(){
        LocalTime time = clock.getActualTime().toLocalTime();
        this.timeInterval = Duration.between(this.initialTime.toLocalTime(), time).getSeconds();
        long timeIncremented = Duration.between(this.finalTime.toLocalTime(), time).getSeconds();
        this.father.setFinalAndTotalTime(this.finalTime, timeIncremented);
        this.finalTime = this.initialTime.plusSeconds(this.timeInterval);
    }

    @Override
    public void update(Observable observable, Object arg) {
        this.updateTime();
        acceptVisitor(new Printer());
    }

    public void setActive(boolean active) { this.active = active; }

    public boolean getActive(){return this.active;}

    public void setFinalTime(){ this.finalTime = clock.getActualTime(); }

}
