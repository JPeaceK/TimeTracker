package Milestone1;/*  Clock class is part of the implementation of Singleton creational Pattern.
 *  The unic instance of this class will be the Timer of the whole app.
 *  The implementation of singleton is Lazy type.
 *  It's part of the Observer pattern, being the Observable object that notify tasks when
 * it receives a tick.
 */

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Clock extends Observable {
  private LocalDateTime actualTime;
  private static Clock clock;

  private static Logger logger = LoggerFactory.getLogger(Clock.class);

  private Clock() {
    super();
    this.actualTime = LocalDateTime.now();

    logger.trace("Clock constructor");
    logger.trace("Actual time: " + this.getActualTime());

    // Thread that changes Clock time constantly.
    TimerTask timerTask = new TimerTask() {
      public void run() {
        actualTime = LocalDateTime.now();
        setChanged();
        notifyObservers(this);
      }
    };

    Timer timer = new Timer();
    timer.schedule(timerTask, 0, 2000);

    /*
     * The TimerTask class with the run() method allows us to schedule an action The
     * Timer class with the schedule() method allows us to schedule the continuous
     * repetition of this action by specifying the delay and repetition period of
     * the action
     */
  }

  //Returns the clocks instance in order to follow the Singleton pattern rules
  public static Clock getInstance() { // Singleton aplication
    if (clock == null) {
      clock = new Clock();

      logger.debug("First Clock instance created at: " + clock.getActualTime());
    }
    return clock;
  }

  public LocalDateTime getActualTime() {
    return this.actualTime;
  }

}