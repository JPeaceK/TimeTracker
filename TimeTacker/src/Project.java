import java.time.*;
import java.util.ArrayList;

public class Project extends Activity{
    private String name;
    private LocalDateTime totalTime;
    private ArrayList<Activity> activities;


    public Project(String name){
        this.name = name;
        this.totalTime = null;
        this.activities = new ArrayList<>();
    }

    @Override
    public LocalDateTime calculatetime() {
        return null;
    }

    public void createNewProject(String projectName){

    }

    public void createNewTask(String taskName){

    }
}
