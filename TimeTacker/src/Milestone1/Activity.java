package Milestone1;

import Milestone3.IdCreator;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  Activity class is designed to implement the Composite Pattern.
 * Tasks and Projects are Activities.
 */
public abstract class Activity {
  protected String name;
  protected LocalDateTime initialTime;
  protected long totalTime;

  protected int id;

  protected Clock clock;
  protected LocalDateTime finalTime;

  // Father will be always a Project instance. If project is Root, father must be NULL.
  protected Project father;

  protected ArrayList<String> tags;

  private Logger logger = LoggerFactory.getLogger(Activity.class);

  /**
   * Activity Constructor.
   *
   * @param name - String variable of the name of the Activity.
   * @param father - Project object of the father of the Activity to be created.
   */
  public Activity(String name, Project father) {
    this.clock = Clock.getInstance();
    this.name = name;
    this.father = father;
    this.id = IdCreator.getId();
    this.totalTime = 0;
    this.initialTime = null;
    this.tags = new ArrayList<>();

    logger.debug("Activity " + this.getName() + " created");
    logger.info("Activity " + this.getName() + " created");
    logger.trace("Tags size: 0");
  }

  public abstract Activity getFather();

  public abstract void setName(String name);

  public abstract void setInitialTime(LocalDateTime time);

  public abstract void setTotalTime(long totalTime);

  public abstract void setFinalTime(LocalDateTime time);

  public abstract void setFather(Project father);

  public abstract void setId(int id);

  public abstract String getName();

  public abstract int getId();

  public abstract long getTotalTime();

  public abstract LocalDateTime getFinalTime();

  public abstract LocalDateTime getInitialTime();

  public abstract void acceptVisitor(Visitor visitor);

  public abstract void setFinalAndTotalTime(LocalDateTime time, long seconds);

  public abstract void addTag(String tag);

  public abstract void start();

  public abstract JSONObject toJson(int id);

}