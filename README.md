# vocabulary-reviewer

A simple. text-based review game for memorizing vocabulary

## Installation

## Code Discussion

## Issues and Future Improvements

Below are some known issues to be fixed and improvements to be made in the near future.

### Trie.java

   1. Just how random is getRandomWord()??? From my testing, I believe not very. However, It Works:&trade: for the time being.
   2. There is aboslutely some general refactoring I'd like to do for better performance and space optimization. I would eventually like to change how children are managed for each new TrieNode, using a Linked-List structure instead of a pre-allocated array.
   3. I would like to change how word count is managed, where any node knows how many words are below it at any point on the trie.

### VocabReview.java

   1. Using ScannerWrapper to mock the Scanner class is a bad way to avoid an annoying problem. I would like to find a different solution to automating user input for testing.
   2. Some cleanup of this code is in order, as the print statements and the structure could be better/more concise.
   3. The replay loop could be done in a far cleaner way, but after coding for 12 hours, that's about all I got!

## Sources

### 1. [geeksforgeeks](https://www.geeksforgeeks.org/introduction-to-trie-data-structure-and-algorithm-tutorials/)
