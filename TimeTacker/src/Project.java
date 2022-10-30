import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Project extends Activity {

    private ArrayList<Activity> activities;

    public Project(String name, Project father){
        super(name, father);
        this.activities = new ArrayList<>();

        if (father != null) father.addActivity(this);
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
    public LocalDateTime getInitialTime() {return this.initialTime;}

    @Override
    public void setFinalAndTotalTime(LocalDateTime finalTime, long seconds){
        this.finalTime = finalTime;
        this.totalTime = this.totalTime + seconds;
        if (this.father != null) this.father.setFinalAndTotalTime(finalTime,seconds);
    }

    @Override
    public void start(){
        if(this.initialTime == null){
            this.initialTime = this.clock.getActualTime();
        }
        if (this.father != null) {
            this.father.start();
        }
    }

    @Override
    public void acceptVisitor(Visitor visitor){
        visitor.visitProject(this);
    }

    public JSONObject getJSON(){
        JSONObject json = new JSONObject();
        json.put("type", "project");
        json.put("name", this.getName());
        json.put("duration", this.getTotalTime());
        if (this.getInitialTime() != null) json.put("initialDate", this.getInitialTime());
        else json.put("finalDate", JSONObject.NULL);
        if (this.getFinalTime() != null) json.put("finalDate", this.getFinalTime());
        else json.put("finalDate", JSONObject.NULL);

        return json;
    }

    public ArrayList<Activity> getActivities() { return activities; }

    public void addActivity(Activity activity){ this.getActivities().add(activity); }
}
