
# My personal project

#### *Who will use it?*
This application is useful for all students who want to record something important of their courses and quickly review it.
#### *What will the application do and Why is this project interesting?*
Different course has different requirements. For example, the grade weight of each course might be different and the midterm time or location can be also different.
For a student who may have several courses in a term, it is difficult to memorize every outline of each course. Most importantly, it is really inconvenient to open every course's syllabus link and check the information one by one.

Therefore, this application will help users to summarize some important messages from each course's syllabus such as the grade weight, the midterm time, professors' email, 
office hours and so on. These messages will be stored in a course that users have and it will be added to a course list. Users only need to enter these messages at the beginning 
of a term so that they can quickly check a course's outline whenever they want.

## User Stories
- As a user, I want to be able to create a new course and add it to a course list.
- As a user, I want to be able to remove a course from my course list.
- As a user, I want to be able to view the list of courses on my course list.
- As a user, I want to be able to select a course in my course list and view course outline's summary.
- As a user, I want to be able to save my course list to file (if I so choose).
- As a user, I want to be able to be able to reload my course list from file and resume exactly where they left off at some earlier time.

# Instructions for Grader
- You can generate the first required action related to adding course to a course list by filling related course information and clicking add button.
- You can generate the second required action related to removing course to a course list by selecting the course in the list and clicking remove button.
- You can locate my visual component by running the CourseListUI, and it can be seen at the top right of the window. Another visual component appears in the new pop-up window after clicking the save button.
- You can save the state of my application by clicking the save button
- You can reload the state of my application by clicking the load button

# Phase 4: Task 2
Wed Apr 05 13:19:07 PDT 2023
Load My Course List
Wed Apr 05 13:19:07 PDT 2023
Added course: model.Course@9b887be
Wed Apr 05 13:19:07 PDT 2023
Added course: model.Course@405c9426
Wed Apr 05 13:19:07 PDT 2023
Added course: model.Course@4cd0b1e6
Wed Apr 05 13:19:07 PDT 2023
Added course: model.Course@4c95b65d
Wed Apr 05 13:19:07 PDT 2023
Added course: model.Course@15d4d06
Wed Apr 05 13:19:07 PDT 2023
Added course: model.Course@250414af
Wed Apr 05 13:19:10 PDT 2023
Added course: model.Course@4504d97d
Wed Apr 05 13:19:12 PDT 2023
Removed course: model.Course@4504d97d
Wed Apr 05 13:19:16 PDT 2023
Save Existing: model.Course@250414af
Wed Apr 05 13:19:17 PDT 2023
Save My Course List

# Phase 4: Task 3
- To provide the code readability and improve cohesion, I think I may extract a ButtonUI from CourseListUI. 
  The ButtonUI contains all the buttons and the effect after clicking button. I think I can extract the method called 
  saveSuccessfullyWindow from the method relate to save button in ButtonUI, which is about a new window that shows 
  save successfully after clicking the save button. 
