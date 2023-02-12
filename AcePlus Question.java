import java.util.Scanner;

public class Question {
   private String prompt;
   
   //The list of possible answers will be initialized as an array, and "rightAnswer" will contain the index of the correct answer
   private String[] answers;
   private int rightAnswer;
   
   //The list of hints will be initialized as an array and accessed sequentially, and "hintCounter" keeps track of which hints have been dispensed.
   //For each question, each hint costs the same number of coins. Hints for different questions may cost different amounts.
   private String[] hints;
   private int hintCounter = 0;
   private int penalty;
   
   //When the user starts a lesson, the game will initialize the questions one by one. This constructor initializes all of the questions' attributes
   //except for hintCounter, which is incremented every time the user receives a hint
   Question(String prompt, String[] answers, int rightAnswer, String[] hints, int penalty) {
      this.prompt = prompt;
      this.answers = answers;
      this.rightAnswer = rightAnswer;
      this.hints = hints;
      this.penalty = penalty;
   }
   
   public String getPrompt() {
      return this.prompt;
   }
   
   /*
   When the user asks for a hint, three scenarios are possible:
   1) There are no (more) hints available. This occurs when hintCounter >= the number of hints available. When hintCounter = 0, it's the index of the
   first hint, when hintCounter = 1, it's the index of the second hint, etc.
   
   2) There are more hints available, but the user doesn't have enough coins (represented by Test.numCoins) to pay for it. The user can't have a negative
   coin balance, so the user won't receive a hint and no coins will be deducted from their account.
   
   3) There are more hints available and the user has enough coins to pay for one. The user already knows the price of the hint from the question prompt,
   and getHint() will tell them what their new account balance is. The price will be deducted from Test.numCoins, which is a static variable and there's
   only one in existence per user.
   
   hintCounter will also be incremented, so that if this is the last hint, the next time the user asks for a hint, the user will be informed that no
   more hints are available.
   */
   
   //Originally, numCoins was a static variable in Test and was passed into presentQuestion(), where it was passed into getUserAnswer(), where it was
   //passed into getHint(). The problem with this was that if a question had multiple hints and a fee was assessed for each one, getHint() would correctly
   //deduct coins from Test.numCoins, but the value of Test.numCoins() that getHint() had access to wouldn't update until the user moved onto the next
   //question.
   
   //This was because each time Test.numCoins was passed into these functions, they received a copy of the value and did not receive a pointer to it.
   //Therefore, when they modified the value of Test.numCoins, they were modifying the value stored in some other address in memory and were not
   //actually modifying Test.numCoins.
   
   //By using the getter and setter for the Test.numCoins variable instead of passing copies of it, when these functions modify Test.numCoins, they
   //modify the actual value instead of modifying a copy, and the coin balance is updated after every hint instead of after every question.
   
   //public void getHint(int numCoins) {
   public void getHint() {
      int numHints = hints.length;
      //System.out.println("There are " + numHints + " hints available");
      
      if (numHints > hintCounter) {
         if (Test.numCoins >= penalty) {
            System.out.println("You have " + Test.numCoins + " coins. You have " + (Test.numCoins - penalty) + " coins left after purchasing this hint.");
            //numCoins = numCoins - penalty;
            Test.setNumCoins(Test.numCoins - penalty);
            
            //return this.hints[hintCounter++];
            System.out.println(this.hints[hintCounter++]);     //++ is done in postfix so that the hintCounter is updated after its value is retrieved,
            System.out.println();                              //and not before
            return;
            //return true;
         }
         else {
            System.out.println("You don't have enough coins to purchase a hint");
            System.out.println();
            return;
            //return false;
            
            //return "You don't have enough coins to purchase a hint";
         }
      }
      else {
         System.out.println("No more hints available");
         System.out.println();
         return;
         //return false;
         
         //return "No more hints available";
      }
   }
   
   //getUserAnswer() is called by presentQuestion(), which prints out the question prompt and list of possible answers. getUserAnswer()'s four jobs are
   //to get the user's input, verify that the user's input is valid, say whether the input is true or false, and call getHint(), if applicable. 
   
   //private boolean getUserAnswer(int numCoins) {
   private boolean getUserAnswer() {
      //System.out.println(prompt);
         
      Scanner getInput = new Scanner(System.in);
      int userInput = getInput.nextInt();
      
      int numAnswers = answers.length;
      
      while ( (userInput <= 0) || (userInput > numAnswers + 1) ) {
         System.out.println("That is not one of the answer choices. Please choose something else.");
         userInput = getInput.nextInt();
         
         //Not enough time to add exception checking for characters.
      }
      
      if (userInput == numAnswers + 1) {
         //getHint(numCoins);
         getHint();

         return false;
         //return getUserAnswer(numCoins);
      }
      
      if (userInput == this.rightAnswer) {
         System.out.println("That is the right answer");
         return true;
      }
      else
         System.out.println("That is not the right answer");
         return false;
   }
   
   //public void presentQuestion(int numCoins) {
   public void presentQuestion() {
      do {
         System.out.println(prompt);
         System.out.println();
      
         int numAnswers = answers.length;
      
         for (int i = 0; i < numAnswers; i++) {
            System.out.println(i + 1 + " - " + answers[i]);
         }
         
         System.out.println(numAnswers + 1 + " - Get a hint for " + penalty + " coins");
         System.out.println();
      } while (getUserAnswer() == false);
      //} while (getUserAnswer(numCoins) == false);
   }
}

/*
class PlayerAccount {
   public static int numCoins = 5000;
   
   public static void setNumCoins(int newNumCoins) {
      numCoins = newNumCoins;
      //System.out.println("setNumCoins has been called");
   }
}
*/

class Test {
   public static int numCoins = 5000;
   
   public static void setNumCoins(int newNumCoins) {
      numCoins = newNumCoins;
      //System.out.println("setNumCoins has been called");
   }
  
   public static void main(String[] args) {
      String prompt1 = "How do you multiply 3 and 5 in C++?";
      String[] answers1 = {"3 by 5", "3 * 5", "3 x 5", "3 times 5"};
      int rightAnswer1 = 3;
      String[] hints1 = {"When C++ looks at x, it sees a letter of the alphabet and not a mathematical symbol.", 
      "Simple arithmetic (adding, subtracting, multiplying, dividing) is represented by symbols and not words in C++."};
      int penalty1 = 100;
      
      Question question1 = new Question(prompt1, answers1, rightAnswer1, hints1, penalty1);

      
      String prompt2 = "What is an integer in C++?";
      String[] answers2 = {"A letter of the alphabet", "A positive number", "A negative number", "A number without a decimal point"};
      int rightAnswer2 = 4;
      String[] hints2 = {"An integer is a number.", "An integer can be either positive or negative."};
      int penalty2 = 225;
      
      Question question2 = new Question(prompt2, answers2, rightAnswer2, hints2, penalty2);
      
      String prompt3 = "What is a variable in C++?";
      String[] answers3 = {"A word whose definition you can change.", "A word whose definition always changes.", "A word that represents a number.", "A word whose definition you can't be certain about.};
      int rightAnswer3 = 1;
      String[] hints3 = {"Not all variables represent numbers.", "Good programmers keep track of the definitions of their variables and whether they change.};
      int penalty3 = 150;
                         
      Question question3 = new Question(prompt3, answers3, rightAnswer3, hints3, penalty3};
   }
}
