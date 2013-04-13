package de.podolak.agenten.eventHandling.fieldCleanlinessChangedEventHandling;

import java.util.EventListener;

/**
 *
 * @version $version$
 * @author $author$
 */
public interface FieldCleanlinessChangedEventListener extends EventListener {

    public void fieldCleanlinessChanged(FieldCleanlinessChangedEvent fieldGetsDirtyEvent);
}
