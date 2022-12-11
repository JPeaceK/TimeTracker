package Milestone3.WebServer;

import Milestone1.Activity;
import Milestone1.Clock;
import Milestone1.Project;
import Milestone1.Task;
import Milestone3.SearchById;

public class MainWebServer {
  public static void main(String[] args) {
    webServer();
  }

  public static void webServer() {
    final Activity root = makeTreeCourses();
    // implement this method that returns the tree of
    // appendix A in the practicum handout

    // start your clock
    Clock clock = Clock.getInstance();

    new WebServer(root);
  }

  public static Activity makeTreeCourses(){
    Project root = new Project("root", null);
    Project softwareDesign = new Project("software_design", root);
    Project softwareTesting = new Project("software_testing", root);
    Project databases = new Project("databases", root);
    Task transportation = new Task("transportation", root);

    Project problems = new Project("problems", softwareDesign);
    Project timeTracker = new Project("time_tracker", softwareDesign);
    Task firstList = new Task("first_list", problems);
    Task secondList = new Task("second_list", problems);
    Task readHandout = new Task("read_handout", timeTracker);
    Task firstMilestone = new Task("first_milestone", timeTracker);

    softwareDesign.addTag("java");
    softwareDesign.addTag("flutter");
    softwareTesting.addTag("c++");
    softwareTesting.addTag("Java");
    softwareTesting.addTag("python");
    databases.addTag("SQL");
    databases.addTag("python");
    databases.addTag("C++");
    firstList.addTag("java");
    secondList.addTag("Dart");
    firstMilestone.addTag("Java");
    firstMilestone.addTag("IntelliJ");

    SearchById searchById = new SearchById(6);
    System.out.println(searchById.search(root));

    return root;
  }
}