package de.podolak.agenten.eventHandling.timerStartedEventHandling;

/**
 *
 * @version $version$
 * @author $author$
 */
public class TimerStartedEvent {
    private int timerID;
    private long duration;

    public TimerStartedEvent() {
    }

    public TimerStartedEvent(int timerID, long duration) {
        this.timerID = timerID;
        this.duration = duration;
    }

    /**
     * @return the timerID
     */
    public int getTimerID() {
        return timerID;
    }

    /**
     * @param timerID the timerID to set
     */
    public void setTimerID(int timerID) {
        this.timerID = timerID;
    }

    /**
     * @return the duration
     */
    public long getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(long duration) {
        this.duration = duration;
    }
}
