import java.util.Scanner;

public class Main {

    public static boolean isLoop = true;
    public static String temp = "";
    public static Mainframe frame;
    public static ListValues list;

    public static void main(String[] args){
        frame = new Mainframe();
        list = new ListValues();
        System.out.println("Welcome to OneLineCalc 1.0");
        System.out.println("To exit the calculator, type EXIT");
        looper();
    }

    public static void looper(){
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        calcuate(line);
    }

    public static void calcuate(String line){
        if (!line.equalsIgnoreCase("exit")){
            framer(line); //turns the line into a mainframe
            System.out.println(frame.calculate()); //prints the result into a new line
            frame = new Mainframe();
            looper();
        }
    }

    public static void framer(String line){
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

        public static void safeAddFrame(String runningNumber, char operator) throws NumberFormatException{
        frame.addFrame(Double.parseDouble(runningNumber), list.convertToOP(operator));

        }
    }





