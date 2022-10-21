import java.time.LocalDateTime;

public class Printer implements Visitor {
    private Activity origin;

    public Printer(Activity origin){
        this.origin=origin;
    }


    @Override
    public void visitTask(Task task) {
        System.out.println("Task " + task.getName() + " child of " + task.getFather().getName() + " " + task.getInitialDate() + " " + "MISSING CURRENT TIME" + " " + task.getTotalTime());
        task.getFather().acceptVisitor(this);
    }

    @Override
    public void visitProject(Project project) {
        System.out.println("Project " + project.getName() + " child of " + project.getFather().getName() + " " + project.getInitialDate() + " " + "MISSING CURRENT TIME" + " " + project.getTotalTime());
        //if(project.getFather() != null) project.getFather().acceptVisitor(this);

    }

    @Override
    public void visitInterval(Interval interval) {
        System.out.println("Interval" + " child of " + interval.getFather().getName() + " " + interval.getInitialTime() + " " + interval.getFinalTime() + " " + interval.getTimeInterval());
        interval.getFather().acceptVisitor(this);
    }
}
