# User guide

## Running the application

Download a runnable **jar** file from the [releases](https://github.com/samumakinen/ot-harjoitustyo/releases) page. Choose the latest release from the top of the page.

Place the **jar** file in a location of your choosing and run the application by double clicking on the **jar** file.

**Attention!** Please keep in mind, that a **billsplitter.db** file is created into the location that you store your **jar** file in. This file will be needed to save the changes you make in the application.

### unning the application from command line (optional)

When running the **jar** for the first time, a **billsplitter.db** file will appear in the _working directory_. In order to access the saved data, you will always need to run the app from the same working directory, e.g:

**Jar** is located in **/User/Downloads/BillSplitter-1.x.jar**.
You `cd` with command line to **Downloads** folder and run the **jar** `java -jar BillSplitter-1.x.jar`.
A **billsplitter.db** file is created in **Downloads** folder. Next time you run the app you must run it from the same path in order to keep the changes, otherwise a new **billsplitter.db** file is created into the path you are running the app from this time.

## Login screen

The first screen when the application is launched.

### Logging in

Existing users can log in by typing in their username and pressing the "Login" -button.

<img src=resources/loginscreen-login.png width="360">

### Creating a new user

A new user can be created by typing in a name and an original username, then pressing the "Create" -button.

<img src=resources/loginscreen-create.png width="360">

## History view

A logged in user can see their bills in the history view.

<img src=resources/historyscreen.png width="360">

### Single bill view

Clicking a single bill on the list enters the single bill view where users can modify or delete the bill.

<img src=resources/modifybill.png width="360">

### Creating a new bill

Clicking on the "New bill" -button enters the new bill screen.

<img src=resources/newbillscreen.png width="360">

### Logging out

Clicking on the "Log out" -button logs out the user and enters the login screen.
