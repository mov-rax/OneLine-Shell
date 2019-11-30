import java.util.Scanner;

public class Main {

    public static boolean isLoop = true;
    public static String temp = "";
    private static Mainframe frame;
    private static ListValues list;
    private static AssignmentController aController;

    public static void main(String[] args){
        frame = new Mainframe();
        list = new ListValues();
        aController = new AssignmentController();
        System.out.println("Welcome to OneLineCalc 1.1");
        System.out.println("To exit the calculator, type EXIT");
        looper();
    }

    private static void looper(){
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        if (!line.equalsIgnoreCase("exit")) { //important line to exit OneLineShell
            line = preChecker(line);//replaces functions/labels with values
            if (lineChecker(line) && !assignChecker(line)) //if the lines is syntatically correct, and is not assigning a value, it feeds it to calculate().
                calculate(line);
            else looper(); //loops back again if the entry was incorrect.
        }
    }

    private static void calculate(String line) {
        framer(line); //turns the line into a mainframe
        System.out.println(frame.calculate()); //prints the result into a new line
        frame = new Mainframe();
        looper();

    }

    private static void framer(String line) {
        String runningNumber = ""; //used for concatenating values
        char[] tempArr = line.toCharArray();
        for (int i = 0; i < tempArr.length; i++) {
            if (list.numListContains(tempArr[i])) { //if it is a number or a .
                runningNumber += tempArr[i];
            } else if (list.opListContains(tempArr[i])) {
                frame.addFrame(Double.parseDouble(runningNumber), list.convertToOP(tempArr[i])); //adds a frame to the mainframe
                runningNumber = ""; //clears the runningNumber
            }
        }
        if (!runningNumber.equals("")) //this is done for the last number at the end of the line :). Ensures that it is added to the frame
            frame.addFrame(Double.parseDouble(runningNumber), OP.NULL);
    }

    public static void safeAddFrame(String runningNumber, OP operator) throws NumberFormatException {
        frame.addFrame(Double.parseDouble(runningNumber), operator);
    }

    private static boolean lineChecker(String line) { //ensures that everything is syntatically correct before it is fed to the framer.
        String runningString = "";
        //AssignmentController controller = new AssignmentController();
        int dCounter = 0; //counter for dots
        int pCounter = 0; //counter for parentheses
        char[] tempArr = line.toCharArray();

        for (int i = 0; i < tempArr.length; i++) {
            runningString += tempArr[i]; //adds a character to the runningString (it is read from left to right).

            if (tempArr[i] == '(')
                pCounter++; //adds one to pcounter
            if (tempArr[i] == ')')
                pCounter--; //removes one to pcounter
            if (tempArr[i] == '.')
                dCounter++;

            if (list.opListContains(tempArr[i])) {
                dCounter--; //removes a . once an operator is put
            }

            if (dCounter > 1) { //used to ensure that numbers are entered in correctly
                System.out.println("NUMBER FORMAT ERROR.");
                return false; //there are more than two .
            }

        }

        if (pCounter != 0) { //used to ensure that the correct amount of parenthesis are utilized
            System.out.println("PARENTHESIS ERROR.");
            return false;
        }

        return true;
    }

    public static boolean assignChecker(String line) {//assigns a value and returns true if it contains := or ->. Returns false if not.

        if (aController.isAssignment(line)) {
            aController.labelAssignment(line); //assigns a label to a value
            return true;
        }
        return false;
    }

    public static String preChecker(String line) { //replaces functions and labels with values. It is called before checking syntax.

        //label checker
        if (aController.containsLabels(line)) { //if the line has labels
            line = aController.replaceLabels(line); //replaces labels with their values
        }

        //function checker not yet implemented
        return line;
    }

}


