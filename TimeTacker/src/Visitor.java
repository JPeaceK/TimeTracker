/**
 * Visitor method implementation.
 * It is used to print the tree of projects and tasks.
 */

public interface Visitor {
  void visitTask(Task task);

  void visitProject(Project project);

  void visitInterval(Interval interval);
}
