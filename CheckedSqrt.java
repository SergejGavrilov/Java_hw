package expression;

/**
 * Created by 808648 on 04.04.2016.
 */
public class CheckedSqrt extends CheckedAbstractUnaryExpression {

    public CheckedSqrt(TripleExpression expression) {
        super(expression);
    }

    protected int action(int value) {
        return (int)(Math.sqrt((double) value));
    }

}
