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
    public LocalDateTime getFinalTime() {return this.finalTime;}

    @Override
    public LocalDateTime getInitialDate() {return this.initialTime;}

    @Override
    public void setFinalAndTotalTime(LocalDateTime finalTime, long seconds){
        this.finalTime = finalTime;
        this.totalTime = this.totalTime + seconds;
        if (this.father != null) this.father.setFinalAndTotalTime(finalTime,seconds);
    }

    @Override
    public void start(){
        this.initialTime = this.clock.getActualTime();
        if (this.father != null) this.father.start();
    }

    @Override
    public void acceptVisitor(Visitor visitor){
        visitor.visitProject(this);
    }



}
