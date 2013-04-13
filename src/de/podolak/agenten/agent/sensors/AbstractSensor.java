package de.podolak.agenten.agent.sensors;

import de.podolak.agenten.agent.Agent;

/**
 * @version $version$
 * @author $author$
 */
public abstract class AbstractSensor implements Sensor {
    protected Agent agent;

    public abstract byte getMeasurement();
}
