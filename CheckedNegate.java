package expression;

/**
 * Created by 808648 on 03.04.2016.
 */
public class CheckedNegate extends CheckedAbstractUnaryExpression {

    public CheckedNegate(TripleExpression expression){
        super(expression);
    }

    public int action(int value) {
        if (value == Integer.MIN_VALUE){
            throw new RuntimeException(" Overflow");
        }
        return -value;
    }
}
