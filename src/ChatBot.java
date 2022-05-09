import java.util.Scanner;
import java.time.LocalTime;

public class ChatBot {

    public static void main(String[] args) {
        greetUser();
        repeatName();
        guessAge();
        countNumber();
        testUser();
        goodBye();

    }

    public static void greetUser() {
        switch (getTimeOfDay()) {
            case "night" -> System.out.println("\nHello!");
            case "evening" -> System.out.println("\nGood evening!");
            case "afternoon" -> System.out.println("\nGood afternoon!");
            case "morning" -> System.out.println("\nGood morning!");
        }
    }

    public static void repeatName() {
        System.out.println("What's your name?\n");
        String name = getInput();
        System.out.println("\nHi, " + name + "! It's a pleasure to meet you!\n");
    }

    public static void guessAge() {
        System.out.println("Let's play a game.");
        System.out.println("I am going to try and guess your age by asking a few questions:\n");

        String[] answers = {
                askQuestion("age", 1),
                askQuestion("age", 2),
                askQuestion("age", 3),
                askQuestion("age", 4),
        };

        System.out.println("\nI think you are " + makeGuess(answers) + " years old.");
        System.out.print("Am I correct? Y/N: ");

        if (getInput().equalsIgnoreCase("Y")) {
            System.out.println("\nYay!");
        } else {
            System.out.println("\nAw, dang it.");
        }

    }

    public static void countNumber() {
        System.out.print("\nNow name a number and I will count to it!\nYour number: ");
        // Get userInput as a string, then parse as a double
        double userInput = Double.parseDouble(getInput());
        printCount(userInput);
    }

    public static void testUser() {
        System.out.println("\nNow I will test your programming knowledge with a random question!");
        System.out.println("Enter only the corresponding letter of your answer choice.");

        // Get a random number between 1 and 4
        int randomQuestionNum = getRandomNumber(1, 5);
        String answer = askQuestion("quiz", randomQuestionNum);
        gradeAnswer(answer, randomQuestionNum);
    }

    public static String getInput() {
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }

    public static String getTimeOfDay() {
        // Gets current time
        LocalTime currentTime = LocalTime.now();
        String partOfDay = null;

        // Return a different greeting depending on time of day
        if (currentTime.isAfter(LocalTime.parse("21:00:00"))
                || currentTime.isBefore(LocalTime.parse("05:00:00"))) {
            partOfDay = "night";
        } else if (currentTime.isAfter(LocalTime.parse("17:00:00"))) {
            partOfDay = "evening";
        } else if (currentTime.isAfter(LocalTime.parse("12:00:00"))) {
            partOfDay = "afternoon";
        } else if (currentTime.isAfter(LocalTime.parse("05:00:00"))) {
            partOfDay = "morning";
        }

        return partOfDay;
    }

    public static String askQuestion(String questionType, int questionNumber) {
        if (questionType.equals("age")) {
            // Questions for age guessing game
            switch (questionNumber) {
                case 1 -> System.out.print("Do you own a pair of white New Balance " +
                        "shoes with a blue 'N' on the side? Y/N: ");
                case 2 -> System.out.print("Do you like Spongebob? Y/N: ");
                case 3 -> System.out.print("Do you drink craft beer? Y/N: ");
                case 4 -> System.out.print("Do you like watching Logan Paul videos? Y/N: ");
            }
        } else if (questionType.equals("quiz")) {
            // Questions for programming quiz
            switch (questionNumber) {
                case 1:
                    // Answer C
                    System.out.println("\nWhich is the only language that a computer can understand?\n");
                    System.out.println("A: High-level");
                    System.out.println("B: Application");
                    System.out.println("C: Machine");
                    System.out.println("D: Assembly\n");
                    System.out.print("Your answer: ");
                    break;
                case 2:
                    // Answer A
                    System.out.println("\nIdentifying and fixing errors in a program is also known as what?\n");
                    System.out.println("A: Debugging");
                    System.out.println("B: Executing");
                    System.out.println("C: Testing");
                    System.out.println("D: Compiling\n");
                    System.out.print("Your answer: ");
                    break;
                case 3:
                    // Answer D
                    System.out.println("\nWhich data type is associated with conditional statements?\n");
                    System.out.println("A: string");
                    System.out.println("B: int");
                    System.out.println("C: char");
                    System.out.println("D: boolean\n");
                    System.out.print("Your answer: ");
                    break;
                case 4:
                    // Answer B
                    System.out.println("\nWhat does HTML stand for?\n");
                    System.out.println("A: 'Hey, That's My Lasagna'");
                    System.out.println("B: HyperText Markup Language");
                    System.out.println("C: High-level Technology Middleware Logistics");
                    System.out.println("D: None of the above\n");
                    System.out.print("Your answer: ");
                    break;
            }
        }

        return checkAnswerInput(getInput().toUpperCase(), questionType, questionNumber);

    }

    public static String checkAnswerInput(String answer, String questionType, int questionNumber) {
        // If user enters anything but "Y" or "N" for age question, ask same question
        if (questionType.equals("age")) {
            if (!answer.equals("Y") && !answer.equals("N")) {
                System.out.println("\nImproper response. Try again!\n");
                answer = askQuestion(questionType, questionNumber);
            }
            // If user enters anything but "A", "B", "C", or "D" for quiz question, ask same question
        } else if (questionType.equals("quiz")) {
            if (!answer.equals("A") && !answer.equals("B") && !answer.equals("C") && !answer.equals("D")) {
                System.out.println("\nImproper response. Try again!\n");
                answer = askQuestion(questionType, questionNumber);
            }
        }

        return answer;
    }

    public static int makeGuess(String[] answers) {
        String allAnswers = String.join("", answers);
        int[] estRange = {0, 0};

        switch (allAnswers) {
            case "YNNN":
                estRange[0] = 50;
                estRange[1] = 56;
                break;
            case "YNYN":
                estRange[0] = 46;
                estRange[1] = 52;
                break;
            case "NNYN":
                estRange[0] = 40;
                estRange[1] = 46;
                break;
            case "YYNN":
                estRange[0] = 30;
                estRange[1] = 36;
                break;
            case "YYYN":
                estRange[0] = 34;
                estRange[1] = 40;
                break;
            case "YNYY":
                estRange[0] = 27;
                estRange[1] = 33;
                break;
            case "NYYN":
                estRange[0] = 26;
                estRange[1] = 32;
                break;
            case "YYYY":
                estRange[0] = 21;
                estRange[1] = 27;
                break;
            case "NNNN":
                estRange[0] = 17;
                estRange[1] = 23;
                break;
            case "YNNY":
                estRange[0] = 13;
                estRange[1] = 19;
                break;
            case "NYNY":
                estRange[0] = 9;
                estRange[1] = 15;
                break;
            case "NNNY":
                estRange[0] = 6;
                estRange[1] = 12;
                break;
            case "NYNN":
                estRange[0] = 4;
                estRange[1] = 10;
                break;
            case "NNYY":
                estRange[0] = 21;
                estRange[1] = 25;
                break;
            case "YYNY":
                estRange[0] = 12;
                estRange[1] = 71;
                break;
            case "NYYY":
                estRange[0] = 21;
                estRange[1] = 71;
                break;
        }

        return getRandomNumber(estRange[0], estRange[1]);
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static String formatNumber(double userInput) {
        return (userInput == (int) userInput)
                ? String.format("%d", (int) userInput)
                : String.valueOf(userInput);
    }

    public static void printCount(double userInput) {
        System.out.println("\nCount:");
        // If user entered a positive number, count from 1 to that number
        if (userInput > 0) {
            // Turn user's number into an int for clean counting
            int max = (int) userInput;
            for (int i = 1; i <= max; i++) {
                System.out.println(i);
            }
            // Print the user's number as a formatted String
            if (userInput % 1 != 0) {
                System.out.println(formatNumber(userInput));
            }
            // If user entered a negative number, count from -1 to that number
        } else if (userInput < 0) {
            int min = (int) userInput;
            for (int i = -1; i >= min; i--) {
                System.out.println(i);
            }
            if (userInput % 1 != 0) {
                System.out.println(formatNumber(userInput));
            }
            // If user entered zero, don't count anything
        } else {
            System.out.println((int) userInput + ". Wow, that was easy!");
        }


    }

    public static void gradeAnswer(String answer, int questionNumber) {
        if (questionNumber == 1) {
            if (answer.equals("C")) {
                System.out.println("\nCorrect. Great job!");
            } else {
                System.out.println("\nThat's incorrect. Let's try again.");
                gradeAnswer(askQuestion("quiz", questionNumber), questionNumber);
            }
        } else if (questionNumber == 2) {
            if (answer.equals("A")) {
                System.out.println("\nCorrect. Great job!");
            } else {
                System.out.println("\nThat's incorrect. Let's try again.");
                gradeAnswer(askQuestion("quiz", questionNumber), questionNumber);
            }
        } else if (questionNumber == 3) {
            if (answer.equals("D")) {
                System.out.println("\nCorrect. Great job!");
            } else {
                System.out.println("\nThat's incorrect. Let's try again.");
                gradeAnswer(askQuestion("quiz", questionNumber), questionNumber);
            }
        } else {
            if (answer.equals("B")) {
                System.out.println("\nCorrect. Great job!");
            } else {
                System.out.println("\nThat's incorrect. Let's try again.");
                gradeAnswer(askQuestion("quiz", questionNumber), questionNumber);
            }
        }
    }

    public static void goodBye() {
        System.out.println("\nWell, it has been nice chatting with you! But it's time for me to go.");
        System.out.println("I hope you have a great rest of your " + getTimeOfDay() + "!\n");
        System.exit(0);
    }
}