import java.time.LocalDateTime;
import java.util.ArrayList;

public class Project extends Activity {

    private ArrayList<Activity> activities;

    public Project(String name, Activity father){
        super(name, father);
        this.activities = new ArrayList<>();
    }

    @Override
    public Activity getFather() {return this.father;}

    @Override
    public String getName() {return this.name;}

    @Override
    public LocalDateTime getTotalTime() {return this.totalTime;}

    @Override
    public LocalDateTime getInitialDate() {return this.initialDate;}

    /*
    @Override
    public LocalDateTime calculateTime(){
        return null;
    }
     */

    @Override
    public void acceptVisitor(Visitor visitor){
        visitor.visitProject(this);
    }

}
