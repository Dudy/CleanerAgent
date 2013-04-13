package de.podolak.agenten.agent.sensors;

import de.podolak.agenten.environment.Environment;

/**
 *
 * @version $version$
 * @author $author$
 */
public interface Sensor {
    public byte getMeasurement();
    public void setEnvironment(Environment environment);
}
