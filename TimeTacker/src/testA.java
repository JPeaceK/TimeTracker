public class testA implements Visitor {

    @Override
    public void visitTask(Task task) {
        System.out.println("Task: " + task.getName() + " child of " + task.getFather().getName() + " initiated at "
                + task.getInitialTime());
        task.getFather().acceptVisitor(this);
    }

    @Override
    public void visitProject(Project project) {
        if (project.getFather() != null) {
            System.out.println("Project: " + project.getName() + " child of " + project.getFather().getName()
                    + " initiated at " + project.getInitialTime());
            project.getFather().acceptVisitor(this);
        } else {
            System.out.println(
                    "Project: " + project.getName() + " child of null initiated at " + project.getInitialTime());
        }
    }

    @Override
    public void visitInterval(Interval interval) {
    }
}
