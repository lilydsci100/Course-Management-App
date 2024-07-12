package ui;

import model.Course;
import model.CourseList;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

//Citation: https://pbaumgarten.com/java/swing/
//          https://stackoverflow.com/questions/284899/how-do-you-add-an-actionlistener-onto-a-jbutton-in-java
//          https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git


//Representation of a CourseList application's main window frame.
public class CourseListUI extends JFrame {
    private JPanel panelLeft;
    private JPanel panelRight;
    private JPanel panelTop;
    private JList listCourse;
    private JTextField textCourseType;
    private JTextField textProfessorInfor;
    private JTextField textOfficeHourLocation;
    private JTextField textOfficeHourTime;
    private JTextField textGradeWeight;
    private JTextField textCourseNum;
    private JPanel panelMain;
    private JButton buttonAdd;
    private JButton buttonSaveExisting;
    private JButton removeButton;
    private JButton buttonSaveData;
    private JButton buttonLoadData;
    private JLabel labelPhoto;
    private CourseList courseList;
    private DefaultListModel listCourseModel;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/CourseList.json";
    private LogPrinter lp;
    private EventLog eventLog;


    // construct CourseListUI
    CourseListUI() {
        super("My Course List");
        this.setContentPane(this.panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        courseList = new CourseList("My Course List");
        listCourseModel = new DefaultListModel<>();
        listCourse.setModel(listCourseModel);
        buttonSaveExisting.setEnabled(false);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        lp = new ScreenPrinter(CourseListUI.this);
        eventLog = EventLog.getInstance();

        labelPhoto.setIcon(new ImageIcon("./src/main/ui/image/courselist.png"));
        validate();

        addButtonPanel();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                printLog();
            }
        });

    }

    //EFFECTS: Add a panel containing buttons
    private void addButtonPanel() {
        buttonAdd.addActionListener(e -> buttonAddClicked());
        removeButton.addActionListener(e -> buttonRemoveClicked());
        buttonSaveExisting.addActionListener(e -> buttonSaveExistingClicked());
        listCourse.addListSelectionListener(e -> listSelected());
        buttonSaveData.addActionListener(e -> buttonSaveClicked());
        buttonLoadData.addActionListener(e -> buttonLoadClicked());
    }

    //EFFECTS: Print the event log in the console
    private void printLog() {
        try {
            lp.printLog(eventLog);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "System Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    //EFFECTS: Add a course into list when clicking the Add button
    private void buttonAddClicked() {
        Course c = new Course(
                textCourseType.getText(),
                textCourseNum.getText(),
                textProfessorInfor.getText(),
                textOfficeHourLocation.getText(),
                textOfficeHourTime.getText(),
                textGradeWeight.getText()
        );
        courseList.addList(c);
        refreshCourseList();
    }

    //EFFECTS: Remove a course into list when clicking the Remove button
    private void buttonRemoveClicked() {
        int selectedCourse = listCourse.getSelectedIndex();
        if (courseList.selectTheCourse(courseList.getCourseList().get(selectedCourse).getCourseType(),
                courseList.getCourseList().get(selectedCourse).getCourseNum())) {
            courseList.removeList(courseList.getCourseList().get(selectedCourse));
            removeButton.setEnabled(true);
            refreshCourseList();
        } else {
            removeButton.setEnabled(false);
        }
    }

    //EFFECTS: Save changes of the course in the course list when clicking the Save existing button
    private void buttonSaveExistingClicked() {
        int courseNumber = listCourse.getSelectedIndex();
        if (courseNumber >= 0) {
            Course c = courseList.getCourseList().get(courseNumber);
            c.modifyCourse(textCourseType.getText(), textCourseNum.getText(), textProfessorInfor.getText(),
                    textOfficeHourLocation.getText(), textOfficeHourTime.getText(), textGradeWeight.getText());
            refreshCourseList();
        }
    }

    //EFFECTS: Show the course list
    private void listSelected() {
        int courseNumber = listCourse.getSelectedIndex();
        if (courseNumber >= 0) {
            Course c = courseList.getCourseList().get(courseNumber);
            textCourseType.setText(c.getCourseType());
            textCourseNum.setText(c.getCourseNum());
            textProfessorInfor.setText(c.getProInfor());
            textOfficeHourLocation.setText(c.getOfficeHourLocation());
            textOfficeHourTime.setText(c.getOfficeHourTime());
            textGradeWeight.setText(c.getGradeWeight());
            buttonSaveExisting.setEnabled(true);
        } else {
            buttonSaveExisting.setEnabled(false);
        }
    }

    //EFFECTS: Save the data when clicking the Remove button
    private void buttonSaveClicked() {
        try {
            jsonWriter.open();
            jsonWriter.write(courseList);
            jsonWriter.close();
        } catch (FileNotFoundException f) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
        refreshCourseList();
        JDialog d = new JDialog();
        d.setSize(500, 500);
        d.setVisible(true);
        JPanel panel = new JPanel();
        d.add(panel);
        JLabel label = new JLabel("Save successfully!!");
        panel.add(label);
        JLabel image = new JLabel();
        image.setIcon(new ImageIcon("./src/main/ui/image/success.png"));
        panel.add(image);
        validate();

    }

    //EFFECTS: Load the data when clicking the Remove button
    private void buttonLoadClicked() {
        try {
            courseList = jsonReader.read();
        } catch (IOException f) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        refreshCourseList();
    }

    //EFFECTS: Refresh the course list
    public void refreshCourseList() {
        listCourseModel.removeAllElements();
        for (Course course : courseList.getCourseList()) {
            listCourseModel.addElement(course.getCourseType() + course.getCourseNum());
        }
    }

    public static void main(String[] args) {
        CourseListUI courseListUI = new CourseListUI();
        courseListUI.setBounds(400, 100, 500, 500);
        courseListUI.setSize(700, 800);
        courseListUI.setVisible(true);
    }
}


