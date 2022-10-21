public class Main {
    public static void main(String[] args) throws InterruptedException{
        Project root = new Project("root", null);
        Project p1 = new Project("P1", root);
        Project p2 = new Project("P2", root);
        Task t1 = new Task("T1", root);
        Task t2 = new Task("T2", p1);
        Task t3 = new Task("T3", p2);

        final Clock clock = Clock.getInstance();

        t1.start();
        t2.start();

        System.out.println("ABABABABAB");
        Thread.sleep(4000);

        Thread.sleep(2000);


        //printer.visitTask(t1);
        //printer.visitTask(t2);

        System.out.println("AAAAAAAAAAA");


    }
}
