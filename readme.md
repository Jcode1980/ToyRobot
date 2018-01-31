
Working with ToyRobot in Intellij 2017.3.1
==============

prerequisites
--------------
The following items should be installed in your system:
- Maven 3 (http://www.sonatype.com/books/mvnref-book/reference/installation.html)
- IntelliJ
- Java 9

Running The Toy Robot Application
--------------

**Inside Intellij**
- Open project into Intellij
    *Running All Tests*
    - Click on ToyRobot project within Intellij -> "Run All Tests"

    *Running main App using default input commands File*
    - Right click on src/main/java/com/toyrobot/App.java
    - Click on "Run 'App.main()'

    *Running main App using custom filePaths*
    -Select ToyRobot project folder
    -Go to Run -> Edit Configurations
    -On the left pane Create New Application Configuration
    -Select App.java for the main class for the new Application Configuration
    -Under "Program Arguments" type in [InputCSV_FilePath] 
    -Hit "Apply"
    -Right click on src/main/java/com/toyrobot/App.java
    -Click Run on your newly created Run Application Configuration

**Terminal Command Line **
   *Running main App using default input commands File*
       mvn exec:java -Dexec.mainClass="com.toyrobot.App

   *Running main App using custom input commands File*
       mvn exec:java -Dexec.mainClass="com.toyrobot.App -Dexec.args=[FILEPATH]
    
Assumptions
=============
   -Valid commands with correct formatting is specified within the problems.md are as follows:
        PLACE X,Y,F
        MOVE
        LEFT
        RIGHT
        REPORT
    -Commands are read line by line
    -Any commands before a place command will be ignored.
    -Non valid incorrectly formatted commands will print an error message to log.error
    -Only one Robot can exist on board at any given time

Project Discussions regarding design and future development
==============
   -Cardinal Point stored as an integer in the Robot class so that value can be stored in a database table.
   -Persistance layer can be developed.
   -A new Board or the existing board class can be created/modified to hold multiple PlaceableItems.
   -More commands can be implemented
   -Give the user ability to specify size of board and number of placeablitems
   -Have the ability to specify un-occupiable spaces on map
   -Support for entering commmands via command line input aside from also reading commands from a file. 
