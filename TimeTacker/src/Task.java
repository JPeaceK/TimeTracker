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
    public long getTotalTime() {return this.totalTime;}

    @Override
    public LocalDateTime getInitialDate() {return this.initialDate;}

    @Override
    public void setFinalTime(LocalDateTime finalTime, long seconds){
        this.finalTime = finalTime;
        this.totalTime = seconds;
    }

    /*
    @Override
    public LocalDateTime calculateTime(){
        return null;
    }
     */


    @Override
    public void start(){
        this.active = true;

        if (intervals.size() == 0){
            this.started = true;
            this.initialDate = clock.getActualTime();
            this.father.start();
        }

        intervals.add(new Interval(this));
        clock.addObserver(intervals.get(intervals.size() -1));
    }

    public void stopTime(){
        int tamany = intervals.size();
        intervals.get(tamany -1).setFinalTime();
    }

    @Override
    public void acceptVisitor(Visitor visitor){
        visitor.visitTask(this);
    }


}
