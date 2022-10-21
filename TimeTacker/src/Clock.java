import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class Clock extends Observable {
    private LocalDateTime actualTime;

    public Clock(){
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

    public static Clock getTime(){
        return new Clock();
    }

}
