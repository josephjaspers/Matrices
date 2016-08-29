/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices;

import java.util.Arrays;

/**
 *
 * @author Joseph
 */
public class Vector extends Matrices {

    private final double[] theVect;

    public Vector(int length) {
        theVect = new double[length];
    }

    public Vector(double[] ary) {
        theVect = ary;
    }

    public Vector(int length, double fill) {
        theVect = new double[length];
        Arrays.fill(theVect, fill);
    }

    public double get(int index) {
        return theVect[index];
    }

    public void set(int index, double s) {
        theVect[index] = s;
    }

    @Override
    public double v(int x, int y) {
        if (x != 0) {
            throw new IllegalArgumentException("Vector's width is always 0");
        } else {
            return theVect[y];
        }
    }

    @Override
    public int getColLength() {
        return theVect.length;
    }

    @Override
    public int getNumbRows() {
        return 0;
    }

    @Override
    public int length() {
        return theVect.length;
    }

    @Override
    public int width() {
        return 0;
    }

    @Override
    public Matrices Transpose() {
        double[][] matr = new double[1][this.length()];
        matr[0] = theVect;
        return new Matrix(matr);
    }

    @Override
    public Matrices T() {
        return Transpose();
    }

    @Override
    public void addEqual(Matrices m) {
        operEqual(m, '+');
    }

    @Override
    public void subtractEqual(Matrices m) {
        operEqual(m, '-');
    }

    @Override
    public void timesEqual(Matrices m) {
        operEqual(m, '*');
    }

    @Override
    public void divideEqual(Matrices m) {
        operEqual(m, '/');
    }

    @Override
    public Matrices add(Matrices m) {
        return operand(m, '+');
    }

    @Override
    public Matrices subtract(Matrices m) {
        return operand(m, '-');
    }

    @Override
    public Matrices divide(Matrices m) {
        return operand(m, '/');
    }

    @Override
    public Matrices multiply(Matrices m) {
        return operand(m, '*');
    }

    private void operEqual(Matrices m, char oper) {
        if (m instanceof Vector) {
            if (m.length() != this.length()) {
                throw new IllegalArgumentException("vector length must be equal");
            }
            Vector obj = (Vector) m;
            for (int i = 0; i < m.length(); ++i) {
                theVect[i] = calculate(theVect[i], oper, obj.get(i));
            }
        } else {
            throw new IllegalArgumentException("evaluating matrix to vector --> may only add/subtraact/etc vector to matrix");
        }
    }

    private Matrices operand(Matrices m, char oper) {
        if (m instanceof Vector) {
            if (m.length() != this.length()) {
                throw new IllegalArgumentException("vector length must be equal");
            }
            double[] vec = new double[m.length()];
            Vector obj = (Vector) m;
            for (int i = 0; i < m.length(); ++i) {
                vec[i] = calculate(theVect[i], oper, obj.get(i));
            }
            return new Vector(vec);
        } else if (this.length() != m.width()) {
            throw new IllegalArgumentException("vector matrice length/width mismatch");
        }
        double[][] vec = new double[m.length()][m.width()];
        for (int l = 0; l < m.length(); ++l) {
            for (int w = 0; w < m.width(); ++w) {
                vec[l][w] = calculate(this.get(w), oper, m.v(l, w));
            }
        }
        return new Matrix(vec);
    }

    private static double calculate(double n1, char oper, double n2) {
        switch (oper) {
            case '*':
                return n1 * n2;
            case '^':
                return Math.pow(n1, n2);
            case '/':
                return n1 / n2;
            case '+':
                return n1 + n2;
            case '-':
                return n1 - n2;
            default:
                throw new IllegalArgumentException("non valid operand, acceptable operands are: *, ^, /, + , -");
        }
    }

    @Override
    public void scale(double number, char oper) {
        for (int i = 0; i < theVect.length; ++i) {
            theVect[i] = calculate(theVect[i], oper, number);
        }
    }

    @Override
    public double sum() {
        double total = 0;

        for (int i = 0; i < length(); ++i) {
            total += theVect[i];
        }
        return total;
    }

    @Override
    public Vector vectorSum() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
