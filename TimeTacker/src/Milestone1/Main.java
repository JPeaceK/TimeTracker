package Milestone1;

import Milestone2.SearchByTag;

public class Main {

  public static void testB() {

    try {
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


      final Clock clock = Clock.getInstance();

      Thread.sleep(1500);
      //System.out.println("Transportation starts");
      transportation.start();
      Thread.sleep(6000);
      transportation.stop();
      //System.out.println("Transportation stops");

      Thread.sleep(2000);

      //System.out.println("First_list starts");
      firstList.start();
      Thread.sleep(6000);
      //System.out.println("Second_list starts");
      secondList.start();
      Thread.sleep(4000);

      //System.out.println("First_list stops");
      firstList.stop();
      Thread.sleep(2000);
      //System.out.println("Second_list stops");
      secondList.stop();
      Thread.sleep(2000);

      //System.out.println("Transportation start");
      transportation.start();
      Thread.sleep(4000);
      //System.out.println("Transportation stops");
      transportation.stop();

      Thread.sleep(5000);
      //System.out.println("\nSaving json...\n");
      Save saver = new Save("data", root);
      //System.out.println("\n---DONE---\n");

    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

  }
  public static void testLoad() {
    Load loader = new Load("data");
    Project root = loader.load();

    int size = root.getActivities().size();

    root.getActivities().get(size - 1).start();

  }

  public static void testTags() {
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

    SearchByTag sbt = new SearchByTag("java");
    System.out.println(sbt.search(softwareDesign));

  }

  public static void main(String[] args) {


    testB(); //Saver test implemented too.
    //testLoad();
    testTags();
  }
}
