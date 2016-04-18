package expression;

/**
 * Created by 808648 on 04.04.2016.
 */
public abstract class CheckedAbstractUnaryExpression implements TripleExpression {
    private final TripleExpression expression;

    public CheckedAbstractUnaryExpression(TripleExpression expression) {
        this.expression = expression;
    }

    protected abstract int action(int value);

    public int evaluate(int x, int y, int z) {
        return action(expression.evaluate(x, y, z));
    }


}
