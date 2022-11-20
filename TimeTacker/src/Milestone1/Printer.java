package Milestone1;

/**
 * Implements Visitor. Allows us to print te whole tree of intervals, tasks and projects.
 * We print the tree starting from the leafs and finishing at the root project.
 */
public class Printer implements Visitor {
  @Override
  public void visitTask(Task task) {
    System.out.println("activity:\t" + task.getName() + "\t" + task.getInitialTime() + "\t"
            + task.getFinalTime() + "\t" + task.getTotalTime());
    task.getFather().acceptVisitor(this);
  }

  @Override
  public void visitProject(Project project) {
    System.out.println("activity:\t" + project.getName() + "\t" + project.getInitialTime()
            + "\t" + project.getFinalTime() + "\t" + project.getTotalTime());
    if (project.getFather() != null) {
      project.getFather().acceptVisitor(this);
    }
  }

  @Override
  public void visitInterval(Interval interval) {
    System.out.println("interval:\t" + interval.getInitialTime() + "\t"
            + interval.getFinalTime() + "\t" + interval.getTimeInterval());
    interval.getFather().acceptVisitor(this);
  }
}
