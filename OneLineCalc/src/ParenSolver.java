import rax.Lables.*;

public class ParenSolver {
    public Double parenRecursion(String line){ //line is the mathematical input :) // parenRecursion() attempts to resolved entries with parentheses recursively. WORKS FOR NESTED PARENTHESES.
        Mainframe mainframe = new Mainframe();
        StringBuilder stringBuilder = new StringBuilder(line);
        String newLine = "";
        boolean lowtoggle = true;//used to ensure low is only put in once
        int low = 0; //minimum size
        int high = line.length()-1; //maximum size
        int pCount = 0; //counter of increments/decrements
        int count = 0; //total increments/decrements done this loop


        for(int i = 0; i < line.length(); i++){
            if (line.charAt(i) == '(') {
                pCount++; //increment pcount if ( comes up
                count++;
            }
            if (line.charAt(i) == ')') {
                pCount--; //decrement pcount if ) comes up
                count++;
            }
            if (pCount == 1 && lowtoggle) {
                low = i; //the first increment will assign low to the index of the first ( seen}
                lowtoggle = false; //ensures low is only assigned once.
            }

            if (pCount == 0 && i != 0) {
                high = i; //the last decrement will assign high to the index of the last ) seen
            }
            }

        if (count == 0){ //done at the deepest parentheses
            framer(line, mainframe);//moves line into a mainframe.
            return mainframe.calculate(); //returns a result
        }


        newLine = strCutter(line, low + 1, high); //sets newLine to a line without outermost parentheses
        stringBuilder.replace(low, high, Double.toString(parenRecursion(newLine))); //replace ( ... ) with a result
        framer(stringBuilder.toString(), mainframe); //calculates the value with the result ^
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
