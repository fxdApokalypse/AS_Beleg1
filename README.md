## Induction:

This document describes the architecture and the API of the MatrixCalculator which is the result of the first assignment work of the SS/14 for the subject programming II.

#Task description of the assigment

It's to develop a application for matrix computations which meet the following tasks:

* add matrices
* multiply matrices
* multiply matrices
* multiply matrices with scalar
* transpose matrices
* rank determination of matrices
* determination of the largest row sum of a matrix
* print matrices
* create matrices and store it for later usage (but not persistent)
* control the above listed tasks with a user interface

## The User Interface

The user interface is a console commander interpreter which waits for a MenuCommand and their list of parameters. Each command is dedicated to only one task,  has a unique name and a list of possible parameters. Which can be obtained by the help command. To improve the usability the paramaters can be leave off,  hence the MenuCommand have to ask the user for the missing informations if the user leave off a parameter.

## The Architecture

The application is split into two parts:

 - The domain objects [org.yvka.Beleg1.matrix.*] - which contains only classes for matrix computations.

- The user interace [org.yvka.Beleg1.ui.*] - which contains only classes which implements the user interface. The user Interace is split into two main components:

	* Application - is responsiple to handle the entered user commands and executing the corresponding MenuCommand which is an encapsulated menu entry.  Because of that the Application is a composition of MenuCommand.Further more the Application is the execution context of all commands,  hence a application has a Context which is used to share information between commands.  Further more the application serves as MenuCommand dispatcher.  

	* Command - is responsiple to handle a unique dedicated task which have to be reuseable.  To Enable a Command to share information with other commands,  it needs to assign to a Application so that it can share informations with the context of the application.  For this purpose there is the abstract class ApplicationCommand which enables commands to share informations.  ApplicationCommands are called by MenuCommands which itself are called by the application.  Further more MenuCommand are only responsible to handle user input,  error handling,  interface handling,  hence the MenuCommand have to delegate to other commands to fulfill his task.  
