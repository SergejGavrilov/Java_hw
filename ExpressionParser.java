package expression.exceptions;


import expression.*;

import java.text.ParseException;

/**
 * Created by 808648 on 02.04.2016.
 */
public class ExpressionParser implements Parser {
    private String str;
    private int position = 0;
    Operation curlex;
    int value;
    int balance = 0;

    private enum Operation {
        SUB,
        SQRT,
        ABS,
        PLUS,
        DIV,
        MUL,
        OPEN,
        CLOSE,
        END,
        DIGIT,
        VARX,
        VARY,
        VARZ
    }


    private int getDigit(boolean negative) throws AssertionError {
        String temp = "";
        while ((position < str.length()) && (Character.isDigit(str.charAt(position)))) {
            temp += (str.charAt(position));
            position++;
        }
        position--;
        if (temp.length() == 10) {
            if (negative && temp.compareTo("2147483648") <= 0) {
                return Integer.parseInt("-" + temp);
            }
            if (temp.compareTo("2147483647") <= 0) {
                return Integer.parseInt(temp);
            }
            throw new AssertionError("overflow " + temp);
        } else if (temp.length() > 10) {
            throw new AssertionError("overflow " + temp);
        } else {
            if (negative) {
                return Integer.parseInt("-" + temp);
            } else {
                return Integer.parseInt(temp);
            }
        }
    }

    private void nextlexem(boolean negative) throws ParseException {
        curlex = null;
        while ((position < str.length()) && (str.charAt(position) == ' ')) {
            position++;
        }
        if (position >= str.length()) {
            curlex = Operation.END;
            return;
        }

        if (Character.isDigit(str.charAt(position))) {
            value = getDigit(negative);
            curlex = Operation.DIGIT;
        } else {
            switch (str.charAt(position)) {
                case '(':
                    curlex = Operation.OPEN;
                    break;
                case ')':
                    curlex = Operation.CLOSE;
                    break;
                case '+':
                    curlex = Operation.PLUS;
                    break;
                case '-':
                    curlex = Operation.SUB;
                    break;
                case '*':
                    curlex = Operation.MUL;
                    break;
                case '/':
                    curlex = Operation.DIV;
                    break;
                case 'x':
                    curlex = Operation.VARX;
                    break;
                case 'y':
                    curlex = Operation.VARY;
                    break;
                case 'z':
                    curlex = Operation.VARZ;
                    break;
                case 's':
                    if (position + 3 < str.length() && str.charAt(position + 1) == 'q' && str.charAt(position + 2) == 'r'
                            && str.charAt(position + 3) == 't') {
                        curlex = Operation.SQRT;
                        break;
                    } else {
                        throw new ParseException("Wrong Expression. sqrt expected", position);
                    }
                case 'a':
                    if (position + 2 < str.length() && str.charAt(position + 1) == 'b' &&
                            str.charAt(position + 2) == 's') {
                        curlex = Operation.ABS;
                        break;
                    } else {
                        throw new ArithmeticException("Wrong Expression. abs expected");
                    }
                default:
                    throw new ParseException("Wrong Expression. Unexpected symbol " + str.charAt(position), position);
            }
        }
        if (curlex == Operation.ABS) {
            position += 3;
        } else if (curlex == Operation.SQRT) {
            position += 4;
        } else {
            position++;
        }

        if (curlex == null) {
            curlex = Operation.END;
        }

    }

    private TripleExpression expr()throws ParseException {
        TripleExpression a = item();
        while (curlex == Operation.SUB || curlex == Operation.PLUS) {
            if (curlex == Operation.SUB) {
                nextlexem(false);
                a = new CheckedSubtract(a, item());
            } else if (curlex == Operation.PLUS) {
                nextlexem(false);
                a = new CheckedAdd(a, item());
            }
        }
        return a;
    }

    private TripleExpression item() throws ParseException{
        TripleExpression a = mult();
        while (curlex == Operation.MUL || curlex == Operation.DIV) {
            if (curlex == Operation.MUL) {
                nextlexem(false);
                a = new CheckedMultiply(a, mult());
            } else if (curlex == Operation.DIV) {
                nextlexem(false);
                a = new CheckedDivide(a, mult());
            }
        }
        return a;
    }


    private TripleExpression mult() throws ParseException {
        switch (curlex) {
            case DIGIT:
                nextlexem(false);
                return new CheckedConst(value);

            case OPEN:
                nextlexem(false);
                TripleExpression a = expr();
                if (curlex == Operation.CLOSE) {
                    nextlexem(false);
                    return a;
                } else {
                    System.out.println("error ocurred(brackets expected)");
                    return new Variable("x");
                }

            case SUB:
                nextlexem(true);
                if (curlex == Operation.DIGIT) {
                    nextlexem(false);
                    return new CheckedConst(value);
                }
                return new CheckedNegate(mult());

            case VARX:
                nextlexem(false);
                return new Variable("x");

            case VARY:
                nextlexem(false);
                return new Variable("y");

            case VARZ:
                nextlexem(false);
                return new Variable("z");

            case ABS:
                nextlexem(false);
                return new CheckedABS(mult());

            case SQRT:
                nextlexem(false);
                return new CheckedSqrt(mult());

            default:
                throw new ArithmeticException("Wrong Expression" + str);
        }
    }


    public TripleExpression parse(String expression) throws ArithmeticException{
        str = expression;
        nextlexem(false);
        TripleExpression a = expr();


        if (position < str.length()) {
            throw new ArithmeticException("Parenthesis expected");
        } else {
            return a;
        }

    }
}
