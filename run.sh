#!/bin/bash

# Save the original directory
original_dir=$(pwd)

# Navigate to the directory containing your .java files
cd src/main/java

# Compile the .java files
javac heykile/wordgame/*.java

# Run the main class
java heykile.wordgame.VocabReviewer

# Return to the original directory
cd "$original_dir"

# Delete the compiled .class files
rm src/main/java/heykile/wordgame/*.class