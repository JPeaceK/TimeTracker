public class Main {
    public static void main(String[] args) throws InterruptedException{
        Project root = new Project("root", null);
        Project p1 = new Project("P1", root);
        Project p2 = new Project("P2", root);
        Task t1 = new Task("T1", root);
        Task t2 = new Task("T2", p1);
        Task t3 = new Task("T3", p2);

        t1.

        System.out.println("ABABABABAB");
        Printer printer = new Printer(root);

        Thread.sleep(4000);
        t1.start();
        Thread.sleep(2000);
        t2.start();

        System.out.println("AAAAAAAAAAA");


    }
}
