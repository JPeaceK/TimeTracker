package Milestone1;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Load class is designed to be able to load a JSON file, previously saved.
 *  This gives to the application the capacity to restart and keep the information before it closes.
 *  We will be able to load all atributes of Activities and Intervals.
 */
public class Load implements Visitor {

  public Logger logger = LoggerFactory.getLogger(Load.class);
  private JSONObject jsonTree;
  private Activity father;

  /**
   * Constructor of Load with a parameter fileName.
   * It reads the JSON file and creates the Load Object with the JSON values.
   *
   * @param fileName - String of the fileName.
   *
   */
  public Load(String fileName) {
    this.father = null;
    try {
      InputStream string = new FileInputStream(fileName);
      JSONTokener json = new JSONTokener(string);
      this.jsonTree = new JSONObject(json);
    } catch (IOException e) {
      logger.error("Could not load");
      throw new RuntimeException(e);
    }

    logger.debug("Load parameter constructor");
    logger.trace("Loading " + fileName + " JSON");
    logger.info("Loading " + fileName + " JSON");
  }

  /**
   * Default Load constructor.
   *
   * @return  the Project object created.
   *
   */
  public Project load() {
    Project root = new Project();
    root.acceptVisitor(this);

    logger.trace("Loading " + root.getName() + " tree");
    logger.info("Loading " + root.getName() + " tree");
    return root;
  }

  @Override
  public void visitTask(Task task) {

    logger.info("Loading task " + task.getName());
    logger.trace("Loading task " + task.getName());

    task.setName(jsonTree.getString("name"));
    task.setId(jsonTree.getInt("id"));
    task.setTotalTime(jsonTree.getLong("totalTime"));

    if (jsonTree.get("initialTime") == JSONObject.NULL) {
      task.setInitialTime(null);
    } else {
      task.setInitialTime(LocalDateTime.parse(jsonTree.getString("initialTime")));
    }

    if (jsonTree.get("finalTime") == JSONObject.NULL) {
      task.setFinalTime(null);
    } else {
      task.setFinalTime(LocalDateTime.parse(jsonTree.getString("finalTime")));
    }
    task.setActive(jsonTree.getBoolean("active"));
    task.setFather((Project) this.father);

    ArrayList<Interval> intervals = new ArrayList<>();

    this.father = task;

    int size = this.jsonTree.getJSONArray("intervals").length();

    if (size > 0) {
      JSONArray intervalsJson = jsonTree.getJSONArray("intervals");
      for (int i = 0; i < size; i++) {
        this.jsonTree = (JSONObject) intervalsJson.get(i);
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

    logger.info("Loading " + project.getName());
    logger.trace("Loading " + project.getName());

    project.setName(jsonTree.getString("name"));
    project.setId(jsonTree.getInt("id"));

    project.setTotalTime(jsonTree.getLong("totalTime"));

    if (jsonTree.get("initialTime") == JSONObject.NULL) {
      project.setInitialTime(null);
    } else {
      project.setInitialTime(LocalDateTime.parse(jsonTree.getString("initialTime")));
    }

    if (jsonTree.get("finalTime") == JSONObject.NULL) {
      project.setFinalTime(null);
    } else {
      project.setFinalTime(LocalDateTime.parse(jsonTree.getString("finalTime")));
    }

    if (this.father == null) {
      project.setFather(null);
    } else {
      project.setFather((Project) this.father);
    }

    ArrayList<Activity> activities = new ArrayList<>();

    this.father = project;
    int size = jsonTree.getJSONArray("activities").length();

    if (size > 0) {
      JSONArray activitiesJson = jsonTree.getJSONArray("activities");

      for (int i = 0; i < size; i++) {
        this.jsonTree = (JSONObject) activitiesJson.get(i);

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

    logger.info("Loading interval");
    logger.trace("Loading interval");

    interval.setTotalTime(jsonTree.getLong("totalTime"));
    if (jsonTree.get("initialTime") == JSONObject.NULL) {
      interval.setInitialTime(null);
    } else {
      interval.setInitialTime(LocalDateTime.parse(jsonTree.getString("initialTime")));
    }

    if (jsonTree.get("finalTime") == JSONObject.NULL) {
      interval.setFinalTime(null);
    } else {
      interval.setFinalTime(LocalDateTime.parse(jsonTree.getString("finalTime")));
    }

    interval.setActive(jsonTree.getBoolean("active"));
    interval.setFather((Task) this.father);
  }
}