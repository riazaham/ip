# User Guide
Duke is a desktop app for managing tasks, 
optimized for use via a Command Line Interface 
(CLI) while still having the benefits of a Graphical User Interface (GUI). 
If you can type fast, Duke can get your manage your tasks 
faster than traditional GUI apps.

## Features 

### Adding a todo task : `todo`
Adds a todo task to the list of tasks

Format: `todo TASK_DESCRIPTION`
* Saves the task to a local file

Examples:
* `todo Go for a morning run` Adds the todo task 
"Go for a morning run" to the task list

### Adding a deadline task : `deadline`
Adds a task with a deadline to the list of tasks

Format: `deadline TASK_DESCRIPTION /by DEADLINE`
* Saves the task to a local file
* Task saved as TASK_DESCRIPTION (by: DEADLINE)
* If the DEADLINE is input as a date in the form
**dd/mm/yyyy** it will be saved as MMM dd yyyy

Examples:
* `deadline Submit assignment /by Sunday` Adds the task 
"Submit assignment (by: Sunday)" to the task list
* `deadline Submit assignment /by 1/1/2020` Adds the task 
"Submit assignment (by: Jan 1 2020)" to the task list

### Adding an event task : `event`
Adds a task as an event to the list of tasks

Format: `event TASK_DESCRIPTION /at EVENT`
* Saves the task to a local file
* Task saved as TASK_DESCRIPTION (at: EVENT)

Examples:
* `event Attend competition /at Stadium` Adds the task 
"Attent competition (at: Stadium)" to the task list

### Marking a task as done : `done`
Marks the task at the specified task number as done

Format: `done INDEX`
* Saves the updated task to a local file
* Task at INDEX is marked as done
* INDEX must be a **positive integer**
* INDEX must exist. Must not be larger than the list of tasks

Examples:
* `done 1` Marks the first task as done

### Delete a task : `delete`
Deletes the task at the specified task number

Format: `delete INDEX`
* Saves the updated task to a local file
* Task at INDEX is deleted
* INDEX must be a **positive integer**
* INDEX must exist. Must not be larger than the list of tasks

Examples:
* `delete 1` Deletes the first task

### Find tasks : `find`
Finds all tasks matching with the DESCRIPTION

Format: `find DESCRIPTION`
* The DESCRIPTION is case-sensitive

Examples:
* `find book` Shows all tasks that contains **book** in its description

### View all tasks : `list`
Shows all the tasks in the list

Format: `list`

Examples:
* `list` Shows all the tasks in the list

## Command Summary

Commands | Action | Examples
------------ | ------------- | -------------
**todo** | Adds a todo task to the list of tasks | `todo Go for a morning run`
**deadline** | Adds a task with a deadline to the list of tasks | `deadline Submit assignment /by Sunday`
**event** | Adds a task as an event to the list of tasks | `event Attend competition /at Stadium`
**done** | Marks the task at the specified task number as done | `done 1`
**delete** | Deletes the task at the specified task number | `delete 1`
**find** | Finds all tasks matching with a given description | `find book`
**list** | Shows all tasks in the list | `list`
