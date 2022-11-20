public class Main {

  public static void testB() {

    try {
      Project root = new Project("root", null);
      Project softwareDesign = new Project("software_design", root);
      Project softwareTesting = new Project("software_testing", root);
      Project databases = new Project("databases", root);
      Task transportation = new Task("transportation", root);

      Project problems = new Project("problems", software_design);
      Project timeTracker = new Project("time_tracker", software_design);
      Task firstList = new Task("first_list", problems);
      Task secondList = new Task("second_list", problems);
      Task readHandout = new Task("read_handout", time_tracker);
      Task firstMilestone = new Task("first_milestone", time_tracker);

      final Clock clock = Clock.getInstance();

      Thread.sleep(1500);
      System.out.println("Transportation starts");
      transportation.start();
      Thread.sleep(6000);
      transportation.stop();
      System.out.println("Transportation stops");

      Thread.sleep(2000);

      System.out.println("First_list starts");
      firstList.start();
      Thread.sleep(6000);
      System.out.println("Second_list starts");
      secondList.start();
      Thread.sleep(4000);

      System.out.println("First_list stops");
      firstList.stop();
      Thread.sleep(2000);
      System.out.println("Second_list stops");
      secondList.stop();
      Thread.sleep(2000);

      System.out.println("Transportation start");
      transportation.start();
      Thread.sleep(4000);
      System.out.println("Transportation stops");
      transportation.stop();

      Thread.sleep(5000);
      System.out.println("\nSaving json...\n");
      Save saver = new Save("data", root);
      System.out.println("\n---DONE---\n");

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

  public static void main(String[] args) {


    testB(); //Saver test implemented too.
    //testLoad();
  }
}
