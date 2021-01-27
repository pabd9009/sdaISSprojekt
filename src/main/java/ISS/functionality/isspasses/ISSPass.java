package ISS.functionality.isspasses;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class ISSPass {

    private long risetime;
    private int duration;

    public ISSPass() {
    }

    public ISSPass(long risetime, int duration) {
        this.risetime = risetime;
        this.duration = duration;
    }

    public long getRisetime() {
        return risetime;
    }

    public void setRisetime(long risetime) {
        this.risetime = risetime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    //TODO String do ogarniecia! PRZED ZMIANAMI ZROBIĆ PULLA!
    @Override
    public String toString() {
        Timestamp ts = new Timestamp(risetime*1000L);
        return "Przelot - " + "data: " + ts.toLocalDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm")) + ", czas trwania: " + duration + " s";
    }
}
