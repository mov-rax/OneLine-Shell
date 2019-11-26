import java.util.HashMap;
import java.util.Map;

public class ListValues{
    private HashMap<String, Boolean> numList;
    private HashMap<String, Boolean> opList;
    private HashMap<String, OP> opListOP;

    ListValues(){
        numList = new HashMap<>();
        opList = new HashMap<>();
        opListOP = new HashMap<>();

        numList.put("0", true);
        numList.put("1", true);
        numList.put("2", true);
        numList.put("3", true);
        numList.put("4", true);
        numList.put("5", true);
        numList.put("6", true);
        numList.put("7", true);
        numList.put("8", true);
        numList.put("9", true);
        numList.put(".", true);

        opList.put("*", true);
        opList.put("/", true);
        opList.put("%", true);
        opList.put("-", true);
        opList.put("+", true);

        opListOP.put("*", OP.MULTIPLY);
        opListOP.put("/", OP.DIVIDE);
        opListOP.put("%", OP.MODULO);
        opListOP.put("+", OP.ADD);
        opListOP.put("-", OP.SUBTRACT);

    }

    public Boolean numListContains(char character){ //safely returns numlist bool
        if (numList.get(character + "") != null)
            return true;
        return false;
    }

    public Boolean opListContains(char character){ //safely returns oplist bool
        if (opList.get(character + "") != null)
            return true;
        return false;
    }

    public OP convertToOP(char character){
        if (opListOP.get(character + "") != null)
            return opListOP.get(character + "");
        return OP.NULL;
    }
}
