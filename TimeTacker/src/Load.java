/*  Load class is designed to be able to load a JSON file, previously saved.
 *  This gives to the application the capacity to restart and keep the information before it closes.
 *  We will be able to load all atributes of Activities and Intervals.
 */ 

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Load implements Visitor {

    private JSONObject jsonTree;
    private Activity father;

    public Load(String fileName) {
        this.father = null;
        try {
            InputStream string = new FileInputStream(fileName);
            JSONTokener JSON = new JSONTokener(string);
            this.jsonTree = new JSONObject(JSON);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Project load() {
        Project root = new Project();
        root.acceptVisitor(this);
        return root;
    }

    @Override
    public void visitTask(Task task) {

        task.setName(jsonTree.getString("name"));

        task.setTotalTime(jsonTree.getLong("totalTime"));

        if (jsonTree.get("initialTime") == JSONObject.NULL)
            task.setInitialTime(null);
        else
            task.setInitialTime(LocalDateTime.parse(jsonTree.getString("initialTime")));

        if (jsonTree.get("finalTime") == JSONObject.NULL)
            task.setFinalTime(null);
        else
            task.setFinalTime(LocalDateTime.parse(jsonTree.getString("finalTime")));

        task.setActive(jsonTree.getBoolean("active"));
        task.setFather((Project) this.father);

        ArrayList<Interval> intervals = new ArrayList<>();

        this.father = task;

        int size = this.jsonTree.getJSONArray("intervals").length();

        if (size > 0) {
            JSONArray intervalsJSON = jsonTree.getJSONArray("intervals");
            for (int i = 0; i < size; i++) {
                this.jsonTree = (JSONObject) intervalsJSON.get(i);
                Interval interval = new Interval();

                interval.acceptVisitor(this);
                intervals.add(interval);
                this.father = task;
            }
        }

        task.setIntervals(intervals);
    }

    @Override
    public void visitProject(Project project) {
        project.setName(jsonTree.getString("name"));

        project.setTotalTime(jsonTree.getLong("totalTime"));

        if (jsonTree.get("initialTime") == JSONObject.NULL)
            project.setInitialTime(null);
        else
            project.setInitialTime(LocalDateTime.parse(jsonTree.getString("initialTime")));

        if (jsonTree.get("finalTime") == JSONObject.NULL)
            project.setFinalTime(null);
        else
            project.setFinalTime(LocalDateTime.parse(jsonTree.getString("finalTime")));

        if (this.father == null)
            project.setFather(null);
        else
            project.setFather((Project) this.father);

        ArrayList<Activity> activities = new ArrayList<>();

        this.father = project;
        int size = jsonTree.getJSONArray("activities").length();

        if (size > 0) {
            JSONArray activitiesJSON = jsonTree.getJSONArray("activities");

            for (int i = 0; i < size; i++) {
                this.jsonTree = (JSONObject) activitiesJSON.get(i);

                if (Objects.equals(jsonTree.getString("type"), "project")) {
                    Project p = new Project();
                    p.acceptVisitor(this);
                    activities.add(p);
                } else if (Objects.equals(jsonTree.getString("type"), "task")) {
                    Task t = new Task();
                    t.acceptVisitor(this);
                    activities.add(t);
                }
                this.father = project;
            }
        }

        project.setActivities(activities);

    }

    @Override
    public void visitInterval(Interval interval) {
        interval.setTotalTime(jsonTree.getLong("totalTime"));
        if (jsonTree.get("initialTime") == JSONObject.NULL)
            interval.setInitialTime(null);
        else
            interval.setInitialTime(LocalDateTime.parse(jsonTree.getString("initialTime")));
        if (jsonTree.get("finalTime") == JSONObject.NULL)
            interval.setFinalTime(null);
        else
            interval.setFinalTime(LocalDateTime.parse(jsonTree.getString("finalTime")));
        interval.setActive(jsonTree.getBoolean("active"));
        interval.setFather((Task) this.father);
    }
}