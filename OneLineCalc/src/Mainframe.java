public class Mainframe {
    private Frame brain;
    private Frame cursor;
    private boolean boolio; //makes PEMDAS a reality :)
    private boolean exists; //makes sure no operation is skipped
    private boolean exists2; //makes sure no operation is skipped
    private boolean nextIncrement; //increments next addFrame
    private boolean nextDecrement; //decrement next addFrame

    Mainframe(){
        brain = new Frame();
        cursor = brain;
        boolio = true;
        exists = true;
        exists2 = true;
        nextIncrement = false;
        nextDecrement = false;
    }

    void addFrame(Double value, OP operator){ //adds a brain to the mainframe.

        while (cursor.getNext() != null){ //moves cursor to the end of the brain
            cursor = cursor.getNext();
        }

        if (cursor.getB() == null) { //if it has no value
            cursor.setB(value); //sets value
            cursor.setOP(operator); //sets operator

        } else {
            cursor.setNext(new Frame(value, operator, cursor)); //sets next brain to a new brain, with value, operator, and sets prevBrain to the cursor;
        }



    }

    private Double recurseCalc(){ //highest priority and last priority
                if (brain.getNext() != null){//start


            if (boolio){ //does magic for MD&M operators
                mdmn_switch(); //checks if the element has a MULTIPLY, DIVIDE, or MODULO operator.
                if (exists)
                    recurseCalc(); //if multiplication operator is found, it will check the same node again in the next loop.
                else if(cursor.getNext() != null){
                    cursor = cursor.getNext(); //moves loop forward
                    recurseCalc(); //calls loop again
                }
            } else{ //does magic for A&S operators, which does not need to be searched in the same way MD&M requires.
                as_switch(); //all that is needed. The cursor does not need to move from the brain. loop will end once the brain is the only element left.
                recurseCalc(); //calls recurseCalc
            }


        }//end
        return brain.getB();
    }

    Double calculate(){ //essentially evaluates the brain, leaving only the root.
        cursor = brain; //sets the cursor back to the forefront.
        return recurseCalc();
    }

    private void mdmn_switch(){

        switch(cursor.getOP()) { //----SEPERATES MULTIPLY. DIVIDE, AND MODULO OPERATORS FROM THE ADD AND SUBTRACT OPERATORS TO KEEP ORDER OF OPERATIONS.
            case MULTIPLY:
                cursor.setB(cursor.getB() * cursor.getNext().getB()); //does the operation
                completeCalculate(); //removes a node
                exists = true; //exists set to true so that it will check the same node again for another operator
                break;
            case DIVIDE:
                cursor.setB(cursor.getB() / cursor.getNext().getB()); //same as above
                completeCalculate();
                exists = true;
                break;
            case MODULO:
                cursor.setB(cursor.getB() % cursor.getNext().getB()); //same as above
                completeCalculate();
                exists = true;
                break;
            case NULL:
                cursor = brain;
                boolio = false; //if there is no more operators, then ADD and SUBTRACT will be performed next by setting this to false;
            default:
                exists = false; //if operator does not exist, it will make exists false, making the recursive function go to the next element.
        }
    }

    private void as_switch(){

        switch (cursor.getOP()) { //ADD AND SUBTRACT ONLY DONE AFTER MULTIPLY, DIVIDE, AND MODULO
            case ADD:
                cursor.setB(cursor.getB() + cursor.getNext().getB());
                completeCalculate();
                break;
            case SUBTRACT:
                cursor.setB(cursor.getB() - cursor.getNext().getB());
                completeCalculate();
                break;
            case NULL:
                cursor = brain; //brings it back to the beginning;
                break;
            default:
                exists2 = false; //If the operator is not found, it will make exists2 false, making the recursive function go to the next element
        }
    }

    public void mergeFrame(Mainframe mainframe){ //merges the next value of the cursor of both frames. !!ONLY SHOULD BE USED AFTER BOTH HAVE BEEN CALCULATED WITH calculate()!!
        cursor.setNext(mainframe.cursor.getNext());
    }


    private void completeCalculate(){ //after an operation is executed, it removes the next operator from the brain, making it smaller until there is only one element left (the answer).
        cursor.setOP(cursor.getNext().getOP()); //transfers over the next operator to the one doing the operation
        cursor.setNext(cursor.getNext().getNext()); //removes the next brain from the mainframe, sets this brain equal to the next one over (which may or may not be null)
    }


}
