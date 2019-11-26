public class BigBrain {
    private BigBrain prevBrain;
    private BigBrain nextBrain;
    private Double brain;
    private OP operator;

    BigBrain(){ //creates an empty brain
        this.prevBrain = null;
        this.nextBrain = null;
        this.brain = null;
        this.operator = null;
    }

    BigBrain(Double brain){ //not sure why this would be used.
        this.brain = brain;
        prevBrain = null;
        nextBrain = null;
        operator = OP.NULL;

    }

    BigBrain(Double brain, OP operator){
        this.brain = brain;
        this.operator = operator;
        prevBrain = null;
        nextBrain = null;
    }

    BigBrain(Double brain, BigBrain prevBrain){
        operator = OP.NULL;
        this.brain = brain;
        this.prevBrain = prevBrain;
        this.nextBrain = null;
        prevBrain.nextBrain = this;
    }

    BigBrain(Double brain, OP operator, BigBrain prevBrain){
        this.brain = brain;
        this.operator = operator;
        this.prevBrain = prevBrain;
        nextBrain = null;
    }

    BigBrain(Double brain, OP operator, BigBrain prevBrain, BigBrain nextBrain){
        this.brain = brain;
        this.operator = operator;
        this.prevBrain = prevBrain;
        this.nextBrain = nextBrain;
    }

    void setNext(BigBrain brain){
        if (nextBrain != null){
            nextBrain.prevBrain = null; //severs the link from the nextbrain
            nextBrain.prevBrain = this;
        }
        nextBrain = brain;

    }

    void setPrev(BigBrain brain){
        if (prevBrain != null)
            prevBrain.nextBrain = null; //severs the link
        prevBrain = brain;
        prevBrain.nextBrain = this;
    }

    void setB(Double brainValue){ //sets the value of brain
        brain = brainValue;
    }

    void setOP(OP operator){ //sets the operation
        this.operator = operator;
    }

    Double getB(){
        return brain;
    }

    OP getOP(){
        return operator;
    }

    BigBrain getNext(){
        return nextBrain;
    }

    BigBrain getPrev(){
        return prevBrain;
    }


}
