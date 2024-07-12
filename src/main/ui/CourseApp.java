package ui;

import model.Course;
import model.CourseList;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//course outline summary application
public class CourseApp {
    private static final String JSON_STORE = "./data/CourseList.json";
    private CourseList courseList;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the course outline summary application
    public CourseApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runCourse();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runCourse() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("remove")) {
            doRemove();
        } else if (command.equals("add")) {
            doAdd();
        } else if (command.equals("view")) {
            doView();
        } else if (command.equals("v")) {
            doViewList();
        } else if (command.equals("s")) {
            saveCourseList();
        } else if (command.equals("l")) {
            loadCourseList();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes course and course list
    private void init() {
        courseList = new CourseList("CourseList");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tremove -> removeCourse");
        System.out.println("\tadd -> addCourse");
        System.out.println("\tview -> viewCourseInformation");
        System.out.println("\tv -> viewCourseList");
        System.out.println("\ts -> save course list to file");
        System.out.println("\tl -> load course list from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: add the course to the course list
    private void doAdd() {
        Course newCourse = new Course("CPSC","110","Margia 12345@gmail.com",
                "DMP","6pm","10%");

        System.out.print("Enter course type you want to add: ");
        String type = input.next();
        newCourse.setType(type);

        System.out.print("Enter course number you want to add: ");
        String num = input.next();
        newCourse.setCourseNum(num);

        System.out.print("Enter professor information you want to add: ");
        String proInfor = input.next();
        newCourse.setProInfor(proInfor);

        System.out.print("Enter office hour location you want to add: ");
        String ohl = input.next();
        newCourse.setOfficeHourLocation(ohl);

        System.out.print("Enter office hour time you want to add: ");
        String oht = input.next();
        newCourse.setOfficeHourTime(oht);

        System.out.print("Enter grade weight you want to add: ");
        String gw = input.next();
        newCourse.setGradeWeight(gw);

        courseList.addList(newCourse);
        System.out.print("Course add successfully!");
    }

    // MODIFIES: this
    // EFFECTS: remove the selected course to the course list
    private void doRemove() {
        System.out.print("Enter course Type you want to remove: ");
        String courseType = input.next();

        System.out.print("Enter course number you want to remove: ");
        String number = input.next();

        if (courseList.selectTheCourse(courseType, number)) {
            courseList.removeList(courseList.findCourse(courseType, number));
            System.out.print("Course remove successfully!");
        } else {
            System.out.print("The course you want to remove is not exist");
        }
    }

    // EFFECTS: get the course information of the course you want to view
    private void doView() {
        System.out.print("Enter course Type you want to review: ");
        String courseType = input.next();

        System.out.print("Enter course number you want to review: ");
        String number = input.next();

        if (courseList.selectTheCourse(courseType,number)) {
            System.out.printf("Professor name:'%s'%n", courseList.findCourse(courseType, number).getProInfor());
            System.out.printf("Office Hour Location'%s'%n",
                    courseList.findCourse(courseType,number).getOfficeHourLocation());
            System.out.printf("Office Hour Time:'%s'%n", courseList.findCourse(courseType,number).getOfficeHourTime());
            System.out.printf("Grade Weight:'%s'%n", courseList.findCourse(courseType,number).getGradeWeight());
        }
        System.out.print("The course doesn't exit! ");
    }

    // EFFECTS: view the current course list
    private void doViewList() {
        for (Course course: courseList.getCourseList()) {
            System.out.printf("Course Type:'%s'%n", course.getCourseType());
            System.out.printf("Course Num:'%s'%n", course.getCourseNum());
        }
    }

    // EFFECTS: saves the course list to file
    private void saveCourseList() {
        try {
            jsonWriter.open();
            jsonWriter.write(courseList);
            jsonWriter.close();
            System.out.println("Saved " + courseList.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads course list from file
    private void loadCourseList() {
        try {
            courseList = jsonReader.read();
            System.out.println("Loaded " + courseList.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
