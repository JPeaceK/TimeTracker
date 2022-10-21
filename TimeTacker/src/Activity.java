import java.time.LocalDateTime;

public abstract class Activity {
    protected String name;
    protected LocalDateTime initialDate;
    protected long totalTime;

    protected Clock clock;
    protected LocalDateTime finalTime;
    protected Activity father;

    public Activity(String name, Activity father){
        this.initialDate = clock.getActualTime();
        this.name = name;
        this.father = father;
        this.totalTime = 0;
    }

    public abstract Activity getFather();
    public abstract String getName();
    public abstract long getTotalTime();

    public abstract LocalDateTime getInitialDate();

    public abstract void acceptVisitor(Visitor visitor);

    public abstract void setFinalTime(LocalDateTime time, long seconds);

    public abstract void start();

    /*
    public abstract LocalDateTime calculateTime();
     */
}
