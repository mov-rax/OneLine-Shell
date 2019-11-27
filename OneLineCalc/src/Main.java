import java.util.Scanner;

public class Main {

    public static boolean isLoop = true;
    public static String temp = "";
    private static Mainframe frame;
    private static ListValues list;
    private static ParenSolver solver;

    public static void main(String[] args){
        frame = new Mainframe();
        list = new ListValues();
        solver = new ParenSolver();
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
            System.out.println(solver.parenRecursion(line));
            //framer(line, frame); //turns the line into a mainframe
            //System.out.println(frame.calculate()); //prints the result into a new line
            //frame = new Mainframe();
            looper();
        }
    }

    private static void framer(String line, Mainframe frame){
        String runningNumber = ""; //used for concatenating values
        char[] tempArr = line.toCharArray();

        for (int i = 0; i < tempArr.length; i++){
            if (list.numListContains(tempArr[i])){ //if it is a number or a .
                runningNumber += tempArr[i]; //appends character to end of runningNumber
            } else if (list.opListContains(tempArr[i])){ //if an operator is found as the character, the value behind it will be put into a frame

                //if (list.convertToOP(tempArr[i]) == OP.PARENTHESIS_OPEN || list.convertToOP(tempArr[i]) == OP.PARENTHESIS_CLOSED){ //If the character is ( or )
                //frame.addFrame(null, list.convertToOP(tempArr[i])); //value is NULL, as a new frame will not be added. The priority of the next frame will be altered.
                //}

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

        private static String charCutter(char[] arr, int low, int high){ //low and high are indicies (not including indicies with parentheses)
            String newString = "";
            for (int i = low; i < high; i++)
                newString += arr[i];
            return newString;
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

                if (list.letterListContains(tempArr[i])){ //used to ensure correct usage of labels (not yet implemented)
                    lastIsLetter = true;
                    lastIsNumber = false;
                }

                if (list.numListContains(tempArr[i])){ //used to ensure correct usage of labels (not yet implemented)
                    lastIsLetter = false;
                    lastIsNumber = true;
                }


                if (tempArr[i] == '(')
                    pCounter++; //adds one to pcounter
                if (tempArr[i] == ')')
                    pCounter--; //removes one to pcounter
                if (tempArr[i] == '.')
                    dCounter++;

                if (list.opListContains(tempArr[i])){
                    dCounter--; //removes a . once an operator is put
                }

                if (dCounter > 1){ //used to ensure that numbers are entered in correctly
                    System.out.println("NUMBER FORMAT ERROR.");
                    return false; //there are more than two .
                }

            }

            if (pCounter != 0){ //used to ensure that the correct amount of parenthesis are utilized
                System.out.println("PARENTHESIS ERROR.");
                return false;
            }

            return true;
        }

    }





