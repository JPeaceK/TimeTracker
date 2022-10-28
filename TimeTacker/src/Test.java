public class Test {
    public void testA(){
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
    public void testB() throws InterruptedException{
        Project root = new Project("root", null);
        Task transportation = new Task("transportation", root);
        Task first_list = new Task("first_list", root);
        Task second_list = new Task("second_list", root);

        transportation.start();
        Thread.sleep(4000);
        transportation.stop();
        Thread.sleep(2000);


        first_list.start();
        Thread.sleep(6000);
        second_list.start();
        Thread.sleep(4000);

        first_list.stop();
        Thread.sleep(2000);
        second_list.stop();
        Thread.sleep(2000);

        transportation.start();
        Thread.sleep(4000);
        transportation.stop();

    }
}
