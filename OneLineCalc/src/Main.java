import java.util.Scanner;

public class Main {

    public static boolean isLoop = true;
    public static String temp = "";
    private static Mainframe frame;
    private static ListValues list;

    public static void main(String[] args){
        frame = new Mainframe();
        list = new ListValues();
        System.out.println("Welcome to OneLineCalc 1.0");
        System.out.println("To exit the calculator, type EXIT");
        looper();
    }

    private static void looper(){
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        if (lineChecker(line))
            calcuate(line);
        else looper(); //loops back again if the entry was incorrect.
    }

    private static void calcuate(String line){
        if (!line.equalsIgnoreCase("exit")){
            framer(line); //turns the line into a mainframe
            System.out.println(frame.calculate()); //prints the result into a new line
            frame = new Mainframe();
            looper();
        }
    }

    private static void framer(String line){
        String runningNumber = ""; //used for concatenating values
        char[] tempArr = line.toCharArray();
        for (int i = 0; i < tempArr.length; i++){
            if (list.numListContains(tempArr[i])){ //if it is a number or a .
                runningNumber += tempArr[i];
            } else if (list.opListContains(tempArr[i])){
                frame.addFrame(Double.parseDouble(runningNumber), list.convertToOP(tempArr[i])); //adds a frame to the mainframe
                runningNumber = ""; //clears the runningNumber
            }
            }
            if (!runningNumber.equals("")) //this is done for the last number at the end of the line :). Ensures that it is added to the frame
                frame.addFrame(Double.parseDouble(runningNumber), OP.NULL);
        }

        public static void safeAddFrame(String runningNumber, OP operator ) throws NumberFormatException{
        frame.addFrame(Double.parseDouble(runningNumber), operator);

        }

        private static boolean lineChecker(String line) { //ensures that everything is syntatically correct before it is fed to the framer.
            boolean lastIsLetter = false;
            boolean lastIsNumber = false;
            String runningString = "";
            int dCounter = 0; //counter for dots
            int pCounter = 0; //counter for parentheses
            char[] tempArr = line.toCharArray();
            for (int i = 0; i < tempArr.length; i++){
                runningString += tempArr[i]; //adds a character to the runningString (it is read from left to right).

                if (tempArr[i] == '(')
                    pCounter++; //adds one to pcounter
                if (tempArr[i] == ')')
                    pCounter--; //removes one to pcounter
                if (tempArr[i] == '.')
                    dCounter++;

                if (dCounter > 1){ //used to ensure that numbers are entered in correctly
                    System.out.println("NUMBER FORMAT ERROR.");
                    return false; //there are more than two .
                }

            }

            return true;
        }

    }





