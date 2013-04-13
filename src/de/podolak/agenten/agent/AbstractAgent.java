package de.podolak.agenten.agent;

import de.podolak.agenten.agent.sensors.Sensor;
import de.podolak.agenten.agent.sensors.SensorType;
import de.podolak.agenten.environment.Environment;
import de.podolak.agenten.agent.AgentComponent;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @version $version$
 * @author $author$
 */
public abstract class AbstractAgent extends Thread implements Agent {

    protected AgentComponent view;
    protected Random random = new Random();
    protected final int ID = random.nextInt();
    protected Environment environment;
    protected PrintStream outputPrintStream;
    protected boolean run = true;
    protected boolean actionPermitted = true;
    protected ArrayList<Sensor> sensors;
    protected HashMap<SensorType, ArrayList<Sensor>> sensorListsByType;

    public abstract int getSize();
    protected abstract void initSensors();

    public AbstractAgent() {
        initView();
        initSensors();
    }

    protected void initView() {
        setView(new AgentComponent(this));
    }

    /**
     * @return the view
     */
    public AgentComponent getView() {
        return view;
    }

    /**
     * @param view the view to set
     */
    public void setView(AgentComponent view) {
        this.view = view;
    }

    /**
     * @return the environment
     */
    public Environment getEnvironment() {
        return environment;
    }

    /**
     * @param environment the environment to set
     */
    public void setEnvironment(Environment environment) {
        this.environment = environment;

        for (Sensor sensor : sensors) {
            sensor.setEnvironment(environment);
        }
    }
}
