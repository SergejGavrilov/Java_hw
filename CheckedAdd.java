package expression;

/**
 * Created by 808648 on 21.03.2016.
 */
public class CheckedAdd extends CheckedAbstractExpression {

    public CheckedAdd(TripleExpression x, TripleExpression y) {
        super(x, y);
    }

    protected int action(int firstOperand, int secondOperand) throws RuntimeException {
        if (((firstOperand > 0) && (secondOperand > (Integer.MAX_VALUE - firstOperand))) ||
                ((firstOperand <= 0 && secondOperand < (Integer.MIN_VALUE - firstOperand)))){
            throw new RuntimeException("Overflow");
        }
        return firstOperand + secondOperand;
    }

}
