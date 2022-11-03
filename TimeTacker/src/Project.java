import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Project extends Activity {

    private ArrayList<Activity> activities;

    public Project(String name, Project father) {
        super(name, father);
        this.activities = new ArrayList<>();

        if (this.father != null)
            this.father.addActivity(this);
    }

    public Project() {
        super(null, null);
        this.activities = new ArrayList<>();
        // if (this.father != null) this.father.addActivity(this);
    }

    @Override
    public Activity getFather() {
        return this.father;
    }

    public void setActivities(ArrayList<Activity> activities) {
        this.activities = activities;
    }

    @Override
    public void setInitialTime(LocalDateTime name) {
        this.initialTime = name;
    }

    @Override
    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    @Override
    public void setFinalTime(LocalDateTime time) {
        this.finalTime = time;
    }

    @Override
    public void setFather(Project father) {
        this.father = father;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public long getTotalTime() {
        return this.totalTime;
    }

    @Override
    public LocalDateTime getFinalTime() {
        return this.finalTime;
    }

    @Override
    public LocalDateTime getInitialTime() {
        return this.initialTime;
    }

    @Override
    public void setFinalAndTotalTime(LocalDateTime finalTime, long seconds) {
        this.finalTime = finalTime;
        this.totalTime = this.totalTime + seconds;
        if (this.father != null)
            this.father.setFinalAndTotalTime(finalTime, seconds);
    }

    @Override
    public void start() {
        if (this.initialTime == null) {
            this.initialTime = this.clock.getActualTime();
        }
        if (this.father != null) {
            this.father.start();
        }
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitProject(this);
    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public void addActivity(Activity activity) {
        this.getActivities().add(activity);
    }
}
