package Milestone1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * The component class from Composite. One Project can have other projects and tasks.
 * It inherits the common methods and atributes
 * from Activity.
 */
public class Project extends Activity {

  private ArrayList<Activity> activities;
  private Logger logger = LoggerFactory.getLogger(Project.class);

  /**
   * Project constructor.
   *
   * @param name - String of the name of the project.
   * @param father - Project object of the father.
   */
  public Project(String name, Project father) {
    super(name, father);
    this.activities = new ArrayList<>();

    assert (name != null) : "NAME CAN'T BE NULL";
    assert (father != null) : "FATHER CAN'T BE NULL";

    logger.debug("Project parameter constructor");

    if (this.father != null) {
      this.father.addActivity(this);
      logger.debug("Project " + this.name + " child of "
              + this.getFather().getName());
    } else {
      logger.debug("Project " + this.name + " child of null");
    }

    logger.trace("Activities: 0");
    assert (invariant());
  }

  /**
   * Default Project constructor.
   */
  public Project() {
    super(null, null);
    this.activities = new ArrayList<>();

    logger.debug("Project default constructor");
    assert (invariant());
  }

  @Override
  public Activity getFather() {
    assert (invariant());
    return this.father;
  }

  public void setActivities(ArrayList<Activity> activities) {
    this.activities = activities;
  }

  @Override
  public void setInitialTime(LocalDateTime name) {
    assert (invariant());

    //Pre condition
    assert (this.active) : "PROJECT CAN'T SET INITIALTIME IF IT IS NOT ACTIVE";
    assert (this.started) : "PROJECT CAN'T SET INITIALTIME IF IT IS NOT STARTED";
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
  public long getTotalTime() {
    assert (invariant());
    return this.totalTime;
  }

  @Override
  public LocalDateTime getFinalTime() {
    assert (invariant());
    return this.finalTime;
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
    assert (this.started) : "CAN'T SET FINAL TIME TO A NOT STARTED PROJECT";

    this.finalTime = finalTime;
    this.totalTime = this.totalTime + seconds;
    if (this.father != null) {
      this.father.setFinalAndTotalTime(finalTime, seconds);
    }

    //PostConditions
    assert (this.finalTime == finalTime) :
            "SET_FINAL_AND_TOTAL_TIME DIDN'T WORK AS EXPECTED WITH FINALTIME";
    assert (this.totalTime != 0) :
            "SET_FINAL_AND_TOTAL_TIME DIDN'T WORK AS EXPECTED WITH TOTALTIME";

    logger.debug("Updating project time");
    logger.trace("Project total time: " + this.getTotalTime());

    assert (invariant());
  }

  @Override
  public void start() {
    assert (invariant());
    if (this.initialTime == null) {
      this.initialTime = this.clock.getActualTime();
      logger.trace("Project started for 1st time");
    }
    if (this.father != null) {
      this.father.start();
    }
    logger.debug("Project " + this.getName() + " running");
    logger.trace("Activities: " + this.getActivities().size());

    //PostConditions
    assert (this.intervals != null) : "START SHOULD CREATE AN INTERVAL";

    assert (invariant());
  }

  @Override
  public void acceptVisitor(Visitor visitor) {
    visitor.visitProject(this);
  }

  public ArrayList<Activity> getActivities() {
    return activities;
  }

  /**
   * Function that adds and activity to the project ArrayList of activities.
   *
   * @param activity - The Activity that is wanted to be added to the project.
   *
   */
  public void addActivity(Activity activity) {
    assert (invariant());
    this.getActivities().add(activity);
    assert (getActivities() != null) : "ACTIVITIES STORE ERROR";
  }

  @Override
  public void addTag(String tag) {
    assert (invariant());
    this.tags.add(tag.toLowerCase());
    logger.trace("Tag: " + tag.toLowerCase() + " added");
    logger.trace("Tags: " + this.getTags().size());
    //PostCondition
    assert (getTags() != null) : "TAGS STORE ERROR";
  }

  public ArrayList<String> getTags() {
    assert (invariant());
    return this.tags;
  }

  private boolean invariant() {
    assert (name != null) : "PROJECT MUST HAVE A NAME";
    assert (tags != null) : "PROJECT MUST HAVE A TAG";
    return true;
  }
}
