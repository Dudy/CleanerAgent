package de.podolak.agenten.agent;

import de.podolak.agenten.agent.sensors.DirtSensor;
import de.podolak.agenten.agent.sensors.LocationXSensor;
import de.podolak.agenten.agent.sensors.LocationYSensor;
import de.podolak.agenten.agent.sensors.Sensor;
import de.podolak.agenten.agent.sensors.SensorType;
import de.podolak.agenten.environment.Field;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @version $version$
 * @author $author$
 */
public class CleanerAgent extends AbstractAgent {
    // refractory period the timer waits before it allwos the agent
    // to do it's job
    private static final int REFRACTORY_PERIOD = 1000;
    // delay time the agent waits if the agent wanted
    // to do it's job but that was not permitted
    private static final int DELAY = 1000;
    // x and y dimension
    private static final int SIZE = 3;
    private Timer timer = new Timer(REFRACTORY_PERIOD, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            actionPermitted = true;
        }
    });

    public CleanerAgent() {
        super();
    }

    public CleanerAgent(PrintStream outputPrintStream) {
        this();

        this.outputPrintStream = outputPrintStream;

        if (this.outputPrintStream == null) {
            this.outputPrintStream = System.out;
        }

        timer.start();
    }

    @Override
    protected void initSensors() {
        sensorListsByType = new HashMap<SensorType, ArrayList<Sensor>>();
        sensorListsByType.put(SensorType.DIRT, new ArrayList<Sensor>());
        sensorListsByType.put(SensorType.LOCATION_X, new ArrayList<Sensor>());
        sensorListsByType.put(SensorType.LOCATION_Y, new ArrayList<Sensor>());

        sensors = new ArrayList<Sensor>();
        sensors.add(new DirtSensor(this));
        sensors.add(new LocationXSensor(this));
        sensors.add(new LocationYSensor(this));

        sensorListsByType.get(SensorType.DIRT).add(sensors.get(0));
        sensorListsByType.get(SensorType.LOCATION_X).add(sensors.get(1));
        sensorListsByType.get(SensorType.LOCATION_Y).add(sensors.get(2));
    }

    @Override
    public void run() {
        while (run) {
            if (actionPermitted) {
                doClean();
                actionPermitted = false;
            } else {
                try {
                    sleep(DELAY);
                } catch (InterruptedException ex) {
                    Logger.getLogger(CleanerAgent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void doClean() {
        Field field = getField();

        if (field != null) {
            if (field.isDirty()) {
                field.setCleanliness(Field.CLEAN);
                this.outputPrintStream.println("agent " + ID + " cleaned up field " + field.getName());
            } else {
                gotoOtherField();
            }
        }
    }

    private void gotoOtherField() {
        // for now go to a random field, that is one or zero
        // steps up or down and one or zero steps left or right
        move(random.nextInt(3) - 1, random.nextInt(3) - 1);
    }

    private void move(int xOffset, int yOffset) {
        Field newField = environment.moveAgent(this, xOffset, yOffset);
        this.outputPrintStream.println("agent " + ID + " moved to field " + newField.getName());
    }

    private Field getField() {
        Field field = null;

        if (environment != null) {
            field = environment.getField(getLocationByte(1), getLocationByte(2));
        }

        return field;
    }

    private byte getLocationByte(int dimension) {
        SensorType sensorType = SensorType.NONE;

        switch (dimension) {
            case 1:
                sensorType = SensorType.LOCATION_X;
                break;
            case 2:
                sensorType = SensorType.LOCATION_Y;
                break;
        }

        // There may be several sensors for the same input. Here all sensor input
        // is added and the arithmetic average is built and used.

        ArrayList<Sensor> locationSensors = sensorListsByType.get(sensorType);
        byte locationByte = 0;

        for (Sensor sensor : locationSensors) {
            locationByte += sensor.getMeasurement();
        }

        if (locationSensors.size() > 0) {
            locationByte /= locationSensors.size();
        }

        return locationByte;
    }

    @Override
    public String toString() {
        return "CleanAgent[id=" + ID + "]";
    }

    @Override
    public int getSize() {
        return SIZE;
    }
}
