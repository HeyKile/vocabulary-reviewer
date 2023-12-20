# vocabulary-reviewer

A simple, text-based review game for memorizing vocabulary!

## Installation

Follow these steps to install and run the vocabulary-reviewer:

1. Make sure you have Java installed on your machine. If not, you can download it from [here](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

2. Clone the repository to your local machine using the following command:

    ```bash
    git clone https://github.com/YOUR-USERNAME-HERE/vocabulary-reviewer.git
    ```

3. Navigate to the directory where you cloned the repository:

    ```bash
    cd vocabulary-reviewer
    ```

## Running The Game

Compile and run the script

For windows systems:

```bash
run.bat
 ```

For unix systems:

```bash
chmod +x run.sh
./run.sh
```

Now you *should* be able to play the vocabulary-reviewer game!

Note: in  `VocabReviewer.java`, remeber to change the filepath to the correct path for your dictionary!

```java
        this.reviewTrie.useDictionaryFile("your-file-path\\dictionaries\\cs-dictionary.txt");
```

## How It Works

### Populating the Trie

In the root directory, the [dictionaries](./dictionaries/) folder contains serveral text files. These files can be used as input files to populate `Trie` objects. These can be passed in to `useDictionaryFile()`.

```java
public boolean useDictionaryFile(String fileName){
        String line;
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            while((line = br.readLine()) != null){
                int spaceIndex = line.indexOf("|");
                if(spaceIndex > -1){
                    String word = line.substring(0, spaceIndex);
                    String def = line.substring(spaceIndex + 1);
                    Trie.insert(this, word.toLowerCase(), def.toLowerCase());
                }
                else{
                    if(this.root == null) return false;
                    else return true;
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
```

Currently, the only options are `food-dictionary.txt` and `cs-dictionary.txt`. If you would like to add more or your own dictionaries, you can make your own `.txt` dictionary with the following format:

```plaintext
    word|definition on this side
```

*note: the only supported characters beyond letters a-z are ' ', '-', and '/' (this is due to change)*

### Picking Answers

When running `VocabReview`, the user inputs the number of questions they'd like to answer. That many unique words are then chosen from the `Trie` at random from `getRandomWord()` and placed into `answerKey`.

```java
    public String getRandomWord() {
        TrieNode currentNode = this.root;
        StringBuilder word = new StringBuilder();
        String lastCompleteWord = null;
        Random random = new Random();
        while (true) {
            ArrayList<TrieNode> possibleWordPaths = new ArrayList<>();
            // for all children of current node, get indexes of possible paths
            for (TrieNode child : currentNode.children){
                if (child != null) {
                    possibleWordPaths.add(child);
                } 
            }
            // no more paths, return lastCompletedWord
            if (possibleWordPaths.size() == 0) {
                return lastCompleteWord != null ? lastCompleteWord : null;
            }  
            // choose child node
            int randomIndex = random.nextInt(possibleWordPaths.size());
            TrieNode selectedNode = possibleWordPaths.get(randomIndex);
            word.append(selectedNode.letter);
            if (selectedNode.isWord) {
                lastCompleteWord = word.toString();
            }
            currentNode = selectedNode;
        }
    }
```

### Playing the Game

Once `answerKey` is populated, the game begins. In `displayQuestions()`, definitions are displayed to the user in the order they were placed into `answerKey`. The definitions are presented by taking the String at the current index and passing it to `getDefinition()`.

```java
this.reviewTrie.getDefinition(this.answerKey.get(i))
```

After the user answers all the questions, user answers are compared to the answer key. Once the results are displayed, the user may choose to play again.

## Issues and Future Improvements

Below are some known issues to be fixed and improvements to be made in the near future.

### [Trie.java](./src/main/java/heykile/wordgame/Trie.java)

   1. Just how random is getRandomWord()? From my testing, I believe not very. However, It Works&trade; for the time being.
   2. There is absolutely some general refactoring I'd like to do for better performance and space optimization. I would eventually like to change how children are managed for each new TrieNode, using a Linked-List structure instead of a pre-allocated array.
   3. I would like to change how word count is managed, where any node knows how many words are below it at any point in the trie.
   4. Currently, the dictionary file parser is limited and needs expansion.

### [VocabReview.java](./src/main/java/heykile/wordgame/VocabReviewer.java)

   1. Using ScannerWrapper to mock the Scanner class is a bad way to avoid an annoying problem. I would like to find a different solution to automating user input for testing.
   2. Some cleanup of this code is in order, as the print statements and the structure could be better/more concise.
   3. The replay loop could be done in a far cleaner way, but after coding for 12 hours, that's about all I got!
   4. I will soon be implementing a way to choose input files to populate the `Trie` for each `VocabReviewer` game.

### README.md

   1. Add a changelog (when there are changed to log...)

### Other

   1. Automate test cases
   2. Finishing writing tests

## Sources

### 1. [geeksforgeeks](https://www.geeksforgeeks.org/introduction-to-trie-data-structure-and-algorithm-tutorials/)
