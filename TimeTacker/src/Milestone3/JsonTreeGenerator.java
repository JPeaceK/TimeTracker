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
    json.put("class", "task");
    json.put("id", task.getId());
    json.put("name", task.getName());
    json.put("duration", task.getTotalTime());
    if (task.getInitialTime() != null) {
      json.put("initialDate ", task.getInitialTime());
    } else {
      json.put("initialDate", JSONObject.NULL);
    }

    if (task.getFinalTime() != null) {
      json.put("finalDate", task.getFinalTime());
    } else {
      json.put("finalDate", JSONObject.NULL);
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
    json.put("class", "project");
    json.put("id", project.getId());
    json.put("name", project.getName());
    json.put("duration", project.getTotalTime());
    if (project.getInitialTime() != null) {
      json.put("initialDate", project.getInitialTime());
    } else {
      json.put("initialDate", JSONObject.NULL);
    }

    if (project.getFinalTime() != null) {
      json.put("finalDate", project.getFinalTime());
    } else {
      json.put("finalDate", JSONObject.NULL);
    }
    this.depth = depthAux;
    this.jsonTree = json;
  }

  @Override
  public void visitInterval(Interval interval) {

    JSONObject json = new JSONObject();
    json.put("class", "interval");
    json.put("duration", interval.getTimeInterval());
    if (interval.getInitialTime() != null) {
      json.put("initialDate", interval.getInitialTime());
    } else {
      json.put("initialDate", JSONObject.NULL);
    }

    if (interval.getFinalTime() != null) {
      json.put("finalDate", interval.getFinalTime());
    } else {
      json.put("finalDate", JSONObject.NULL);
    }

    json.put("active", interval.getActive());
    this.jsonTree = json;
  }
}
