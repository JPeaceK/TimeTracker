package Milestone3;

import Milestone1.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonTreeGenerator implements Visitor {

  private JSONObject jsonTree;
  private int depth;
  private Activity activity;

  public JsonTreeGenerator(int depth, Activity activity){
    this.depth = depth;
    this.activity = activity;
    this.jsonTree = new JSONObject();
  }

  public JSONObject generate(){
    this.activity.acceptVisitor(this);
    return this.jsonTree;
  }


  @Override
  public void visitTask(Task task) {
    JSONArray intervals = new JSONArray();
    for (Interval interval : task.getIntervals()){
      interval.acceptVisitor(this);
      intervals.put(this.jsonTree);
    }

    JSONObject json = new JSONObject();
    json.put("intervals", intervals);
    json.put("type", "task");
    json.put("id", task.getId());
    json.put("name", task.getName());
    json.put("totalTime", task.getTotalTime());
    if (task.getInitialTime() != null) {
      json.put("initialTime", task.getInitialTime());
    } else {
      json.put("initialTime", JSONObject.NULL);
    }

    if (task.getFinalTime() != null) {
      json.put("finalTime", task.getFinalTime());
    } else {
      json.put("finalTime", JSONObject.NULL);
    }
    json.put("active", task.getActive());

    this.jsonTree = json;
  }

  @Override
  public void visitProject(Project project) {
    JSONArray activities = new JSONArray();
    JSONObject json = new JSONObject();

    int depthAux = depth;

    if (depthAux > 0){
      depth--;
      for (Activity act : project.getActivities()){
        act.acceptVisitor(this);
        activities.put(this.jsonTree);
      }
    }

    json.put("activities", activities);
    json.put("type", "project");
    json.put("id", project.getId());
    json.put("name", project.getName());
    json.put("totalTime", project.getTotalTime());
    if (project.getInitialTime() != null) {
      json.put("initialTime", project.getInitialTime());
    } else {
      json.put("initialTime", JSONObject.NULL);
    }

    if (project.getFinalTime() != null) {
      json.put("finalTime", project.getFinalTime());
    } else {
      json.put("finalTime", JSONObject.NULL);
    }
    this.depth = depthAux;
    this.jsonTree = json;
  }

  @Override
  public void visitInterval(Interval interval) {

    JSONObject json = new JSONObject();
    json.put("type", "interval");
    json.put("totalTime", interval.getTimeInterval());
    if (interval.getInitialTime() != null) {
      json.put("initialTime", interval.getInitialTime());
    } else {
      json.put("initialTime", JSONObject.NULL);
    }

    if (interval.getFinalTime() != null) {
      json.put("finalTime", interval.getFinalTime());
    } else {
      json.put("finalTime", JSONObject.NULL);
    }

    json.put("active", interval.getActive());
    this.jsonTree = json;
  }
}
