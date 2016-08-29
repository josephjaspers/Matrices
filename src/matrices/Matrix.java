/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices;

import java.util.ArrayList;

/**
 *
 * @author Joseph
 */
public class Matrix extends Matrices {

    ArrayList<Vector> theMatrix = new ArrayList<>();
    final boolean updateable;
    private int width;

    public Matrix(int length, int width) {
        for (int l = 0; l < length; ++l) {
            theMatrix.add(new Vector(new double[width]));
        }
        this.width = width;
        updateable = false;
    }

    public Matrix(double[][] doubleary) {
        for (int i = 0; i < doubleary.length; ++i) {
            theMatrix.add(new Vector(doubleary[i]));
        }
        this.width = doubleary[0].length;
        updateable = false;
    }

    public Matrix(int l, int w, boolean updateable) {
        for (int i = 0; i < l; ++i) {
            theMatrix.add(new Vector(new double[w]));
        }
        this.updateable = updateable;
    }

    public Matrix(double[][] doubleAry, boolean updateable) {
        for (int i = 0; i < doubleAry.length; ++i) {
            theMatrix.add(new Vector(doubleAry[i]));
        }
        this.updateable = updateable;
        this.width = doubleAry[0].length;
    }

    public boolean addRow(double[] row) {
        if (row.length != this.width()) {
            throw new IllegalArgumentException("length must match width when adding row");
        }
        if (!updateable) {
            throw new IllegalArgumentException("Not updateable // initialize this matrix as updateable");
            //return false;
        }
        theMatrix.add(new Vector(row));
        return true;
    }

    public boolean isUpdateable() {
        return updateable;
    }

    @Override
    public void addEqual(Matrices m) {
        operandEqualLesser(m, '+');
    }

    @Override
    public void subtractEqual(Matrices m) {
        operandEqualLesser(m, '-');
    }

    @Override
    public void timesEqual(Matrices m) {
        operandEqualGreater(m, '*');
    }

    @Override
    public void divideEqual(Matrices m) {
        operandEqualGreater(m, '/');
    }

    @Override
    public Matrices add(Matrices m) {
        return operandLesser(m, '+');
    }

    @Override
    public Matrices subtract(Matrices m) {
        return operandLesser(m, '-');
    }

    @Override
    public Matrices divide(Matrices m) {
        return operandGreater(m, '/');
    }

    @Override
    public Matrices multiply(Matrices m) {
        return operandGreater(m, '*');
    }

    @Override
    public double v(int x, int y) {
        return theMatrix.get(x).get(y);
    }

    @Override
    public int getColLength() {
        return theMatrix.size();
    }

    @Override
    public int getNumbRows() {
        return width();

    }

    @Override
    public int length() {
        return theMatrix.size();
    }

    @Override
    public int width() {
        return width;
    }

    @Override
    public Matrices Transpose() {
        double[][] matr = new double[this.length()][this.width()];
        double tmp[];
        for (int w = 0; w < this.width(); ++w) {
            tmp = new double[this.length()];
            for (int l = 0; l < this.length(); ++l) {
                tmp[l] = this.v(l, w);
            }
            matr[w] = tmp;
        }
        return new Matrix(matr);
    }

    @Override
    public Matrices T() {
        return Transpose();
    }

    private Matrices operandLesser(Matrices m, char oper) {
        if (m instanceof Vector) {
            if (m.length() != this.width()) {
                throw new IllegalArgumentException("vector length must be equal to matrix width");
            }
            double[][] newMatrix = new double[this.length()][this.width()];
            Vector vec = (Vector) m;
            for (int l = 0; l < this.length(); ++l) {
                for (int w = 0; w < this.width(); ++w) {
                    newMatrix[l][w] = calculate(this.v(l, w), oper, vec.get(w));
                }
            }
            return new Matrix(newMatrix);
        } else if (m.length() != this.length() || m.width() != this.width()) {
            throw new IllegalArgumentException("matrices length/width mismatch");
        } else {
            double[][] newM = new double[this.length()][this.width()];
            for (int l = 0; l < this.length(); ++l) {
                for (int w = 0; w < this.width(); ++w) {
                    newM[l][w] = calculate(this.v(l, w), oper, m.v(l, w));
                }
            }
            return new Matrix(newM);
        }
    }

    private Matrices operandGreater(Matrices m, char oper) {
        if (m instanceof Vector) {
            if (m.length() != this.width()) {
                throw new IllegalArgumentException("vector length must be equal to matrix width");
            }
            double[][] newMatrix = new double[this.length()][this.width()];
            Vector vec = (Vector) m;
            for (int l = 0; l < this.length(); ++l) {
                for (int w = 0; w < this.width(); ++w) {
                    newMatrix[l][w] = calculate(this.v(l, w), oper, vec.get(w));
                }
            }
            return new Matrix(newMatrix);
        } else if (m.length() != this.width() || m.width() != this.length()) {
            throw new IllegalArgumentException("matrices length/width mismatch");
        } else {
            double[][] newM = new double[this.length()][this.width()];
            for (int l = 0; l < this.length(); ++l) {
                for (int w = 0; w < this.width(); ++w) {
                    newM[l][w] = calculate(this.v(l, w), oper, m.v(w, l));
                }
            }
            return new Matrix(newM);
        }
    }

    private void operandEqualGreater(Matrices m, char oper) {
        if (m instanceof Vector) {
            if (m.length() != this.width()) {
                throw new IllegalArgumentException("vector length must be equal");
            }
            Vector vec = (Vector) m;
            for (int l = 0; l < this.length(); ++l) {
                for (int w = 0; w < this.width(); ++w) {
                    theMatrix.get(l).set(w, calculate(this.v(l, w), oper, vec.get(w)));
                }
            }
        } else if (m.width() != this.width() || m.length() != this.width()) {
            throw new IllegalArgumentException("matrices length/width mismatch");
        } else {
            for (int l = 0; l < m.length(); ++l) {
                for (int w = 0; w < m.width(); ++l) {
                    theMatrix.get(l).set(w, calculate(this.v(l, w), oper, m.v(w, l)));
                }
            }
        }
    }

    private void operandEqualLesser(Matrices m, char oper) {
        if (m instanceof Vector) {
            if (m.length() != this.width()) {
                throw new IllegalArgumentException("vector length must be equal");
            }
            Vector vec = (Vector) m;
            for (int l = 0; l < this.length(); ++l) {
                for (int w = 0; w < this.width(); ++w) {
                    theMatrix.get(l).set(w, calculate(this.v(l, w), oper, vec.get(w)));
                }
            }
        } else if (m.width() != this.width() || m.length() != this.length()) {
            throw new IllegalArgumentException("matrices length/width mismatch");
        } else {
            for (int l = 0; l < m.length(); ++l) {
                for (int w = 0; w < m.width(); ++l) {
                    theMatrix.get(l).set(w, calculate(this.v(l, w), oper, m.v(l, w)));
                }
            }
        }
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
        for (int l = 0; l < length(); ++l) {
            for (int w = 0; w < width(); ++w) {
                theMatrix.get(l).set(w, calculate(theMatrix.get(l).get(w), oper, number));
            }
        }
    }

    @Override
    public double sum() {
        double total = 0;
        for (int l = 0; l < length(); ++l) {
            for (int w = 0; w < width(); ++w) {
                total += this.v(l, w);
            }
        }
        return total;
    }

    public void set(int x, int y, double val) {
        theMatrix.get(x).set(y, val);
    }

    public Vector vectorSum() {
        //return a vector that is the sum of all its widths
        double tmp;
        double[] newVec = new double[theMatrix.size()];
        for (int i = 0; i < theMatrix.size(); ++i) {
            tmp = 0;
            for (int j = 0; j < theMatrix.get(i).length(); ++j) {
                tmp += theMatrix.get(i).get(j);
            }
            newVec[i] = tmp;
        }
        return new Vector(newVec);
    }

    public void print() {
        for (int i = 0; i < length(); ++i) {
            for (int j = 0; j < width(); ++j) {
                System.out.print(theMatrix.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}
