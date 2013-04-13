package de.podolak.agenten.environment;

import de.podolak.agenten.agent.sensors.SensorType;
import java.util.Random;

/**
 *
 * @version $version$
 * @author $author$
 */
public class Field {

    public static final boolean CLEAN = true;
    public static final boolean DIRTY = false;

    private int ID;
    private boolean cleanliness = CLEAN;
    private String name;
    private Environment environment;
    private FieldPanel fieldPanel;

    public Field() {
        this(null, CLEAN, null);
    }

    public Field(Environment environment) {
        this(null, CLEAN, environment);
    }

    public Field(String name, Environment environment) {
        this(name, CLEAN, environment);
    }

    public Field(String name, boolean dirty, Environment environment) {
        ID = new Random().nextInt();

        if (name == null || name.isEmpty()) {
            name = Integer.toString(ID);
        }

        this.name = name;
        this.environment = environment;
        this.cleanliness = dirty;
        this.fieldPanel = new FieldPanel(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Field other = (Field) obj;
        if (this.ID != other.ID) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.ID;
        return hash;
    }

    public boolean isClean() {
        return cleanliness == CLEAN;
    }

    public boolean isDirty() {
        return cleanliness == DIRTY;
    }

    public boolean getCleanliness() {
        return cleanliness;
    }

    /**
     * @param dirty the dirty to set
     */
    public void setCleanliness(boolean cleanliness) {
        if (this.cleanliness != cleanliness) {
//            FieldCleanlinessChangedEvent fieldCleanlinessChangedEvent =
//                    new FieldCleanlinessChangedEvent(this.cleanliness, cleanliness, this);
//            environment.fireFieldCleanlinessChanged(fieldCleanlinessChangedEvent);
        }

        this.cleanliness = cleanliness;
        this.fieldPanel.setCleanliness(cleanliness);
    }

    public String getCleanlinessString() {
        return getCleanlinessString(cleanliness);
    }

    public static String getCleanlinessString(boolean state) {
        return state == DIRTY ? "dirty" : "clean";
    }

    @Override
    public String toString() {
        return "Field[" +
                "name=\"" + getName() + "\"," +
                "cleanliness=" + getCleanlinessString() + "]";
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public byte measure(SensorType sensorType) {
        byte value = -1;

        if (sensorType != null) {
            switch (sensorType) {
                case DIRT:
                    if (isDirty()) {
                        value = 1;
                    } else {
                        value = 2;
                    }
                    break;

                case LOCATION_X:
                    value = (byte) environment.getLocation(this).x;
                    break;
                case LOCATION_Y:
                    value = (byte) environment.getLocation(this).y;
                    break;

                default:
                    break;
            }
        }

        return value;
    }

    /**
     * @return the fieldPanel
     */
    public FieldPanel getFieldPanel() {
        return fieldPanel;
    }

    /**
     * @param fieldPanel the fieldPanel to set
     */
    public void setFieldPanel(FieldPanel fieldPanel) {
        this.fieldPanel = fieldPanel;
    }

    public FieldPanel getFieldPanel(int zoomFactor) {
        fieldPanel.setWithBorder(zoomFactor > 5);
        return fieldPanel;
    }
}
