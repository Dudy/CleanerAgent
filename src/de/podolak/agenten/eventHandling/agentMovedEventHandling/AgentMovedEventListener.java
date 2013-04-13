package de.podolak.agenten.eventHandling.agentMovedEventHandling;

import java.util.EventListener;

/**
 *
 * @version $version$
 * @author $author$
 */
public interface AgentMovedEventListener extends EventListener {

    public void agentMoved(AgentMovedEvent agentMovedEvent);
}
