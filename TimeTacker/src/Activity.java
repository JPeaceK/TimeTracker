import org.json.JSONObject;

import java.time.LocalDateTime;

public abstract class Activity {
    protected String name;
    protected LocalDateTime initialTime;
    protected long totalTime;

    protected Clock clock;
    protected LocalDateTime finalTime;
    protected Project father;

    protected JSONObject json;

    public Activity(String name, Project father){
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

    public abstract LocalDateTime getInitialTime();

    public abstract void acceptVisitor(Visitor visitor);

    public abstract void setFinalAndTotalTime(LocalDateTime time, long seconds);

    public abstract void start();

    public abstract JSONObject getJSON();

}
