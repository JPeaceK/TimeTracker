package Milestone1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implements Visitor. Allows us to print te whole tree of intervals, tasks and projects.
 * We print the tree starting from the leafs and finishing at the root project.
 */
public class Printer implements Visitor {

  public Logger logger = LoggerFactory.getLogger(Printer.class);

  @Override
  public void visitTask(Task task) {
    logger.debug("activity:\t" + task.getName() + "\t" + task.getInitialTime() + "\t"
            + task.getFinalTime() + "\t" + task.getTotalTime());

    logger.info("activity:\t" + task.getName() + "\t" + task.getInitialTime() + "\t"
            + task.getFinalTime() + "\t" + task.getTotalTime());
    task.getFather().acceptVisitor(this);
  }

  @Override
  public void visitProject(Project project) {
    logger.debug("activity:\t" + project.getName() + "\t" + project.getInitialTime()
            + "\t" + project.getFinalTime() + "\t" + project.getTotalTime());
    logger.info("activity:\t" + project.getName() + "\t" + project.getInitialTime()
            + "\t" + project.getFinalTime() + "\t" + project.getTotalTime());
    if (project.getFather() != null) {
      project.getFather().acceptVisitor(this);
    }
  }

  @Override
  public void visitInterval(Interval interval) {
    logger.debug("interval:\t" + interval.getInitialTime() + "\t"
            + interval.getFinalTime() + "\t" + interval.getTimeInterval());
    logger.info("interval:\t" + interval.getInitialTime() + "\t"
            + interval.getFinalTime() + "\t" + interval.getTimeInterval());
    interval.getFather().acceptVisitor(this);
  }
}
