import rax.Lables.*;

public class ParenSolver {
    public Double parenRecursion(String line){ //line is the mathematical input :)
        Mainframe mainframe = new Mainframe();
        String newLine = "";
        char[] arr = line.toCharArray();
        int low = 0; //minimum size
        int high = arr.length - 1; //maximum size
        int pCount = 0; //counter of increments/decrements
        int count = 0; //total increments/decrements done this loop


        for(int i = 0; i < arr.length; i++){
            if (arr[i] == '(') {
                pCount++; //increment pcount if ( comes up
                count++;
            }
            if (arr[i] == ')') {
                pCount--; //decrement pcount if ) comes up
                count++;
            }
            if (pCount == 1)
                low = i; //the first increment will assign low to the index of the first ( seen
            if (pCount == 0 && i != 0) {
                high = i; //the last decrement will assign high to the index of the last ) seen
            }
        }

        if (count == 0){ //done at the deepest parentheses
            framer(line, mainframe);//moves line into a mainframe.
            return mainframe.calculate();
        }

        newLine = strCutter(line, low + 1, high - 1); //sets newLine to a line without outermost parentheses
        line.replace("(" + newLine + ")", Double.toString(parenRecursion(newLine))); //replaces newLine (with parentheses) with its value (without parentheses)
        framer(line, mainframe); //calculates the value
        return mainframe.calculate(); //returns the magic value :)
    }

    public String strCutter(String line, int low, int high){
        return line.substring(low, high);
    }


    public void framer(String line, Mainframe mainframe){
        ListValues listValues = new ListValues();
        String runningString = "";

        for (int i = 0; i < line.length(); i++){
            if (listValues.numListContains(line.charAt(i))){
                runningString += line.charAt(i); //adds char to runningString
            } else if (listValues.opListContains(line.charAt(i))) {//if operator shows up, it adds it to the mainframe
                mainframe.addFrame(Double.parseDouble(runningString), listValues.convertToOP(line.charAt(i)));
                runningString = "";
            }
        }
        //done at end of for-loop
        if (!runningString.equals("")) //if the running string does not equal "" (it is at the end) and there are no longer any operators, it adds a frame with a OP.NULL operator
            mainframe.addFrame(Double.parseDouble(runningString), OP.NULL);
    }

}
