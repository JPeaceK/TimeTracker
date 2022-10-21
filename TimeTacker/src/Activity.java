import java.time.LocalDateTime;

public abstract class Activity {
    protected String name;
    protected LocalDateTime initialDate;
    protected LocalDateTime totalTime;
    protected Activity father;

    public Activity(String name, Activity father){
        this.initialDate = LocalDateTime.now();
        this.name = name;
        this.father = father;
        this.totalTime = null;
    }

    public abstract Activity getFather();
    public abstract String getName();
    public abstract LocalDateTime getTotalTime();

    public abstract LocalDateTime getInitialDate();

    public abstract void acceptVisitor(Visitor visitor);

    /*
    public abstract LocalDateTime calculateTime();
     */
}
