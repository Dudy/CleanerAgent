package de.podolak.agenten.eventHandling.fieldCleanlinessChangedEventHandling;

import de.podolak.agenten.environment.Field;

/**
 *
 * @version $version$
 * @author $author$
 */
public class FieldCleanlinessChangedEvent {
    private boolean oldValue;
    private boolean newValue;
    private Field field;

    public FieldCleanlinessChangedEvent() {
    }

    public FieldCleanlinessChangedEvent(boolean oldValue, boolean newValue, Field field) {
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.field = field;
    }

    /**
     * @return the oldValue
     */
    public boolean getOldValue() {
        return oldValue;
    }

    /**
     * @param oldValue the oldValue to set
     */
    public void setOldValue(boolean oldValue) {
        this.oldValue = oldValue;
    }

    /**
     * @return the newValue
     */
    public boolean getNewValue() {
        return newValue;
    }

    /**
     * @param newValue the newValue to set
     */
    public void setNewValue(boolean newValue) {
        this.newValue = newValue;
    }

    /**
     * @return the field
     */
    public Field getField() {
        return field;
    }

    /**
     * @param field the field to set
     */
    public void setField(Field field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "FieldCleanlinessChangedEvent[" +
                "oldValue=" + Field.getCleanlinessString(oldValue) + "," +
                "newValue=" + Field.getCleanlinessString(newValue) + "," +
                "field=" + field + "]";
    }
}
