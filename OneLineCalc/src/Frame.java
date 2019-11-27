public class Frame {
    private Frame prevBrain;
    private Frame nextBrain;
    private Double brain;
    private OP operator;

    Frame(){ //creates an empty brain
        this.prevBrain = null;
        this.nextBrain = null;
        this.brain = null;
        this.operator = null;
    }

    Frame(Double brain){ //not sure why this would be used.
        this.brain = brain;
        prevBrain = null;
        nextBrain = null;
        operator = OP.NULL;

    }

    Frame(Double brain, OP operator){
        this.brain = brain;
        this.operator = operator;
        prevBrain = null;
        nextBrain = null;
    }

    Frame(Double brain, Frame prevBrain){
        operator = OP.NULL;
        this.brain = brain;
        this.prevBrain = prevBrain;
        this.nextBrain = null;
        prevBrain.nextBrain = this;
    }

    Frame(Double brain, OP operator, Frame prevBrain){
        this.brain = brain;
        this.operator = operator;
        this.prevBrain = prevBrain;
        this.nextBrain = null;

    }

    Frame(Double brain, OP operator, Frame prevBrain, Frame nextBrain){
        this.brain = brain;
        this.operator = operator;
        this.prevBrain = prevBrain;
        this.nextBrain = nextBrain;
    }

    void setNext(Frame brain){
        if (nextBrain != null){
            nextBrain.prevBrain = null; //severs the link from the nextbrain
            nextBrain.prevBrain = this;
        }
        nextBrain = brain;

    }

    void setPrev(Frame brain){
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

    Frame getNext(){
        return nextBrain;
    }


    Frame getPrev(){
        return prevBrain;
    }


}
