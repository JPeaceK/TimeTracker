import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Task extends Activity {
    private ArrayList<Interval> intervals;
    private boolean active;
    private boolean started;

    public Task(String name, Project father){
        super(name, father);
        this.active=false;
        this.started=false;
        this.intervals = new ArrayList<>();
        this.father.addActivity(this);
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
    public LocalDateTime getInitialTime() {return this.initialTime;}

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
        this.active = false;
        //this.intervals.get(this.intervals.size() -1).updateTime();
        this.intervals.get(this.intervals.size() -1).setActive(false);
        clock.deleteObserver(this.intervals.get(this.intervals.size() -1));
    }

    @Override
    public void acceptVisitor(Visitor visitor){
        visitor.visitTask(this);
    }

    public boolean getActive(){return this.active;}


    public JSONObject getJSON(){
        JSONObject json = new JSONObject();
        json.put("type", "task");
        json.put("name", this.getName());
        json.put("duration", this.getTotalTime());
        if (this.getInitialTime() != null) json.put("initialDate", this.getInitialTime());
        else json.put("finalDate", JSONObject.NULL);
        if (this.getFinalTime() != null) json.put("initialDate", this.getFinalTime());
        else json.put("finalDate", JSONObject.NULL);
        json.put("active", this.getActive());

        return json;
    }


    public ArrayList<Interval> getIntervals(){ return this.intervals; }
}
