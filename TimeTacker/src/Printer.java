public class Printer implements Visitor {

    @Override
    public void visitTask(Task task) {
        System.out.println("Task " + task.getName() + "     child of " + task.getFather().getName() + "     " + task.getInitialDate() + "     " + task.getTotalTime() + "     " + task.getTotalTime());
        task.getFather().acceptVisitor(this);
    }

    @Override
    public void visitProject(Project project) {

        if(project.getFather() != null){
            System.out.println("Project " + project.getName() + "     child of " + project.getFather().getName() + "     " + project.getInitialDate() + "     " + project.getTotalTime() + "     " + project.getTotalTime());
            project.getFather().acceptVisitor(this);
        }
        else{
            System.out.println("Project " + project.getName() + "    child of null     " + project.getInitialDate() + "     " + project.getTotalTime() + "     " + project.getTotalTime());
            System.out.println("\n");
        }
    }

    @Override
    public void visitInterval(Interval interval) {
        System.out.println("Interval" + " child of " + interval.getFather().getName() + "     " + interval.getInitialTime() + "     " + interval.getFinalTime() + "     " + interval.getTimeInterval());
        interval.getFather().acceptVisitor(this);
    }
}
