/*
 * EnvironmentPanel.java
 *
 * Created on 26.10.2008, 20:13:23
 */
package de.podolak.agenten.environment;

import de.podolak.agenten.agent.AgentComponent;
import de.podolak.agenten.agent.AbstractAgent;
import de.podolak.agenten.agent.Agent;
import de.podolak.agenten.eventHandling.agentMovedEventHandling.AgentMovedEvent;
import de.podolak.agenten.eventHandling.agentMovedEventHandling.AgentMovedEventListener;
import de.podolak.agenten.eventHandling.fieldCleanlinessChangedEventHandling.FieldCleanlinessChangedEvent;
import de.podolak.agenten.eventHandling.fieldCleanlinessChangedEventHandling.FieldCleanlinessChangedEventListener;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 *
 * @version $version$
 * @author $author$
 */
public class EnvironmentPanel extends JPanel
        implements FieldCleanlinessChangedEventListener, AgentMovedEventListener {

    private Environment environment;
    private HashMap<Field, FieldPanel> panelsByFields;
    private ArrayList<ArrayList<FieldPanel>> fieldPanels;
    private ArrayList<AgentComponent> agentComponents;
    private ArrayList<ArrayList<Color>> backgrounds;
    private Rectangle visibleRectangle;
    private int zoomFactor = 10;

    public EnvironmentPanel() {
        initComponents();

        panelsByFields = new HashMap<Field, FieldPanel>();
        fieldPanels = new ArrayList<ArrayList<FieldPanel>>();
        agentComponents = new ArrayList<AgentComponent>();

        backgrounds = new ArrayList<ArrayList<Color>>();

        visibleRectangle = new Rectangle(0, 0, getPreferredSize().width, getPreferredSize().height);
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

        if (this.environment != null) {
            this.environment.addAgentMovedEventListener(this);
            this.environment.setWorldPanel(this);

            initFields();
        }
    }

    @Override
    public void fieldCleanlinessChanged(FieldCleanlinessChangedEvent fieldCleanlinessChangedEvent) {
        panelsByFields.get(fieldCleanlinessChangedEvent.getField()).
                fieldCleanlinessChanged(fieldCleanlinessChangedEvent);

        FieldPanel fieldPanel = panelsByFields.get(fieldCleanlinessChangedEvent.getField());
        for (int y = 0; y < backgrounds.size(); y++) {
            for (int x = 0; x < backgrounds.get(y).size(); x++) {
                if (fieldPanels.get(y).get(x) == fieldPanel) {
                    backgrounds.get(y).set(x, fieldPanel.getBackground());

                    // double "break;"visibleRectangle
                    x = backgrounds.get(y).size() - 1;
                    y = backgrounds.size() - 1;
                }
            }
        }

        repaint();
    }

    public void addAgent(AbstractAgent agent, int x, int y) {
        AgentComponent agentComponent = agent.getView();
        agentComponents.add(agentComponent);
        add(agentComponent, 0);

        agentComponent.setBounds(
                (x - visibleRectangle.x) * getZoomFactor(),
                (y - visibleRectangle.y) * getZoomFactor(),
                agentComponent.getAgent().getSize() * getZoomFactor(),
                agentComponent.getAgent().getSize() * getZoomFactor());
    }

//    private void repaintAgents() {
//        for (AgentComponent agentComponent : agentComponents) {
//            agentComponent.getAgent();
//
//            add(agentComponent, 0);
//
//            agentComponent.setBounds(
//                    (x - visibleRectangle.x) * getZoomFactor(),
//                    (y - visibleRectangle.y) * getZoomFactor(),
//                    agentComponent.getAgent().getSize() * getZoomFactor(),
//                    agentComponent.getAgent().getSize() * getZoomFactor());
//        }
//    }

    public void removeAgent(Agent agent) {
        // concurrent modification exception ?
        for (AgentComponent agentComponent : agentComponents) {
            if (agentComponent.getAgent() == agent) {
                remove(agentComponent);
                agentComponents.remove(agentComponent);
                break;
            }
        }
    }

    public void initFields() {
        removeAll();

        int maxY = visibleRectangle.y + visibleRectangle.height / getZoomFactor();
        int maxX = visibleRectangle.x + visibleRectangle.width / getZoomFactor();

        for (int y = visibleRectangle.y; y < maxY; y++) {
            ArrayList<FieldPanel> fieldPanelRow = new ArrayList<FieldPanel>();

            for (int x = visibleRectangle.x; x < maxX; x++) {
                Field field = environment.getField(x, y);

                if (field != null) {
                    FieldPanel fieldPanel = field.getFieldPanel(zoomFactor);
                    add(fieldPanel);
                    fieldPanel.setBounds(x * getZoomFactor(), y * getZoomFactor(), getZoomFactor(), getZoomFactor());
                    fieldPanelRow.add(fieldPanel);
                }
            }

            fieldPanels.add(fieldPanelRow);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(800, 600));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
//        initFields();
    }//GEN-LAST:event_formComponentResized

    @Override
    public void agentMoved(AgentMovedEvent agentMovedEvent) {
        AgentComponent agentComponent = agentMovedEvent.getAgent().getView();
        Point point = environment.getLocation(agentMovedEvent.getNewField());

        agentComponent.setBounds(
                point.x * getZoomFactor(),
                point.y * getZoomFactor(),
                agentComponent.getAgent().getSize() * getZoomFactor(),
                agentComponent.getAgent().getSize() * getZoomFactor());
    }

    /**
     * @return the zoomFactor
     */
    public int getZoomFactor() {
        return zoomFactor;
    }

    /**
     * @param zoomFactor the zoomFactor to set
     */
    public void setZoomFactor(int zoomFactor) {
System.out.println("old zoom factor: " + this.zoomFactor);
        this.zoomFactor = zoomFactor;
System.out.println("new zoom factor: " + this.zoomFactor);
//TODO: repaint agents

        initFields();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
