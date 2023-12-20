@echo off

REM Save the original directory
set original_dir=%cd%

REM Navigate to the directory containing your .java files
cd src\main\java\

REM Compile the .java files
javac heykile\wordgame\*.java

REM Run the main class
java heykile.wordgame.VocabReviewer

REM Return to the original directory
cd %original_dir%

REM Delete the compiled .class files
del src\main\java\heykile\wordgame\*.class