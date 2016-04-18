package expression;

/**
 * Created by 808648 on 04.04.2016.
 */
public class CheckedABS extends CheckedAbstractUnaryExpression {


    public CheckedABS(TripleExpression expression) {
        super(expression);
    }

    protected int action(int value) {
        return (value < 0 ? -value : value);
    }
}
