public class LabelList {
    private LabelNode root; //used to keep the root
    private LabelNode cursor; //used to advance and perform operations on the list
    private String runningNames; //used as an efficient means of checking if a label exists.

    LabelList() {
        root = new LabelNode();
        cursor = root;
        runningNames = "";
    }

    public void append(String label, String value) {
        while (cursor.getNext() != null)
            cursor = cursor.getNext(); //advances cursor to the end of the list

        cursor.setNext(new LabelNode(label, value));
        runningNames += label + " ";//used as a seperator
    }

    public void redefine(String label, String value) { //redefines a label
        cursor = root;
        while (cursor.getNext() != null) {
            if (cursor.getNext().getLabel().equals(label)) //if the next LabelNode's label equals label, it breaks.
                break;
            cursor = cursor.getNext();
        }
        cursor.getNext().setValue(value);
    }

    public void rename(String label, String newLabel) { //renames a label
        cursor = root;
        while (cursor.getNext() != null) { //stops when the next LabelNode either doesnt exist or does with the label.

            if (cursor.getNext().getLabel().equals(label)) { //if the next LabelNode's label equals label
                runningNames = runningNames.replace(label, newLabel);//replaces the label with newLabel (new name) on runningNames
                cursor.getNext().setLabel(newLabel);//replaces label on linkedList with new label
                break;
            }
            cursor = cursor.getNext(); //goes through the LabelList if the label has not been found.
        }

    }

    public void remove(String label) {
        cursor = root;
        while (cursor.getNext() != null) { //stops when the next LabelNode doesnt exist or does with the label.
            if (cursor.getNext().getLabel().equals(label)) { //if the next LabelNode's label equals label
                cursor.setNext(cursor.getNext().getNext()); //removes a labelNode from the list by having nothing pointing to it
                runningNames = runningNames.replace(label, "");//removes a label from runningNames.
                cursor.setNext(cursor.getNext());
            }
            cursor = cursor.getNext(); //goes through the LabelList
        }
    }

    public String getLabelValue(String labelName) { //returns value of label.
        cursor = root;
        while (cursor.getNext() != null) {
            if (cursor.getNext().getLabel().equals(labelName))
                return cursor.getNext().getValue();
            cursor = cursor.getNext(); //goes to the next label, if it exists
        }
        return null; //returns null if label is not found (which means that this method should not have been called)
    }

    String[] getLabels() { //gets all labels and returns them as a String[]
        return runningNames.split(" ");//returns values in runningNames as a String[]
    }

    public boolean containsLabel(String name) { // T or F depending if a label exists
        if (runningNames.contains(name))
            return true;
        return false;
    }


}
