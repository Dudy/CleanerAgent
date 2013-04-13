package de.podolak.agenten.eventHandling.agentMovedEventHandling;

import de.podolak.agenten.agent.AbstractAgent;
import de.podolak.agenten.environment.Field;

/**
 *
 * @version $version$
 * @author $author$
 */
public class AgentMovedEvent {
    private Field oldField;
    private Field newField;
    private AbstractAgent agent;
    private int xStep;
    private int yStep;

    public AgentMovedEvent() {
    }

    public AgentMovedEvent(Field oldField, Field newField, AbstractAgent agent) {
        this.oldField = oldField;
        this.newField = newField;
        this.agent = agent;
    }

    public AgentMovedEvent(AbstractAgent agent, int xStep, int yStep) {
        this.agent = agent;
        this.xStep = xStep;
        this.yStep = yStep;
    }

    /**
     * @return the oldField
     */
    public Field getOldField() {
        return oldField;
    }

    /**
     * @param oldField the oldField to set
     */
    public void setOldField(Field oldField) {
        this.oldField = oldField;
    }

    /**
     * @return the newField
     */
    public Field getNewField() {
        return newField;
    }

    /**
     * @param newField the newField to set
     */
    public void setNewField(Field newField) {
        this.newField = newField;
    }

    /**
     * @return the agent
     */
    public AbstractAgent getAgent() {
        return agent;
    }

    /**
     * @param agent the agent to set
     */
    public void setAgent(AbstractAgent agent) {
        this.agent = agent;
    }

    @Override
    public String toString() {
        return "AgentMovedEvent[" +
                "oldField=" + oldField + "," +
                "newField=" + newField + "," +
                "agent=" + agent + "]";
    }

    /**
     * @return the xStep
     */
    public int getXStep() {
        return xStep;
    }

    /**
     * @param xStep the xStep to set
     */
    public void setXStep(int xStep) {
        this.xStep = xStep;
    }

    /**
     * @return the yStep
     */
    public int getYStep() {
        return yStep;
    }

    /**
     * @param yStep the yStep to set
     */
    public void setYStep(int yStep) {
        this.yStep = yStep;
    }
}
