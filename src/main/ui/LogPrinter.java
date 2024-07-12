package ui;

import model.EventLog;

//Citation: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git

//Defines behaviours that event log printers must support.
public interface LogPrinter {

	//EFFECTS: Prints the log
    void printLog(EventLog el) throws Exception;
}
