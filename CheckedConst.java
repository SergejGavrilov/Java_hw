package expression;

/**
 * Created by 808648 on 21.03.2016.
 */
public class CheckedConst implements TripleExpression {
    private final int value;

    public CheckedConst(int value) {
        this.value = value;
    }

    public int evaluate(int x, int y, int z) {
        return value;
    }
}
