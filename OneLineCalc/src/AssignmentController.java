public class AssignmentController {
    private LabelList list;
    private ListValues values;

    AssignmentController() {
        list = new LabelList();
        values = new ListValues();
    }

    public boolean isAssignment(String line) {
        if (line.contains(":=") || line.contains("->"))
            return true;
        return false;
    }

    public boolean labelAssignment(String line) { //checks if line wishes to assign a value. If it does, then it returns true and adds a label into memory
        if (line.contains(":=")) //the assignment operator
        {
            line = line.replaceAll(" ", "");//removes all spaces between values
            String[] lines = line.split(":="); //splits at this assignment operator
            list.append(lines[0], lines[1]);
            return true;
        }

        if (line.contains("->")) { //the redefine operator
            line = line.replaceAll(" ", ""); //removes all spaces
            String[] lines = line.split("->");
            list.redefine(lines[0], lines[1]);
            return true;
        }
        return false;
    }

    public boolean containsLabels(String line) {//checks if labels are present in the line, returns TRUE if yes.
        String[] labels = list.getLabels(); //returns label names
        for (int i = 0; i < labels.length; i++) { //iteratively searches if the line contains assigned lables
            if (line.contains(labels[i]) && !labels[i].equals(""))
                return true;
        }
        return false;
    }

    public String replaceLabels(String line) { //replaces all labels (if they exist in the line) with their assigned values
        String[] labels = list.getLabels();
        for (String label : labels) {
            if (line.contains(label)) //if the line contains a charSequence with the name of a label
                line = line.replace(label, list.getLabelValue(label)); //it does a nice replacement :)
        }

        return line;
    }

    public boolean deleteLabel(String line) { //removes a label
        //implement here
        return true;
    }


}
