
public class ParenSolver {
    public Double parenRecursion(String line){ //line is the mathematical input :) // parenRecursion() solves NESTED parentheses.
        Mainframe mainframe = new Mainframe();
        StringBuilder stringBuilder = new StringBuilder(line);
        String newLine = "";
        boolean lowtoggle = true;//used to ensure low is only put in once
        boolean hightoggle = true;
        int low = line.length() - 1; //minimum size
        int high = 0; //maximum size
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

            if (pCount == 0 && i > low && hightoggle) { //if
                high = i; //the last decrement will assign high to the index of the last ) seen
                hightoggle = false;
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

    public String multiP(String line){ //splits un-nested parentheses and feeds them to parenRecursion() then joins them and feeds them to parenRecursion again. :)
        int low = line.length() - 1;
        int high = 0;
        int pCount = 0; //keeps track of parentheses
        boolean lowToggle = true; //checks makes sure the first ( has its index taken.
        StringBuilder stringBuilder = new StringBuilder(line);

        if (!line.contains("(")) //if value contains no parentheses, it will feed it directly into parenRecursion().
            return parenRecursion(line).toString();

        for (int i = 0; i < line.length(); i++){
            if (line.charAt(i) == '('){ //adds one if char is (
                pCount++;
            }

            if (line.charAt(i) == ')') { //removes one if char is )
                pCount--;
            }

            if (pCount == 1 && lowToggle){ //saves the index of first (
                low = i;
                lowToggle = false;
            }

            if (pCount == 0 && i > low){ //saves index of last ) of current (
                high = i;
                break; //breaks from the loop, as the limits of the first parentheses have been reached.
            }
        }

        if (high == line.length() - 1){ //if line has no other parentheses than the original, it will return its value.
            return parenRecursion(line).toString();
        }

        String newLine = parenRecursion(stringBuilder.substring(low, high + 1)).toString(); //gets value of first parentheses
        stringBuilder.replace(low, high + 1, newLine); //replaces parentheses in stringBuilder with the value^
        return multiP(stringBuilder.toString());
    }
}



