/*
 * FieldPanel.java
 *
 * Created on 04.11.2008, 17:46:57
 */
package de.podolak.agenten.environment;

import de.podolak.agenten.eventHandling.fieldCleanlinessChangedEventHandling.FieldCleanlinessChangedEvent;
import java.awt.Color;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 *
 * @version $version$
 * @author $author$
 */
public class FieldPanel extends JPanel {

    private Field field;
    private static HashMap<Boolean, Color> colorsByCleanlinessState;
    private boolean withBorder = true;

    static {
        colorsByCleanlinessState = new HashMap<Boolean, Color>(2);
        colorsByCleanlinessState.put(Field.CLEAN, Color.WHITE);
        colorsByCleanlinessState.put(Field.DIRTY, Color.GRAY);
    }

    public FieldPanel() {
        initComponents();
    }

    public FieldPanel(Field field) {
        this();

        setField(field);
    }

    /**
     * @return the field
     */
    public Field getField() {
        return field;
    }

    /**
     * @param field the field to set
     */
    public void setField(Field field) {
        this.field = field;
    }

    public void fieldCleanlinessChanged(FieldCleanlinessChangedEvent fieldCleanlinessChangedEvent) {
        setBackground(colorsByCleanlinessState.get(fieldCleanlinessChangedEvent.getNewValue()));
        repaint();
    }

    public void setCleanliness(boolean cleanliness) {
        setBackground(colorsByCleanlinessState.get(cleanliness));
        //repaint();
    }



    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @return the withBorder
     */
    public boolean isWithBorder() {
        return withBorder;
    }

    /**
     * @param withBorder the withBorder to set
     */
    public void setWithBorder(boolean withBorder) {
        this.withBorder = withBorder;

        if (withBorder) {
            setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        } else {
            setBorder(null);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
