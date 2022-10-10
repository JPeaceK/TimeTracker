import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
public abstract class Activity {
    private String name;

    abstract public LocalDateTime calculatetime();
}
