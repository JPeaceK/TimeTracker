import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class Clock extends Observable {
    private LocalDateTime actualTime;
    private static Clock clock;

    private Clock(){
        super();
        this.actualTime = LocalDateTime.now();
        Timer timer;
        TimerTask timerTask = new TimerTask() {
            public void run() {
                actualTime = LocalDateTime.now();
                setChanged();
                notifyObservers(this);
            }
        };

        timer = new Timer();
        timer.schedule(timerTask, 0, 1000);
    }

    public Clock getInstance(){
        if (clock == null) clock = new Clock();
        return clock;
    }

}
