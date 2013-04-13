package de.podolak.agenten.environment;

import de.podolak.agenten.agent.AbstractAgent;
import de.podolak.agenten.agent.Agent;
import de.podolak.agenten.agent.sensors.SensorType;
import de.podolak.agenten.eventHandling.agentMovedEventHandling.AgentMovedEvent;
import de.podolak.agenten.eventHandling.agentMovedEventHandling.AgentMovedEventListener;
import de.podolak.agenten.eventHandling.fieldCleanlinessChangedEventHandling.FieldCleanlinessChangedEvent;
import de.podolak.agenten.eventHandling.fieldCleanlinessChangedEventHandling.FieldCleanlinessChangedEventListener;
import de.podolak.agenten.eventHandling.timerStartedEventHandling.TimerStartedEvent;
import de.podolak.agenten.eventHandling.timerStartedEventHandling.TimerStartedEventListener;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javax.swing.Timer;
import javax.swing.event.EventListenerList;

/**
 *
 * @version $version$
 * @author $author$
 */
public class Environment implements ActionListener {

    private EventListenerList listenerList = new EventListenerList();
    private static final int MIN_TIME = 2000;
    private static final int MAX_TIME = 5000;
    private ArrayList<Agent> agents;
    private HashMap<Agent, Field> agentPositions;
    private Random random = new Random();
    private Timer timer = new Timer(MIN_TIME + random.nextInt(MAX_TIME - MIN_TIME), this);

    private Area area;
    private int dimensionX = 100;
    private int dimensionY = 100;
    private EnvironmentPanel worldPanel;

    public Environment() {
        this(100, 100);
    }

    public Environment(int dimensionX, int dimensionY) {
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;

        agents = new ArrayList<Agent>();
        agentPositions = new HashMap<Agent, Field>();

        initArea();

        timer.start();
    }

    private void initArea() {
        area = new Area(this, getDimensionX(), getDimensionY());
    }

    private Field getRandomField() {
        return area.getField(random.nextInt(getDimensionX()), random.nextInt(getDimensionY()));
    }

    public void addAgent(AbstractAgent agent) {
        agents.add(agent);
        agentPositions.put(agent, getRandomField());
        worldPanel.addAgent(agent, area.getX(agentPositions.get(agent)), area.getY(agentPositions.get(agent)));
    }

    public void removeAgent(Agent agent) {
        agents.remove(agent);
        agentPositions.remove(agent);
        worldPanel.removeAgent(agent);
    }

    public int numberOfAgents() {
        return agents.size();
    }

    public Field moveAgent(AbstractAgent agent, int xOffset, int yOffset) {
        Field f = agentPositions.get(agent);

        Field field = area.getField(agentPositions.get(agent), xOffset, yOffset);
        moveAgentToField(agent, field);

        return field;
    }

    public void moveAgentToField(AbstractAgent agent, Field newField) {
        if (agents.contains(agent)) {
            Field oldField = agentPositions.get(agent);

            if (oldField != newField) {
                agentPositions.put(agent, newField);
                fireAgentMoved(new AgentMovedEvent(oldField, newField, agent));
            }
        }
    }

    public Field getField(byte locationX, byte locationY) {
        return area.getField(locationX, locationY);
    }

    public Field getField(int locationX, int locationY) {
        Field field = null;

        if (
            locationX >= 0 && locationY >= 0 &&
            locationX < dimensionX && locationY < dimensionY) {
            field = area.getField(locationX, locationY);
        }

        return field;
    }

    public Point getLocation(Field field) {
        return area.getLocation(field);
    }

    public byte measure(Agent agent, SensorType sensorType) {
        byte value = 0;

        if (agents.contains(agent)) {
            value = agentPositions.get(agent).measure(sensorType);
        }

        return value;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        getRandomField().setCleanliness(Field.DIRTY);
        timer.setDelay(MIN_TIME + random.nextInt(MAX_TIME - MIN_TIME));
        fireTimerStarted(new TimerStartedEvent(1, timer.getDelay()));
    }

    /**
     * @return the dimensionX
     */
    public int getDimensionX() {
        return dimensionX;
    }

    /**
     * @param aDimensionX the dimensionX to set
     */
    public void setDimensionX(int aDimensionX) {
        dimensionX = aDimensionX;
    }

    /**
     * @return the dimensionY
     */
    public int getDimensionY() {
        return dimensionY;
    }

    /**
     * @param aDimensionY the dimensionY to set
     */
    public void setDimensionY(int aDimensionY) {
        dimensionY = aDimensionY;
    }

    /**
     * @return the worldPanel
     */
    public EnvironmentPanel getWorldPanel() {
        return worldPanel;
    }

    /**
     * @param worldPanel the worldPanel to set
     */
    public void setWorldPanel(EnvironmentPanel worldPanel) {
        this.worldPanel = worldPanel;
    }

    // <editor-fold defaultstate="collapsed" desc=" field cleanliness changed event handling ">
    public void addFieldCleanlinessChangedEventListener(FieldCleanlinessChangedEventListener fieldCleanlinessChangedEventListener) {
        listenerList.add(FieldCleanlinessChangedEventListener.class, fieldCleanlinessChangedEventListener);
    }

    public void removeFieldCleanlinessChangedEventListener(FieldCleanlinessChangedEventListener fieldCleanlinessChangedEventListener) {
        listenerList.remove(FieldCleanlinessChangedEventListener.class, fieldCleanlinessChangedEventListener);
    }

    public void fireFieldCleanlinessChanged(FieldCleanlinessChangedEvent fieldCleanlinessChangedEvent) {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == FieldCleanlinessChangedEventListener.class) {
                ((FieldCleanlinessChangedEventListener) listeners[i + 1]).fieldCleanlinessChanged(fieldCleanlinessChangedEvent);
            }
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" timer started event handling ">
    public void addTimerStartedEventListener(TimerStartedEventListener timerStartedEventListener) {
        listenerList.add(TimerStartedEventListener.class, timerStartedEventListener);
    }

    public void removeTimerStartedEventListener(TimerStartedEventListener timerStartedEventListener) {
        listenerList.remove(TimerStartedEventListener.class, timerStartedEventListener);
    }

    public void fireTimerStarted(TimerStartedEvent timerStartedEvent) {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == TimerStartedEventListener.class) {
                ((TimerStartedEventListener) listeners[i + 1]).timerStarted(timerStartedEvent);
            }
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" agent moved ">
    public void addAgentMovedEventListener(AgentMovedEventListener agentMovedEventListener) {
        listenerList.add(AgentMovedEventListener.class, agentMovedEventListener);
    }

    public void removeAgentMovedEventListener(AgentMovedEventListener agentMovedEventListener) {
        listenerList.remove(AgentMovedEventListener.class, agentMovedEventListener);
    }

    public void fireAgentMoved(AgentMovedEvent agentMovedEvent) {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == AgentMovedEventListener.class) {
                ((AgentMovedEventListener) listeners[i + 1]).agentMoved(agentMovedEvent);
            }
        }
    }
    // </editor-fold>
}
