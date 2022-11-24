package Milestone2;

import Milestone1.Activity;
import Milestone1.Interval;
import Milestone1.Project;
import Milestone1.Task;
import Milestone1.Visitor;
import java.util.ArrayList;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Function that searches a project or a task with a specific tag.
 */
public class SearchByTag implements Visitor {
  private ArrayList<Activity> activitiesWithTag;
  private String tag;

  public Logger logger = LoggerFactory.getLogger(SearchByTag.class);

  /**
   * Constructor of SearchByTag.
   *
   * @param tag - A String variable that is the tag to be found in an Activity.
   *
   */
  public SearchByTag(String tag) {
    this.activitiesWithTag = new ArrayList<>();
    this.tag = tag.toLowerCase();

    logger.debug("SearchByTag parameter constructor");
  }

  /**
   * Returns an ArrayList of the Activities that matched with the tag.
   *
   * @param activity - An Activity Object.
   * @return    ArrayList of all Activity objects with the tag.
   */
  public ArrayList<Activity> search(Activity activity) {
    activity.acceptVisitor(this);
    logger.debug("Searching tag: " + this.tag.toLowerCase());
    return getActivitiesWithTag();
  }

  @Override
  public void visitTask(Task task) {
    for (int i = 0; i < task.getTags().size(); i++) {
      if (Objects.equals(task.getTags().get(i), this.tag)) {
        this.activitiesWithTag.add(task);
      }
    }

    logger.debug("Searching " + task.getName() + " tags");
  }

  @Override
  public void visitProject(Project project) {
    if (project.getActivities().size() != 0) {
      for (int i = 0; i < project.getActivities().size(); i++) {
        project.getActivities().get(i).acceptVisitor(this);
      }
    }

    for (int i = 0; i < project.getTags().size(); i++) {
      if (Objects.equals(project.getTags().get(i), this.tag)) {
        this.activitiesWithTag.add(project);
      }
    }

    logger.debug("Searching " + project.getName() + " tags");
  }

  @Override
  public void visitInterval(Interval interval) { }

  public ArrayList<Activity> getActivitiesWithTag() {
    return this.activitiesWithTag;
  }

}
