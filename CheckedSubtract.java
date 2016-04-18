package expression;

/**
 * Created by 808648 on 21.03.2016.
 */
public class CheckedSubtract extends CheckedAbstractExpression {

    public CheckedSubtract(TripleExpression x, TripleExpression y) {
        super(x, y);
    }

    protected int action(int firstOperand, int secondOperand) {
        if ((firstOperand >= 0 && secondOperand <= (Integer.MIN_VALUE + firstOperand)) ||
        (firstOperand < 0 && secondOperand > (Integer.MAX_VALUE + firstOperand + 1))){
            throw new RuntimeException(" Overflow");
        };
        return firstOperand - secondOperand;
    }
}
