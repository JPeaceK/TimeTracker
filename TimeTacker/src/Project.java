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
    public long getTotalTime() {return this.totalTime;}

    @Override
    public LocalDateTime getInitialDate() {return this.initialDate;}

    @Override
    public void setFinalTime(LocalDateTime finalTime, long seconds){
        this.finalTime = finalTime;
        this.totalTime = seconds;
    }

    @Override
    public void start(){

    }

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
