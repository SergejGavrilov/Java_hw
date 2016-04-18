package expression;

/**
 * Created by 808648 on 21.03.2016.
 */
public class Variable implements TripleExpression {
    private final String var;

    public Variable(String var) {
        this.var = var;
    }

    public int evaluate(int x, int y, int z) {
        switch (var) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                return 0;
        }

    }
}
