package expression;

/**
 * Created by 808648 on 21.03.2016.
 */
public class CheckedDivide extends CheckedAbstractExpression {

    public CheckedDivide(TripleExpression x, TripleExpression y) {
        super(x, y);
    }

    protected int action(int firstOperand, int secondOperand) throws RuntimeException {
        if (secondOperand == 0) {
            throw new RuntimeException("division by zero");
        } else if (firstOperand == Integer.MIN_VALUE && secondOperand == -1){
            throw new RuntimeException(" Overflow");
        }
            return firstOperand / secondOperand;

    }
}
