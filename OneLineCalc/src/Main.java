import java.util.Scanner;

public class Main {

    private static ListValues list;
    private static ParenSolver solver;

    public static void main(String[] args){
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
            looper();
        }
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





