package Milestone1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * The leaf class from Composite. One Task is compound of Intervals.
 * It inherits the common methods and attributes from Activity.
 */

public class Task extends Activity {
  private ArrayList<Interval> intervals;
  private boolean active;
  private boolean started;

  private Logger logger = LoggerFactory.getLogger(Task.class);


  /**
   * Constructor with two parameters: String name and Project father.
   */
  public Task(String name, Project father) {
    super(name, father);
    this.active = false;
    this.started = false;
    this.intervals = new ArrayList<>();
    this.father.addActivity(this);

    logger.debug("Task parameter constructor");
    logger.trace("Task " + this.getName() + " child of " + this.getFather().getName());
    logger.trace("Intervals: 0");
  }

  /**
   * Default constructor for Task.
   */
  public Task() {
    super(null, null);
    this.active = false;
    this.started = false;
    this.intervals = new ArrayList<>();

    logger.debug("Task default constructor");
  }

  @Override
  public Activity getFather() {
    return this.father;
  }

  @Override
  public void setInitialTime(LocalDateTime name) {
    assert (invariant()) : "TASK INVARIANT ERROR";
    //PreCondition
    assert (this.active) : "TASK CAN'T SET INITIALTIME IF IT IS NOT ACTIVE";
    assert (this.started) : "TASK CAN'T SET INITIALTIME IF IT IS NOT STARTED";
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
    assert (invariant()) : "TASK INVARIANT ERROR";

    //PreConditions
    assert (!this.active) : "CAN'TSET FINAL TIME TO AN ACTIVE TASK";
    assert (this.started) : "CANT SET FINAL TIME TO A NOT STARTED TASK";

    this.finalTime = finalTime;
    this.totalTime = this.totalTime + seconds;
    this.father.setFinalAndTotalTime(finalTime, seconds);

    //PostConditions
    assert (getTotalTime() > 0.0) : "TOTAL TIME MUST BE > 0";
    assert (this.intervals != null) : "SET FINAL AND TOTAL TIME SHOULD CREATE AN INTERVAL";
    logger.debug("Updating task time");
    logger.debug("Task total time: " + this.getTotalTime());
  }

  @Override
  public void start() {
    assert (invariant()) : "TASK INVARIANT ERROR";
    this.active = true;
    this.started = true;

    if (this.initialTime == null) {
      this.initialTime = this.clock.getActualTime();
      logger.debug("Task started for 1st time");
    }

    this.father.start();
    intervals.add(new Interval(this));
    clock.addObserver(intervals.get(intervals.size() - 1));

    logger.debug("Task " + this.getName() + " running");
    logger.debug("Intervals: " + this.getIntervals().size());

    //PostConditions
    assert (this.intervals != null) : "START SHOULD CREATE AN INTERVAL";
    assert (getInitialTime() != null) : "START SHOULD SET AN INITIAL TIME";
    assert (getFinalTime() == null) : "START DON'T SET ANY FINAL TIME";
  }

  /**
   * Function that stops the task counter.
   */
  public void stop() {
    assert (invariant()) : "TASK INVARIANT ERROR";
    //PreConditions
    assert (this.active) : "CAN'T STOP A NOT ACTIVE TASK";
    assert (this.started) : "CAN'T STOP A NOT STARTED TASK";
    assert (this.intervals != null) : "INTERVALS CAN'T BE NULL TO STOP";
    assert (getFinalTime() == null) : "FINAL TIME ALREADY SETTED";

    this.active = false;
    this.intervals.get(this.intervals.size() - 1).setActive(false);
    this.intervals.get(this.intervals.size() - 1).setFinalTime();
    clock.deleteObserver(this.intervals.get(this.intervals.size() - 1));

    logger.debug("Task " + this.getName() + " stopped");
    logger.debug("Intervals: " + this.getIntervals().size());

    //PostConditions
    assert (!this.active) : "STOP SHOULL SET TASK TO NOT ACTIVE";
    assert (getFinalTime() != null) : "STOP FINALTIME SET ERROR";
    assert (getTotalTime() > 0.0) : "STOP TOTALTIME SET ERROR";
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
  public void addTag(String tag) {
    assert (invariant()) : "TASK INVARIANT ERROR";
    this.tags.add(tag.toLowerCase());

    logger.debug("Tag: " + tag.toLowerCase() + " added");
    logger.trace("Tags: " + this.getTags().size());
    //PostCondition
    assert (getTags() != null) : "TAGS STORE ERROR";
  }

  public ArrayList<String> getTags() {
    return this.tags;
  }

  private boolean invariant() {
    Activity father = getFather(); //All tasks have a parent.
    return (father != null);
  }
}
