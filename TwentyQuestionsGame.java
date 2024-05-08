import java.util.Scanner;

class TreeNode { // tree set up
    String val;
    TreeNode left;
    TreeNode right;

    public TreeNode(String val) {
        this.val = val;
        left = null;
        right = null;
    }
}

class TreeGame {
    TreeNode root;
    TreeNode current;

    public TreeGame() {
        root = null;
        current = null;
    }

    public void load() { // loading the default tree
        System.out.println("Please think of an animal. I will ask you a series of Y/N questions to try to guess.");
        root = new TreeNode("Does it live on land?");
        root.left = new TreeNode("Is it a mammal?");
        root.right = new TreeNode("Does it have fins?");
        root.left.left = new TreeNode("dog");
        root.left.right = new TreeNode("bird");
        root.right.left = new TreeNode("fish");
        root.right.right = new TreeNode("octopus");
        current = root;
    }

    // for gui
    public String getCurrentQuestion() { // get current question
        return current.val;
    }

    public void answerQuestion(boolean answer) { // yes (left) or no (right)
        if (current != null) {
            if (answer) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
    }

    public boolean isFinished() { // reaches a leaf node (no children)
        return current != null && current.left == null && current.right == null;
    }

    public boolean getGuessResult() { // reaches a leaf node (no children)
        return current != null && current.left == null && current.right == null;
    }

    public void updateTree(String definingQuestion, boolean answerToQuestion, String correctAnswer) { // get new question
        if (current != null) {
            TreeNode newQuestion = new TreeNode(definingQuestion);
            TreeNode newAnswer = new TreeNode(correctAnswer);
            TreeNode oldAnswer = current;

            current.val = definingQuestion;
            current.left = newQuestion;
            current.right = newAnswer;

            if (answerToQuestion) {
                newQuestion.left = oldAnswer;
                newQuestion.right = new TreeNode(correctAnswer);
            } else {
                newQuestion.left = new TreeNode(correctAnswer);
                newQuestion.right = oldAnswer;
            }
        }
    }

    public void play() {
        Scanner sc = new Scanner(System.in);
        current = root; // assign current to root as starting point

        int questionCount = 0; // updates every question
        boolean quit = false; // for later when user can choose to play again or quit
        boolean finished = false; // used in place of a break
        TreeNode newAnswer; // initialized

        while (current != null && questionCount < 20 && !finished) {
            if (current.left != null && current.right != null) { // make sure it's a question node
                System.out.println(current.val);
                String response = sc.nextLine().toUpperCase();

                if (response.equals("YES")) {
                    current = current.left; // yes = left
                } else if (response.equals("NO")) {
                    current = current.right; // no = right
                } else {
                    System.out.println("Invalid input"); // else, invalid.
                    break;
                }
                questionCount++; // increase q count

            } else { // if it is a leaf node...
                if (current.left == null && current.right == null) { // check if it's a leaf node (guess)
                    System.out.println("Are you thinking of a " + current.val + "?"); // makes guess
                    String answer = sc.nextLine().toUpperCase(); // user says yes or no

                    if (answer.equals("NO")) {
                        System.out.println("I give up... What were you thinking of?");
                        String correct = sc.nextLine(); // user provides correct answer

                        System.out.println("Please provide a yes/no question that distinguishes your answer"); // program asks for defining question
                        String definingQuestion = sc.nextLine();
                        TreeNode newQuestion = new TreeNode(definingQuestion);

                        System.out.println("For " + correct + ", is the answer yes or no?"); // asks if their answer is y/n for that question
                        String answerToQuestion = sc.nextLine().toUpperCase();

                        current.val = definingQuestion; // defining question becomes current node

                        // creates new answer and leaf nodes
                        if (answerToQuestion.equals("YES")) {
                            newAnswer = new TreeNode(correct);
                        } else {
                            newAnswer = new TreeNode(current.val);
                        }


                        current.left = newQuestion; // adjust/assign
                        current.right = newAnswer;


                        newQuestion.left = new TreeNode(correct); // adjust/assign
                        newQuestion.right = new TreeNode(answerToQuestion.equals("YES") ? current.left.val : correct);

                        System.out.println("I'll remember that for next time.");
                        finished = true; // end the questioning
                    } else {
                        System.out.println("I knew it!");
                        finished = true; // end the questioning
                    }
                } else {
                    System.out.println("Are you thinking of a " + current.val + "?"); // makes guess
                    String answer = sc.nextLine().toUpperCase(); // user says yes or no

                    if (answer.equals("NO")) { // if no, program asks for correct answer and a defining question
                        System.out.println("I give up... What were you thinking of?");
                        String correct = sc.nextLine(); // user provides correct answer

                        System.out.println("Please provide a yes/no question that distinguishes your answer"); // program asks for defining question
                        String definingQuestion = sc.nextLine();
                        TreeNode newQuestion = new TreeNode(definingQuestion);

                        System.out.println("For " + correct + ", is the answer yes or no?"); // asks if their answer is y/n for that question
                        String answerToQuestion = sc.nextLine().toUpperCase();

                        // defining question becomes the current node
                        current.val = definingQuestion;

                        if (answerToQuestion.equals("YES")) { // create new answer and leaf/guess nodes
                            newAnswer = new TreeNode(correct);
                        } else {
                            newAnswer = new TreeNode(current.left.val);
                        }

                        current.left = newQuestion; // adjust/assign children
                        current.right = newAnswer;

                        if (answerToQuestion.equals("YES")) { // correct answer is left child of new question
                            newQuestion.right = new TreeNode(current.left.val);
                        } else {
                            newQuestion.right = new TreeNode(correct);
                        }

                        System.out.println("I'll remember that for next time.");
                        finished = true; // end the questioning
                    } else {
                        System.out.println("I knew it!");
                        finished = true; // end the questioning
                    }
                }
            }
        }
        if (questionCount >= 20) { // 20 questions only
            System.out.println("I've asked 20 questions. I give up!");
        }

        System.out.println("Do you want to play again? (Y/N)"); // user can play again with updated tree
        String playAgain = sc.nextLine().toUpperCase();
        if (!playAgain.equals("Y")) { // clicking n or anything else will quit
            quit = true;
        } else { // clicking y will make game play again
            System.out.println("\nPlease think of a new animal!");
            play();
        }
    }

    public static void main(String[] args) {
        TreeGame game = new TreeGame();
        game.load();
        game.play();
    }
}