import java.util.HashMap;

public class ListValues{
    private HashMap<String, OP> opListOP;
    private String numList;
    private String opList;
    private String letterList;

    ListValues(){
        opListOP = new HashMap<>();

        numList = "0123456789."; //allowed list of numbers
        opList = "*/%+-"; //allowed list of operators
        letterList = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_-"; //allowed list of characters for usage in labels

        opListOP.put("*", OP.MULTIPLY);
        opListOP.put("/", OP.DIVIDE);
        opListOP.put("%", OP.MODULO);
        opListOP.put("+", OP.ADD);
        opListOP.put("-", OP.SUBTRACT);

    }

    public Boolean numListContains(char character){ //safely returns numlist bool
        if (numList.contains(character + ""))
            return true;
        return false;
    }

    public Boolean opListContains(char character){ //safely returns oplist bool
        if (opList.contains(character + ""))
            return true;
        return false;
    }

    public OP convertToOP(char character){
        if (opListOP.get(character + "") != null)
            return opListOP.get(character + "");
        return OP.NULL;
    }

    public Boolean letterListContains(char character){
        if (letterList.contains(character + ""))
            return true;
        return false;
    }
}
