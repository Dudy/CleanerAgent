package de.podolak.agenten.agent.sensors;

import de.podolak.agenten.agent.Agent;
import de.podolak.agenten.environment.Environment;

/**
 *
 * @version $version$
 * @author $author$
 */
public class DirtSensor extends AbstractSensor {

    private Environment environment;

    public DirtSensor(Agent agent) {
        this.agent = agent;
    }

    @Override
    public byte getMeasurement() {
        byte value = 0;

        if (environment != null) {
            value = environment.measure(agent, SensorType.DIRT);
        }

        return value;
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
    }
}
