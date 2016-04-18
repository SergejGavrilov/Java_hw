package expression;

/**
 * Created by 808648 on 27.03.2016.
 */
public abstract class CheckedAbstractExpression implements TripleExpression {
    private final TripleExpression first, second;

    public CheckedAbstractExpression(TripleExpression x, TripleExpression y) {
        first = x;
        second = y;
    }

    protected abstract int action(int firstOperand, int secondOperand);

    public int evaluate(int x, int y, int z) {
        return action(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }
}

