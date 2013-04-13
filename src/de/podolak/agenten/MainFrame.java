/*
 * MainFrame.java
 *
 * Created on 23.10.2008, 18:12:03
 */
package de.podolak.agenten;

import de.podolak.agenten.agent.CleanerAgent;
import de.podolak.agenten.environment.Environment;
import de.podolak.agenten.eventHandling.fieldCleanlinessChangedEventHandling.FieldCleanlinessChangedEvent;
import de.podolak.agenten.eventHandling.fieldCleanlinessChangedEventHandling.FieldCleanlinessChangedEventListener;
import de.podolak.agenten.eventHandling.timerStartedEventHandling.TimerStartedEvent;
import de.podolak.agenten.eventHandling.timerStartedEventHandling.TimerStartedEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import javax.swing.JSlider;
import javax.swing.Timer;

/**
 *
 * @version $version$
 * @author $author$
 */
public class MainFrame extends javax.swing.JFrame
    implements FieldCleanlinessChangedEventListener, TimerStartedEventListener,
    ActionListener {

    private static final int TIMER_DISPLAY_REFRESH_TIME = 100; // in milliseconds
    private Environment environment;
    private HashMap<Integer, Timer> timerByID;
    private HashMap<Integer, Long> timerDurationByID;
    private int displayedID;
    private PrintStream outputPrintStream;

    /** Creates new form MainFrame */
    public MainFrame() {
        initComponents();
        initOutputStream();

        timerByID = new HashMap<Integer, Timer>();
        timerDurationByID = new HashMap<Integer, Long>();

        environment = new Environment();
        environment.addFieldCleanlinessChangedEventListener(this);
        environment.addTimerStartedEventListener(this);

        worldPanel.setEnvironment(environment);
        zoomSlider.setValue(worldPanel.getZoomFactor());
    }

    private void initOutputStream() {
        try {
            outputPrintStream = new PrintStream(new OutputStream() {

                @Override
                public void write(int b) throws IOException {
                    outputTextArea.append(String.valueOf((char) b));
                    outputTextArea.setCaretPosition(outputTextArea.getText().length());
                }
            }, true, "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fieldCleanlinessChanged(FieldCleanlinessChangedEvent fieldCleanlinessChangedEvent) {
//        JLabel jLabel = null;
//
//        if ("A".equals(fieldCleanlinessChangedEvent.getField().getName())) {
//            jLabel = fieldALabel;
//        } else if ("B".equals(fieldCleanlinessChangedEvent.getField().getName())) {
//            jLabel = fieldBLabel;
//        }
//
//        if (jLabel != null) {
//            jLabel.setText(Field.getCleanlinessString(fieldCleanlinessChangedEvent.getNewValue()));
//        }
//
//        outputPrintStream.println("field " +
//                fieldCleanlinessChangedEvent.getField().getName() + " set to " +
//                Field.getCleanlinessString(fieldCleanlinessChangedEvent.getNewValue()));
        // TODO
    }

    @Override
    public void timerStarted(TimerStartedEvent timerStartedEvent) {
        // local timer to display new remote timer value
        // every TIMER_DISPLAY_REFRESH_TIME milliseconds
        Timer timer = null;

        if (timerByID.containsKey(timerStartedEvent.getTimerID())) {
            timer = timerByID.get(timerStartedEvent.getTimerID());
        } else {
            timer = new Timer(TIMER_DISPLAY_REFRESH_TIME, this);
            timer.start();
        }

        timerByID.put(timerStartedEvent.getTimerID(), timer);

        // duration of remote timer
        Long duration = null;

        if (timerDurationByID.containsKey(timerStartedEvent.getTimerID())) {
            duration = timerDurationByID.get(timerStartedEvent.getTimerID());
        } else {
            duration = timerStartedEvent.getDuration() / 100;
        }

        timerDurationByID.put(timerStartedEvent.getTimerID(), duration);

        // TODO this is not good but enough as I have only one label to dispolay onto
        // ID of timer to display
        displayedID = timerStartedEvent.getTimerID();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (timerByID != null && timerDurationByID != null && displayedID > 0) {
            if (timerDurationByID.get(displayedID) == null || timerDurationByID.get(displayedID) < 0) {
                timerByID.get(displayedID).stop();
                timerByID.remove(displayedID);
                timerDurationByID.remove(displayedID);
                timerUpdateLabel.setText("refractory period");
            } else {
                timerUpdateLabel.setText(
                    Long.toString(timerDurationByID.get(displayedID) / 10) + ","
                    + Long.toString(timerDurationByID.get(displayedID) % 10) + " s");
                timerDurationByID.put(displayedID, timerDurationByID.get(displayedID) - 1);
            }
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
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        timerUpdateLabelLabel = new javax.swing.JLabel();
        timerUpdateLabel = new javax.swing.JLabel();
        worldPanel = new de.podolak.agenten.environment.EnvironmentPanel();
        outputScrollPane = new javax.swing.JScrollPane();
        outputTextArea = new javax.swing.JTextArea();
        startAgentButton = new javax.swing.JButton();
        agentsRunningLabelLabel = new javax.swing.JLabel();
        agentsRunningLabel = new javax.swing.JLabel();
        zoomLabel = new javax.swing.JLabel();
        zoomSlider = new javax.swing.JSlider();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        timerUpdateLabelLabel.setText("time until next refresh of a random field:");

        timerUpdateLabel.setText("unknown");

        outputTextArea.setColumns(20);
        outputTextArea.setRows(5);
        outputScrollPane.setViewportView(outputTextArea);

        startAgentButton.setText("start agent");
        startAgentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startAgentButtonActionPerformed(evt);
            }
        });

        agentsRunningLabelLabel.setText("agents running:");

        zoomLabel.setText("zoom");

        zoomSlider.setMinimum(1);
        zoomSlider.setValue(10);
        zoomSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                zoomSliderStateChanged(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/podolak/agenten/pics/minus.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/podolak/agenten/pics/plus.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, zoomSlider, org.jdesktop.beansbinding.ELProperty.create("${value}"), jLabel1, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(worldPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(startAgentButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zoomLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(zoomSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(agentsRunningLabelLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(agentsRunningLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(outputScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(timerUpdateLabelLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(timerUpdateLabel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timerUpdateLabelLabel)
                    .addComponent(timerUpdateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(worldPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(outputScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(zoomSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(startAgentButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(agentsRunningLabelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(zoomLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1))
                        .addComponent(agentsRunningLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, 0, 0, Short.MAX_VALUE))
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {agentsRunningLabel, agentsRunningLabelLabel, jButton1, jButton2, jLabel1, startAgentButton, zoomLabel, zoomSlider});

        bindingGroup.bind();

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-722)/2, (screenSize.height-707)/2, 722, 707);
    }// </editor-fold>//GEN-END:initComponents

    private void startAgentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startAgentButtonActionPerformed
        CleanerAgent agent = new CleanerAgent(outputPrintStream);

        agent.setEnvironment(environment);
        environment.addAgent(agent);

        agent.start();

        agentsRunningLabel.setText(Integer.toString(environment.numberOfAgents()));
    }//GEN-LAST:event_startAgentButtonActionPerformed

    private void zoomSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_zoomSliderStateChanged
        JSlider source = (JSlider) evt.getSource();

        if (!source.getValueIsAdjusting()) {
            worldPanel.setZoomFactor((int) source.getValue());
        }
    }//GEN-LAST:event_zoomSliderStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        zoomSlider.setValue(zoomSlider.getValue() - 1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        zoomSlider.setValue(zoomSlider.getValue() + 1);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel agentsRunningLabel;
    private javax.swing.JLabel agentsRunningLabelLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane outputScrollPane;
    private javax.swing.JTextArea outputTextArea;
    private javax.swing.JButton startAgentButton;
    private javax.swing.JLabel timerUpdateLabel;
    private javax.swing.JLabel timerUpdateLabelLabel;
    private de.podolak.agenten.environment.EnvironmentPanel worldPanel;
    private javax.swing.JLabel zoomLabel;
    private javax.swing.JSlider zoomSlider;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
