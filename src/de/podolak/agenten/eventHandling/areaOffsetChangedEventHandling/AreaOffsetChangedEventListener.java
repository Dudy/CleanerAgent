package de.podolak.agenten.eventHandling.areaOffsetChangedEventHandling;

import java.util.EventListener;

/**
 *
 * @version $version$
 * @author $author$
 */
public interface AreaOffsetChangedEventListener extends EventListener {

    public void areaOffsetChanged(AreaOffsetChangedEvent areaOffsetChangedEvent);
}
