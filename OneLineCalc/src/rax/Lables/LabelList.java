package rax.Lables;

public class LabelList {
    LabelNode root;
    LabelNode cursor;

    LabelList(){
        root = new LabelNode();
        cursor = root;
    }

    public void append(String label, String value){
        while (cursor.getNext() != null)
            cursor.setNext(cursor.getNext()); //advances cursor to the end of the list

        cursor.setNext(new LabelNode(label, value));
    }

    public void redefine(String label, String value){
        cursor = root;
        while(cursor.getNext() != null || cursor.getLabel().equals(label))
            cursor.setNext(cursor.getNext());

        cursor.setValue(value);
    }

    public void rename(String label, String newLabel){
        cursor = root;
        while(cursor.getNext() != null || cursor.getLabel().equals(label))
            cursor.setNext(cursor.getNext());

        cursor.setLabel(newLabel);
    }

    public void remove(String label){
        cursor = root;
        while(cursor.getNext() != null){
            if (cursor.getNext().getLabel().equals(label)){
                cursor.setNext(cursor.getNext().getNext()); //removes a labelNode from the list by having nothing pointing to it
            }
            cursor.setNext(cursor.getNext());
        }



    }
}
