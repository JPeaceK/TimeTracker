package Milestone1;

import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Observable;
import java.util.Observer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  Interval class is responsible to get the information about total time of activities.
 *  When you start a task for the first time, it creates an interval with initial time of the task.
 *  When you stop a task, the interval terminates with a final date.
 *  When you start again a class, it creates an interval with a new initial time.
 */
public class Interval implements Observer {
  private LocalDateTime initialTime;
  private LocalDateTime finalTime;
  private long timeInterval;
  private Clock clock;

  private Logger logger = LoggerFactory.getLogger(Interval.class);

  private Task father;

  private boolean active;

  /**
   * Interval constructor with Father parameter.
   *
   * @param father - You assign the interval father.
   *
   */
  public Interval(Task father) {
    this.father = father;
    this.initialTime = Clock.getInstance().getActualTime();
    this.finalTime = this.initialTime;
    this.timeInterval = 0;
    this.clock = Clock.getInstance();
    this.active = true;

    logger.debug("Interval parameter constructor");
    logger.debug("Interval of " + this.getFather().getName() +
            " created at: " + this.getInitialTime());

  }

  /**
   * Default constructor of interval.
   */
  public Interval() {
    this.father = null;
    this.initialTime = null;
    this.finalTime = null;
    this.timeInterval = 0;
    this.clock = null;
    this.active = false;

    logger.debug("Interval default constructor");
  }

  public void setInitialTime(LocalDateTime name) {
    this.initialTime = name;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public void setTotalTime(long totalTime) {
    this.timeInterval = totalTime;
  }

  //Used to load the final time from json
  public void setFinalTime(LocalDateTime time) {
    this.finalTime = time;
  }

  public void setFinalTime() {
    this.finalTime = clock.getActualTime();
  }

  public void setFather(Task father) {
    this.father = father;
  }

  public LocalDateTime getInitialTime() {
    return this.initialTime;
  }

  public Task getFather() {
    return this.father;
  }

  public LocalDateTime getFinalTime() {
    return this.finalTime;
  }

  public long getTimeInterval() {
    return this.timeInterval;
  }

  public boolean getActive() {
    return this.active;
  }

  public void acceptVisitor(Visitor visitor) {
    visitor.visitInterval(this);
  }

  /**
   * Function that it is called to update the time of the interval.
   */
  public void updateTime() {
    LocalTime time = clock.getActualTime().toLocalTime();
    this.timeInterval = Duration.between(this.initialTime.toLocalTime(), time).getSeconds();
    long timeIncremented = Duration.between(this.finalTime.toLocalTime(), time).getSeconds();
    this.father.setFinalAndTotalTime(this.finalTime, timeIncremented);
    this.finalTime = this.initialTime.plusSeconds(this.timeInterval);

    logger.debug("Updating inteval time");
    logger.debug("Interval total time: " + this.getTimeInterval());
  }

  @Override
  public void update(Observable observable, Object arg) {
    this.updateTime();
    acceptVisitor(new Printer());
  }
}