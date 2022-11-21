package Milestone1;/*
 *  The component class from Composite. One Project can have other projects and tasks.
 * It inherits the common methods and atributes
 * from Activity.
 */
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Project extends Activity {

  private ArrayList<Activity> activities;
  private Logger logger = LoggerFactory.getLogger(Project.class);

  public Project(String name, Project father) {
    super(name, father);
    this.activities = new ArrayList<>();

    logger.debug("Project parameter constructor");

    if (this.father != null) {
      this.father.addActivity(this);
      logger.debug("Project " + this.name + " child of "
              + this.getFather().getName());
    } else {
      logger.debug("Project " + this.name + " child of null");
    }

    logger.debug("Activities: 0");
  }

  public Project() {
    super(null, null);
    this.activities = new ArrayList<>();

    logger.debug("Project default constructor");
  }

  @Override
  public Activity getFather() {
    return this.father;
  }

  public void setActivities(ArrayList<Activity> activities) {
    this.activities = activities;
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
  public long getTotalTime() {
    return this.totalTime;
  }

  @Override
  public LocalDateTime getFinalTime() {
    return this.finalTime;
  }

  @Override
  public LocalDateTime getInitialTime() {
    return this.initialTime;
  }

  @Override
  public void setFinalAndTotalTime(LocalDateTime finalTime, long seconds) {
    this.finalTime = finalTime;
    this.totalTime = this.totalTime + seconds;
    if (this.father != null) {
      this.father.setFinalAndTotalTime(finalTime, seconds);
    }

    logger.debug("Updating project time");
    logger.debug("Project total time: " + this.getTotalTime());
  }

  @Override
  public void start() {
    if (this.initialTime == null) {
      this.initialTime = this.clock.getActualTime();
      logger.debug("Project started for 1st time");
    }
    if (this.father != null) {
      this.father.start();
    }
    logger.debug("Project " + this.getName() + " running");
    logger.debug("Activities: " + this.getActivities().size());
  }

  @Override
  public void acceptVisitor(Visitor visitor) {
    visitor.visitProject(this);
  }

  public ArrayList<Activity> getActivities() {
    return activities;
  }

  public void addActivity(Activity activity) {
    this.getActivities().add(activity);
  }

  @Override
  public void addTag(String tag){
    this.tags.add(tag.toLowerCase());

    logger.debug("Tag: " + tag.toLowerCase() + " added");
    logger.debug("Tags: " + this.getTags().size());
  }

  public ArrayList<String> getTags() {return this.tags;}

}