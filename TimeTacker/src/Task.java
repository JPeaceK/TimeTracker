import java.time.LocalDateTime;
import java.util.ArrayList;

public class Task extends Activity {
    private ArrayList<Interval> intervals;
    private boolean active;
    private boolean started;

    public Task(String name, Activity father){
        super(name, father);
        this.active=false;
        this.started=false;
        this.intervals = new ArrayList<>();
    }

    @Override
    public Activity getFather() {return this.father;}

    @Override
    public String getName() {return this.name;}

    @Override
    public LocalDateTime getFinalTime() {return this.finalTime;}

    @Override
    public long getTotalTime() {return this.totalTime;}

    @Override
    public LocalDateTime getInitialDate() {return this.initialTime;}

    @Override
    public void setFinalAndTotalTime(LocalDateTime finalTime, long seconds){
        this.finalTime = finalTime;
        this.totalTime = this.totalTime+seconds;
        this.father.setFinalAndTotalTime(finalTime, seconds);
    }

    @Override
    public void start(){
        if(this.initialTime == null) {
            this.initialTime = this.clock.getActualTime();
        }
        this.active = true;
        this.started = true;
        this.father.start();
        intervals.add(new Interval(this));
        clock.addObserver(intervals.get(intervals.size() -1));
    }

    public void stop(){
        int tamany = intervals.size();
        intervals.get(tamany -1).setFinalTime();
    }

    @Override
    public void acceptVisitor(Visitor visitor){
        visitor.visitTask(this);
    }


}
