# Todo-App
A simple android app for managing your todo list.

## Features
* The main activity consists of a single button. Upon clicking the button, the next activity is launched.

![Screenshot 1](/screenshots/newTodo.png)

* The save button sends the form data back to the main activity in an Intent object.
* In the Main Activity, a Todo object is created from the received Intent object and added to the Todolist.

* The tasks in the list are displayed using RecyclerView
* Each task in the list displays its due date, title, abbreviated contents and its completion state.
* Long pressing a task in the list removes the task
* Clicking on a task launches the edit activity or marks the task as complete
* The task list survives configuration changes
* This app utilizes a SQLite database to persist the Todo list

![Screenshot 1](/screenshots/todoList.png)
