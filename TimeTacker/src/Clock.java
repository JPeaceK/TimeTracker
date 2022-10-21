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

        //Thread que canvia el temps del rellotge constantment
        TimerTask timerTask = new TimerTask() {
            public void run() {
                actualTime = LocalDateTime.now();
                setChanged();
                notifyObservers(this);
            }
        };

        timer = new Timer();
        timer.schedule(timerTask, 0, 5000);

        /*
            La classe TimerTask amb el mètode run() ens permet programar una acció
            La classe Timer amb el mètode schedule() ens permet programar la repetició
            continua d'aquesta tasca especificant el delay i període de repetició de la tasca
        */
    }


    public static Clock getInstance(){ //Aplicació del singleton
        if (clock == null) clock = new Clock();
        return clock;
    }

    public LocalDateTime getActualTime(){
        return this.actualTime;
    }

}
