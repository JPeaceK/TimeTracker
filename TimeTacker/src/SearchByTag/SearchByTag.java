package SearchByTag;

import Milestone1.Interval;
import Milestone1.Project;
import Milestone1.Task;
import Milestone1.Visitor;
import Milestone1.Activity;

import java.util.ArrayList;
import java.util.Objects;


public class SearchByTag implements Visitor{

  private ArrayList<Activity> activitiesWithTag;
  private String tag;

  public SearchByTag(String tag){
    this.activitiesWithTag = new ArrayList<>();
    this.tag = tag.toLowerCase();
  }

  public ArrayList<Activity> search(Activity activity){
    activity.acceptVisitor(this);
    return getActivitiesWithTag();
  }

  @Override
  public void visitTask(Task task) {
    for (int i = 0; i<task.getTags().size(); i++){
      if (Objects.equals(task.getTags().get(i), this.tag)){
        this.activitiesWithTag.add(task);
      }
    }
  }

  @Override
  public void visitProject(Project project) {
    if (project.getActivities().size() != 0) {
      for (int i = 0; i<project.getActivities().size(); i++){
        project.getActivities().get(i).acceptVisitor(this);
      }
    }

    for (int i = 0; i<project.getTags().size(); i++) {
      if (Objects.equals(project.getTags().get(i), this.tag)) {
        this.activitiesWithTag.add(project);
      }
    }
  }

  @Override
  public void visitInterval(Interval interval) {}

  public ArrayList<Activity> getActivitiesWithTag(){ return this.activitiesWithTag; }

}
