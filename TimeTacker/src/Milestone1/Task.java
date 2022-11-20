package Milestone1;

import java.time.LocalDateTime;
import java.util.ArrayList;
/**
 * The leaf class from Composite. One Task is compound of Intervals.
 * It inherits the common methods and attributes from Activity.
 */

public class Task extends Activity {
  private ArrayList<Interval> intervals;
  private boolean active;
  private boolean started;


  /**
   * Constructor with two parameters: String name and Project father.
   */
  public Task(String name, Project father) {
    super(name, father);
    this.active = false;
    this.started = false;
    this.intervals = new ArrayList<>();
    this.father.addActivity(this);
  }

  /**
   * Default constructor for Task.
   */
  public Task() {
    super(null, null);
    this.active = false;
    this.started = false;
    this.intervals = new ArrayList<>();
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

  /**
   * Function that stops the task counter.
   */
  public void stop() {
    this.active = false;
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

  @Override
  public void addTag(String tag){
    this.tags.add(tag.toLowerCase());
  }

  public ArrayList<String> getTags() {return this.tags;}
}