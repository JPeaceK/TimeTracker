package Milestone1;

import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Save class is designed to save a JSON file with information that
 * we will load when application restarts.
 * It will save all atributes of Activities and Intervals.
 */
public class Save implements Visitor {

  public Logger logger = LoggerFactory.getLogger(Save.class);
  private FileWriter textEditor;
  private JSONObject jsonTree;
  private Project root;

  /**
   * Save Constructor.
   *
   * @param fileName - String of the name of the JSON.
   * @param root - Project object of the root project.
   */
  public Save(String fileName, Project root) {
    try {
      this.textEditor = new FileWriter(fileName);
      this.root = root;
      this.root.acceptVisitor(this);
    } catch (IOException e) {
      logger.error("Could not save");
      throw new RuntimeException(e);
    }

    logger.debug("Save parameter constructor");
    logger.trace("Saving " + root + " at " + fileName);
    logger.info("Saving " + root + " at " + fileName);
  }

  // We want to accept ROOT to sabe the whole tree.
  @Override
  public void visitTask(Task task) {

    logger.trace("Saving " + task.getName());
    logger.info("Saving " + task.getName());

    JSONObject json = new JSONObject();

    json.put("class", "task");
    json.put("id", task.getId());
    json.put("name", task.getName());
    json.put("duration", task.getTotalTime());
    if (task.getInitialTime() != null) {
      json.put("initialDate", task.getInitialTime());
    } else {
      json.put("initialDate", JSONObject.NULL);
    }

    if (task.getFinalTime() != null) {
      json.put("finalDate", task.getFinalTime());
    } else {
      json.put("finalDate", JSONObject.NULL);
    }
    json.put("active", task.getActive());
    JSONArray intervals = new JSONArray();

    for (Interval interval : task.getIntervals()) {
      if (interval != null) {
        interval.acceptVisitor(this);
        intervals.put(this.jsonTree);
      }
    }
    json.put("intervals", intervals);
    this.jsonTree = json;
  }

  @Override
  public void visitProject(Project project) {

    logger.trace("Saving " + project.getName());
    logger.info("Saving " + project.getName());

    JSONObject json = new JSONObject();

    json.put("class", "project");
    json.put("id", project.getId());
    json.put("name", project.getName());
    json.put("duration", project.getTotalTime());
    if (project.getInitialTime() != null) {
      json.put("initialDate", project.getInitialTime());
    } else {
      json.put("intialDate", JSONObject.NULL);
    }

    if (project.getFinalTime() != null) {
      json.put("finalDate", project.getFinalTime());
    } else {
      json.put("finalDate", JSONObject.NULL);
    }

    JSONArray activities = new JSONArray();

    for (Activity activity : project.getActivities()) {
      if (activity != null) {
        activity.acceptVisitor(this);
        activities.put(this.jsonTree);
      }
    }
    json.put("activities", activities);
    this.jsonTree = json;

    // Si estem a root, ja podem guardar el JSON
    if (project.getFather() == null) {
      this.writeDocument();
    }

  }

  @Override
  public void visitInterval(Interval interval) {

    logger.trace("Saving interval");
    logger.info("Saving interval");

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

  /**
   * Function that writes the tree of Activities.
   */
  public void writeDocument() {
    try {
      this.textEditor.write(this.jsonTree.toString());
      this.textEditor.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    logger.debug("Writting JSON document");
    logger.info("Writting JSON document");
  }
}
