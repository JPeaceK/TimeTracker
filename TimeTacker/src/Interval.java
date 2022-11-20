/*  Interval class is responsible to get the information about total time of activities.
 *  When you start a task for the first time, it creates an interval that has the
 * initial time of the task. When you stop a task, the interval terminates with a final date.
 *  When you start again a class, it creates an interval with a new initial time.
 */

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Observable;
import java.util.Observer;
import org.json.JSONObject;

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

  private Task father;

  private boolean active;

  public Interval(Task father) {
    this.father = father;
    this.initialTime = Clock.getInstance().getActualTime();
    this.finalTime = this.initialTime;
    this.timeInterval = 0;
    this.clock = Clock.getInstance();
    this.active = true;
  }

  public Interval() {
    this.father = null;
    this.initialTime = null;
    this.finalTime = null;
    this.timeInterval = 0;
    this.clock = null;
    this.active = false;
  }

  public Task getFather() {
    return this.father;
  }

  public void setInitialTime(LocalDateTime name) {
    this.initialTime = name;
  }

  public void setFinalTime(LocalDateTime time) {
    this.finalTime = time;
  }

  public void setTotalTime(long totalTime) {
    this.timeInterval = totalTime;
  }

  public void setFather(Task father) {
    this.father = father;
  }

  public void acceptVisitor(Visitor visitor) {
    visitor.visitInterval(this);
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public LocalDateTime getInitialTime() {
    return this.initialTime;
  }

  public LocalDateTime getFinalTime() {
    return this.finalTime;
  }

  public long getTimeInterval() {
    return this.timeInterval;
  }

  public void setFinalTime() {
    this.finalTime = clock.getActualTime();
  }

  public void updateTime() {
    LocalTime time = clock.getActualTime().toLocalTime();
    this.timeInterval = Duration.between(this.initialTime.toLocalTime(), time).getSeconds();
    long timeIncremented = Duration.between(this.finalTime.toLocalTime(), time).getSeconds();
    this.father.setFinalAndTotalTime(this.finalTime, timeIncremented);
    this.finalTime = this.initialTime.plusSeconds(this.timeInterval);
  }



  public boolean getActive() {
    return this.active;
  }

  @Override
  public void update(Observable observable, Object arg) {
    this.updateTime();
    acceptVisitor(new Printer());
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
  }
  @Override
  public void update(Observable observable, Object arg) {
    this.updateTime();
    acceptVisitor(new Printer());
  }
}