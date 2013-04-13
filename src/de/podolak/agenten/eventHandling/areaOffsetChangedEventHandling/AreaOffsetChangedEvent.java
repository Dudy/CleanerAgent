package de.podolak.agenten.eventHandling.areaOffsetChangedEventHandling;

/**
 *
 * @version $version$
 * @author $author$
 */
public class AreaOffsetChangedEvent {
    private int oldXOffset;
    private int oldYOffset;
    private int newXOffset;
    private int newYOffset;

    public AreaOffsetChangedEvent(int oldXOffset, int oldYOffset, int newXOffset, int newYOffset) {
        this.oldXOffset = oldXOffset;
        this.oldYOffset = oldYOffset;
        this.newXOffset = newXOffset;
        this.newYOffset = newYOffset;
    }

    /**
     * @return the oldXOffset
     */
    public int getOldXOffset() {
        return oldXOffset;
    }

    /**
     * @param oldXOffset the oldXOffset to set
     */
    public void setOldXOffset(int oldXOffset) {
        this.oldXOffset = oldXOffset;
    }

    /**
     * @return the oldYOffset
     */
    public int getOldYOffset() {
        return oldYOffset;
    }

    /**
     * @param oldYOffset the oldYOffset to set
     */
    public void setOldYOffset(int oldYOffset) {
        this.oldYOffset = oldYOffset;
    }

    /**
     * @return the newXOffset
     */
    public int getNewXOffset() {
        return newXOffset;
    }

    /**
     * @param newXOffset the newXOffset to set
     */
    public void setNewXOffset(int newXOffset) {
        this.newXOffset = newXOffset;
    }

    /**
     * @return the newYOffset
     */
    public int getNewYOffset() {
        return newYOffset;
    }

    /**
     * @param newYOffset the newYOffset to set
     */
    public void setNewYOffset(int newYOffset) {
        this.newYOffset = newYOffset;
    }
}