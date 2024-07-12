package ui;

import model.Event;
import model.EventLog;

import java.awt.Component;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

//Citation: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git

//Represents a screen printer for printing event log to screen.
public class ScreenPrinter extends JInternalFrame implements LogPrinter {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private JTextArea logArea;


    //Constructor sets up window in which log will be printed on screen
    public ScreenPrinter(Component parent) {
        super("Event log", false, true, false, false);
        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        add(scrollPane);
        setSize(WIDTH, HEIGHT);
        setPosition(parent);
        setVisible(true);
    }

    @Override
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(logArea.getText() + next.toString());
        }
    }

    // Sets the position of window in which log will be printed relative to parent
    private void setPosition(Component parent) {
        setLocation(parent.getWidth() - getWidth() - 20,
                parent.getHeight() - getHeight() - 20);
    }
}
