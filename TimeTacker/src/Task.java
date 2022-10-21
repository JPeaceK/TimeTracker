import java.time.LocalDateTime;
import java.util.ArrayList;

public class Task extends Activity {
    private ArrayList<Interval> intervals;
    private boolean executant;
    private boolean started;

    public Task(String name, Activity father){
        super(name, father);
        this.intervals = new ArrayList<>();
    }

    @Override
    public Activity getFather() {return this.father;}

    @Override
    public String getName() {return this.name;}

    @Override
    public LocalDateTime getTotalTime() {return this.totalTime;}

    @Override
    public LocalDateTime getInitialDate() {return this.initialDate;}

    /*
    @Override
    public LocalDateTime calculateTime(){
        return null;
    }
     */


    public void start(){
        intervals.add(new Interval(this));
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
