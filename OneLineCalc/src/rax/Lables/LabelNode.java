package rax.Lables;

public class LabelNode { //gives the user the ability to assign values to labels
    private String label;
    private String value;
    private LabelNode next;

    LabelNode(){
        this.label = null;
        this.value = null;
    }

    LabelNode(String label, String value){
        this.label = label;
        this.value = value;
    }

    LabelNode(String label, String value, LabelNode next){
        this.label = label;
        this.value = value;
    }

    public LabelNode getNext(){
        return next;
    }

    public String getLabel(){
        return label;
    }

    public String getValue(){
        return value;
    }

    public void setNext(LabelNode next){
        this.next = next;
    }

    public void setLabel(String label){
        this.label = label;
    }

    public void setValue(String value){
        this.value = value;
    }

}
