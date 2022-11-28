# LaserTag_team12
Laser Tag System

Description: Laser tag game interface with two screens, one for player entry and one for game progress. The current version has three scenes or screens, the splash screen or loading screen, the player input screen, where the user enters the players, and the first version of the game progress screen.

Built with:

* Java
* JavaFX Version 18 (Recommended)
* FXML
* Oracle OpenJDK Version 17.0.1 (Recommended)
* IntelliJ Idea Community Edition (Recommended)
* Heroku
* PostgreSQL
* Maven Version 3.8.1

How To Make Use Of IT?

- In Scene 1
    1. Make sure you have the recommended libraries and Java versions installed.
    2. and you are using any IDE of your preference
    3. Run the project/application specifically HelloApplication.java for the game to start.
    4. Wait for the Laser Tag game interface to load.

- In Scene 2

    1. Once you can see the player entry screen, you will be able to move around the application
       either with your mouse or the tab key
    2. You will see two tables, one for each team
    3. Enter players in their corresponding team table in the format: code name as a word using the English alphabet and player id as a number. No special characters are allowed.
    4. There is a maximum of 15 players in each table
    5. After entering the players (ID, NAME), you can push the start button or press the right-shift (R-SHIFT) key on the keyboard
       in order to add the players to the database
    6. You will be prompted to enter your picks for the session. First, you will be asked to choose a team to attack. Then, you will be asked to choose shooter and a target according to your previous decision
       Note: players must be added in order to proceed with the game since all data is deleted after each game session
    7. You are also given the option to end the program by typing "end". This will terminate the program


- In scene 3
    1. When all data needed for the session id collected, the third screen will pop up
    2. You will see three tables: a table for red players on the left side of the screen, a log table for the session events in the middle section, and a table for the green players on the right side of the screen
    3. you will see a 6:00 min timer in the top-left corner indicating the time of the session. This timer is automatically started when the screen changes. It also changes its color when 30 seconds are left
    4. The action events will be displayed on the Log table
    5. A warning window will pop up when the timer hits 0 s
    6. after the session is finished all data is deleted from the database



Solutions to specific errors:
note: current solution only works for Windows

UDP server bind exception, do the following:
1. open your terminal
2. Type  the command netstat -ano | findstr port_number , where port number is 7500
3. All process running on this port will be printed
4. look for the ones that are marked as UDP processes and get the process ID (the pID could be a four or five digits number)
5. When you know the pID, type the command: STOP-PROCESS pID , where pID is the running process ID. This should stop the process
6. Check by typing netstat -ano | findstr port_number again. You will see that all processes running on this port are now gone
7. Run the program again
