public class Main {

    public static void testB(){

        try {
            Project root = new Project("root", null);
            Project software_design = new Project("software_design", root);
            Project software_testing = new Project("software_testing", root);
            Project databases = new Project("databases", root);
            Task transportation = new Task("transportation", root);

            Project problems = new Project("problems", software_design);
            Project time_tracker = new Project("time_tracker", software_design);
            Task first_list = new Task("first_list", problems);
            Task second_list = new Task("second_list", problems);
            Task read_handout = new Task("read_handout", time_tracker);
            Task first_milestone = new Task("first_milestone", time_tracker);



            System.out.println("\nTransportation starts\n");
            transportation.start();
            Thread.sleep(4000);
            transportation.stop();
            System.out.println("\nTransportation stops\n");

            Thread.sleep(2000);

            System.out.println("\nFirst_list starts\n");
            first_list.start();
            Thread.sleep(6000);
            System.out.println("\nSecond_list starts\n");
            second_list.start();
            Thread.sleep(4000);

            System.out.println("\nFirst_list stops\n");
            first_list.stop();
            Thread.sleep(2000);
            System.out.println("\nSecond_list stops\n");
            second_list.stop();
            Thread.sleep(2000);

            System.out.println("\nTransportation starts\n");
            transportation.start();
            Thread.sleep(4000);
            System.out.println("\nTransportation stops\n");
            transportation.stop();

            Thread.sleep(5000);
            System.out.println("\nSaving json...\n");
            Save saver = new Save("data", root);
            System.out.println("\n---DONE---\n");




        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
    public static void testA(){
        Project root = new Project("root", null);
        Project software_design = new Project("software_design", root);
        Project software_testing = new Project("software_testing", root);
        Project databases = new Project("databases", root);
        Task transportation = new Task("transportation", root);

        Project problems = new Project("problems", software_design);
        Project time_tracker = new Project("time_tracker", software_design);
        Task first_list = new Task("first_list", problems);
        Task second_list = new Task("second_list", problems);
        Task read_handout = new Task("read_handout", time_tracker);
        Task first_milestone = new Task("first_milestone", time_tracker);
    }



    public static void main(String[] args) throws InterruptedException{
        final Clock clock = Clock.getInstance();
        //testA();
        testB();
    }
}
