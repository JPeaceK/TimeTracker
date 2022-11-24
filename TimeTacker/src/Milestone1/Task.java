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
    //Pre conditions
    assert (name != null) : "NAME CAN'T BE NULL";
    assert (father != null) : "FATHER CAN'T BE NULL";


    this.active = false;
    this.started = false;
    this.intervals = new ArrayList<>();
    this.father.addActivity(this);

    logger.debug("Task parameter constructor");
    logger.trace("Task " + this.getName() + " child of " + this.getFather().getName());
    logger.trace("Intervals: 0");

    assert (invariant());
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

    assert (invariant());
  }

  @Override
  public Activity getFather() {
    assert (invariant());
    return this.father;
  }

  @Override
  public void setInitialTime(LocalDateTime name) {
    assert (invariant());

    //Pre condition
    assert (this.active) : "TASK CAN'T SET INITIALTIME IF IT IS NOT ACTIVE";
    assert (this.started) : "TASK CAN'T SET INITIALTIME IF IT IS NOT STARTED";
    this.initialTime = name;

    //Post condition
    assert (initialTime == name);
  }

  @Override
  public void setTotalTime(long totalTime) {
    this.totalTime = totalTime;

    //Post condition
    assert (this.totalTime == totalTime) : "SET_TOTAL_TIME MUST SET A VALID TIME";
  }

  @Override
  public void setFinalTime(LocalDateTime time) {
    this.finalTime = time;

    //Post condition
    assert (this.finalTime == time) : "SET_FINAL_TIME MUST SET A VALID TIME";
  }

  @Override
  public void setFather(Project father) {
    this.father = father;
  
    assert (this.father == father) : "SET_FATHER DIDN'T WORK AS EXPECTED";
  }

  @Override
  public void setName(String name) {
    this.name = name;

    assert (this.name == name) : "SET_NAME DIDN'T WORK AS EXPECTED";
  }

  @Override
  public String getName() {
    assert (invariant());
    return this.name;
  }

  @Override
  public LocalDateTime getFinalTime() {
    assert (invariant());
    return this.finalTime;
  }

  @Override
  public long getTotalTime() {
    assert (invariant());
    return this.totalTime;
  }

  @Override
  public LocalDateTime getInitialTime() {
    assert (invariant());
    return this.initialTime;
  }

  @Override
  public void setFinalAndTotalTime(LocalDateTime finalTime, long seconds) {
    assert (invariant());

    //Pre conditions
    assert (this.started) : "CAN'T SET FINAL TIME TO A NOT STARTED TASK";

    this.finalTime = finalTime;
    this.totalTime = this.totalTime + seconds;
    this.father.setFinalAndTotalTime(finalTime, seconds);

    //PostConditions
    assert (this.finalTime == finalTime) :
            "SET_FINAL_AND_TOTAL_TIME DIDN'T WORK AS EXPECTED WITH FINALTIME";
    assert (this.totalTime != 0) :
            "SET_FINAL_AND_TOTAL_TIME DIDN'T WORK AS EXPECTED WITH TOTALTIME";

    logger.debug("Updating task time");
    logger.debug("Task total time: " + this.getTotalTime());

    assert (invariant());
  }

  @Override
  public void start() {
    assert (invariant());

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

    assert (invariant());
  }

  /**
   * Function that stops the task counter.
   */
  public void stop() {
    assert (invariant());

    //PreConditions
    assert (this.active) : "CAN'T STOP A NOT ACTIVE TASK";
    assert (this.started) : "CAN'T STOP A NOT STARTED TASK";


    this.active = false;
    this.intervals.get(this.intervals.size() - 1).setActive(false);
    this.intervals.get(this.intervals.size() - 1).setFinalTime();
    clock.deleteObserver(this.intervals.get(this.intervals.size() - 1));

    logger.debug("Task " + this.getName() + " stopped");
    logger.debug("Intervals: " + this.getIntervals().size());

    //PostConditions
    assert (!this.active) : "STOP DIND'T WORK AS EXPECTED";
    assert (this.started) : "CAN'T STOP A NOT STARTED TASK";
    assert (this.finalTime != null) : "FINAL TIME ALREADY SETTED";
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
        assert (this.active == active) : "SET_ACTIVE DIDN'T WORK AS EXPECTED";
  }

  public void setIntervals(ArrayList<Interval> intervals) {
    this.intervals = intervals;
        assert (this.intervals == intervals) : "SET_INTERVALS DIDN'T WORK AS EXPECTED";
  }

  @Override
  public void addTag(String tag) {
    assert (invariant());
    this.tags.add(tag.toLowerCase());

    logger.debug("Tag: " + tag.toLowerCase() + " added");
    logger.trace("Tags: " + this.getTags().size());
    //PostCondition
    assert (getTags() != null) : "TAGS STORE ERROR";
  }

  public ArrayList<String> getTags() {
    assert (invariant());
    return this.tags;
  }

  private boolean invariant() {
    assert (father != null) : "TASK MUST HAVE A FATHER";
    assert (name != null) : "TASK MUST HAVE A NAME";
    assert (tags != null) : "TASK MUST HAVE A TAG";

    return true;
  }
}
