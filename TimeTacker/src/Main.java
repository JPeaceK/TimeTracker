public class Main {
    public static void main(String[] args) throws InterruptedException{

        final Clock clock = Clock.getInstance();

        t1.start();
        Thread.sleep(4000);
        t2.start();
        Thread.sleep(4000);
        t3.start();
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

        






    }
}
