/* Activity class is designed to implement the Composite Pattern.
 * Tasks and Projects are Activities.
 */

import org.json.JSONObject;

import java.time.LocalDateTime;

public abstract class Activity {
    protected String name;
    protected LocalDateTime initialTime;
    protected long totalTime;

    protected Clock clock;
    protected LocalDateTime finalTime;
    protected Project father; // Father will be always a Project instance. If project is Root, father must be NULL.

    public Activity(String name, Project father) {
        this.clock = Clock.getInstance();
        this.name = name;
        this.father = father;
        this.totalTime = 0;
        this.initialTime = null;
    }

    public abstract Activity getFather();

    public abstract void setName(String name);

    public abstract void setInitialTime(LocalDateTime time);

    public abstract void setTotalTime(long totalTime);

    public abstract void setFinalTime(LocalDateTime time);

    public abstract void setFather(Project father);

    public abstract String getName();

    public abstract long getTotalTime();

    public abstract LocalDateTime getFinalTime();

    public abstract LocalDateTime getInitialTime();

    public abstract void acceptVisitor(Visitor visitor);

    public abstract void setFinalAndTotalTime(LocalDateTime time, long seconds);

    public abstract void start();

}