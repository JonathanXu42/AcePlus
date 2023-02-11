import java.util.Scanner;

public class Question {
   private String prompt;
   
   private String[] answers;
   private int rightAnswer;
 
   private String[] hints;
   private int hintCounter = 0;
   private int penalty;
   
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
   
   public void getHint(int numCoins) {
      int numHints = hints.length;
      //System.out.println("There are " + numHints + " hints available");
      
      if (numHints > hintCounter) {
         if (numCoins >= penalty) {
            System.out.println("You have " + numCoins + " coins. You have " + (numCoins - penalty) + " coins left after purchasing this hint.");
            numCoins = numCoins - penalty;
            
            //return this.hints[hintCounter++];
            System.out.println(this.hints[hintCounter++]);
            return;
            //return true;
         }
         else {
            System.out.println("You don't have enough coins to purchase a hint");
            return;
            //return false;
            
            //return "You don't have enough coins to purchase a hint";
         }
      }
      else {
         System.out.println("No more hints available");
         return;
         //return false;
         
         //return "No more hints available";
      }
   }
   
   private boolean getUserAnswer(int numCoins) {
      //System.out.println(prompt);
         
      Scanner getInput = new Scanner(System.in);
      int userInput = getInput.nextInt();
      
      int numAnswers = answers.length;
      
      while ( (userInput <= 0) || (userInput > numAnswers + 1) ) {
         System.out.println("That is not one of the answer choices. Please choose something else.");
         userInput = getInput.nextInt();
         
         //Remember to add exception checking for characters
      }
      
      if (userInput == numAnswers + 1) {
         getHint(numCoins);
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
   
   public void presentQuestion(int numCoins) {
      do {
         System.out.println(prompt);
         System.out.println();
      
         int numAnswers = answers.length;
      
         for (int i = 0; i < numAnswers; i++) {
            System.out.println(i + 1 + " - " + answers[i]);
         }
         
         System.out.println(numAnswers + 1 + " - Get a hint for " + penalty + " coins");
      } while (getUserAnswer(numCoins) == false);
   }
}

class Test {
   public static int numCoins = 5000;

   public static void main(String[] args) {
      String prompt1 = "What is the flight speed of an unladen swallow?";
      String[] answers1 = {"I don't know that!", "45 mph", "75 mph", "Is that an African or European swallow?"};
      int rightAnswer1 = 4;
      //String rightAnswer1 = "Is that an African or European swallow?";
      String[] hints1 = {};
      int penalty1 = 500;
      
      Question question1 = new Question(prompt1, answers1, rightAnswer1, hints1, penalty1);
      
      question1.presentQuestion(numCoins);
      
      
      
      String prompt2 = "How do you print the number 5 on the screen in C++?";
      String[] answers2 = {"cout << 5", "cin >> 5", "cout < 5", "cin > 5", "printf 5"};
      int rightAnswer2 = 1;
      String[] hints2 = {"Cout stands for console output and cin stands for console input"};
      int penalty2 = 100;
      
      Question question2 = new Question(prompt2, answers2, rightAnswer2, hints2, penalty2);
      
      question2.presentQuestion(numCoins);
   }
}