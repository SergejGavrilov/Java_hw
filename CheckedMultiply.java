package expression;

/**
 * Created by 808648 on 21.03.2016.
 */
public class CheckedMultiply extends CheckedAbstractExpression {

    public CheckedMultiply(TripleExpression x, TripleExpression y) {
        super(x, y);
    }

    protected int action(int firstOperand, int secondOperand) {
        if (firstOperand > 0 && (secondOperand > Integer.MAX_VALUE / firstOperand
                || secondOperand < Integer.MIN_VALUE / firstOperand)) {
            throw new RuntimeException(" Overflow");
        }else if (firstOperand < -1 && (secondOperand > Integer.MIN_VALUE / firstOperand
                || secondOperand < Integer.MAX_VALUE / firstOperand)) {
            throw new RuntimeException(" Overflow");
        }else if (firstOperand == -1 && secondOperand == Integer.MIN_VALUE){
            throw new RuntimeException(" Overflow");
        }

        return firstOperand * secondOperand;
    }
}
