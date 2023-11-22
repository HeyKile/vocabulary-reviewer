/**
 * Wrapper class for mocking Scanner class
 */

package heykile.wordgame;

import java.util.*;

public class ScannerWrapper {
        private Scanner scanner;

        public ScannerWrapper() {
            this.scanner = new Scanner(System.in);
        }

        public int nextInt() {
            return scanner.nextInt();
        }

        public String nextLine() {
            return scanner.nextLine();
        }

        public void close(){
            scanner.close();
        }
    }
