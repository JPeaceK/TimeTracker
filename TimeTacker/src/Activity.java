import java.time.LocalDateTime;

public abstract class Activity {
    protected String name;
    protected LocalDateTime initialTime;
    protected long totalTime;

    protected Clock clock;
    protected LocalDateTime finalTime;
    protected Activity father;

    public Activity(String name, Activity father){
        this.clock = Clock.getInstance();
        this.name = name;
        this.father = father;
        this.totalTime = 0;
        this.initialTime = null;
    }

    public abstract Activity getFather();
    public abstract String getName();
    public abstract long getTotalTime();

    public abstract LocalDateTime getFinalTime();

    public abstract LocalDateTime getInitialDate();

    public abstract void acceptVisitor(Visitor visitor);

    public abstract void setFinalAndTotalTime(LocalDateTime time, long seconds);

    public abstract void start();

}
