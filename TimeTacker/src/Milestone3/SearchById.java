package Milestone3;

import Milestone1.*;

public class SearchById implements Visitor{

  private int id;
  private Activity activity;

  public SearchById(int id){
    this.id = id;
    this.activity = null;
  }

  public Activity search(Activity activity){
    if (activity != null){
      activity.acceptVisitor(this);
    }
    return this.activity;
  }

  @Override
  public void visitTask(Task task) {
    if (task.getId() == this.id){
      this.activity = task;
    }
  }

  @Override
  public void visitProject(Project project) {
    if (project.getId() == this.id){
      this.activity = project;
    } else {
      for (Activity act : project.getActivities()){
        act.acceptVisitor(this);
      }
    }
  }

  @Override
  public void visitInterval(Interval interval) {}
}
