import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Task extends Activity {
    private ArrayList<Interval> intervals;
    private boolean active;
    private boolean started;

    public Task(String name, Project father) {
        super(name, father);
        this.active = false;
        this.started = false;
        this.intervals = new ArrayList<>();
        this.father.addActivity(this);
    }

    public Task() {
        super(null, null);
        this.active = false;
        this.started = false;
        this.intervals = new ArrayList<>();
        // this.father.addActivity(this);
    }

    @Override
    public Activity getFather() {
        return this.father;
    }

    @Override
    public void setInitialTime(LocalDateTime name) {
        this.initialTime = name;
    }

    @Override
    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    @Override
    public void setFinalTime(LocalDateTime time) {
        this.finalTime = time;
    }

    @Override
    public void setFather(Project father) {
        this.father = father;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public LocalDateTime getFinalTime() {
        return this.finalTime;
    }

    @Override
    public long getTotalTime() {
        return this.totalTime;
    }

    @Override
    public LocalDateTime getInitialTime() {
        return this.initialTime;
    }

    @Override
    public void setFinalAndTotalTime(LocalDateTime finalTime, long seconds) {
        this.finalTime = finalTime;
        this.totalTime = this.totalTime + seconds;
        this.father.setFinalAndTotalTime(finalTime, seconds);
    }

    @Override
    public void start() {
        if (this.initialTime == null) {
            this.initialTime = this.clock.getActualTime();
        }
        this.active = true;
        this.started = true;
        this.father.start();
        intervals.add(new Interval(this));
        clock.addObserver(intervals.get(intervals.size() - 1));
    }

    public void stop() {
        this.active = false;
        // this.intervals.get(this.intervals.size() -1).updateTime();
        this.intervals.get(this.intervals.size() - 1).setActive(false);
        this.intervals.get(this.intervals.size() - 1).setFinalTime();
        clock.deleteObserver(this.intervals.get(this.intervals.size() - 1));
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitTask(this);
    }

    public boolean getActive() {
        return this.active;
    }

    public ArrayList<Interval> getIntervals() {
        return this.intervals;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setIntervals(ArrayList<Interval> intervals) {
        this.intervals = intervals;
    }
}
