package matrices;

import java.util.Arrays;

public class Matrix extends Matrices {

    final double[][] theMatrix;
    final int length;
    final int width;

    public Matrix(double[][] mat) {
        theMatrix = mat;
        length = mat.length;
        width = mat[0].length;
    }

    public Matrix(int length, int width) {
        theMatrix = new double[length][width];

        this.length = length;
        this.width = width;
    }

    public Matrix(int length, int width, double fill) {
        theMatrix = new double[length][width];
        Arrays.fill(theMatrix, fill);

        this.length = length;
        this.width = width;
    }

    @Override
    public void addEqual(Matrices m) {
        if (m.length() != this.length() || m.width() != this.width()) {
            throw new IllegalArgumentException("Matrix length/width mismatch");
        }

        for (int l = 0; l < m.length(); ++l) {
            for (int w = 0; w < m.length(); ++w) {
                this.set(l, w, this.v(l, w) + m.v(l, w));
            }
        }
    }

    @Override
    public void subtractEqual(Matrices m) {
        if (m.length() != this.length() || m.width() != this.width()) {
            throw new IllegalArgumentException("Matrix length/width mismatch");
        }
        for (int l = 0; l < m.length(); ++l) {
            for (int w = 0; w < m.length(); ++w) {
                this.set(l, w, this.v(l, w) - m.v(l, w));
            }
        }
    }

    @Override
    public void timesEqual(Matrices m) {
        if (m.length() != this.length() || m.width() != this.width()) {
            throw new IllegalArgumentException("Matrix length/width mismatch");
        }
        for (int l = 0; l < m.length(); ++l) {
            for (int w = 0; w < m.length(); ++w) {
                this.set(l, w, this.v(l, w) * m.v(l, w));
            }
        }
    }

    @Override
    public void divideEqual(Matrices m) {
        if (m.length() != this.length() || m.width() != this.width()) {
            throw new IllegalArgumentException("Matrix length/width mismatch");
        }
        for (int l = 0; l < m.length(); ++l) {
            for (int w = 0; w < m.length(); ++w) {
                this.set(l, w, this.v(l, w) / m.v(l, w));
            }
        }
    }

    @Override
    public Matrix add(Matrices m) {
        if (m.length() != this.length() || m.width() != this.width()) {
            throw new IllegalArgumentException("Matrix length/width mismatch");
        }

        double[][] newMatrix = new double[this.length()][this.width()];
        for (int l = 0; l < this.length(); ++l) {
            for (int w = 0; w < this.length(); ++w) {
                newMatrix[l][w] = this.v(l, w) + m.v(l, w);
            }
        }
        return new Matrix(newMatrix);
    }

    @Override
    public Matrix subtract(Matrices m) {
        if (m.length() != this.length() || m.width() != this.width()) {
            throw new IllegalArgumentException("Matrix length/width mismatch");
        }

        double[][] newMatrix = new double[this.length()][this.width()];
        for (int l = 0; l < this.length(); ++l) {
            for (int w = 0; w < this.length(); ++w) {
                newMatrix[l][w] = this.v(l, w) - m.v(l, w);
            }
        }
        return new Matrix(newMatrix);
    }

    @Override
    public Matrix divide(Matrices m) {
        if (m.length() != this.length() || m.width() != this.width()) {
            throw new IllegalArgumentException("Matrix length/width mismatch");
        }

        double[][] newMatrix = new double[this.length()][this.width()];
        for (int l = 0; l < this.length(); ++l) {
            for (int w = 0; w < this.length(); ++w) {
                newMatrix[l][w] = this.v(l, w) / m.v(l, w);
            }
        }
        return new Matrix(newMatrix);
    }

    @Override
    public Matrix multiply(Matrices m) {
        if (m.length() != this.length() || m.width() != this.width()) {
            throw new IllegalArgumentException("Matrix length/width mismatch");
        }

        double[][] newMatrix = new double[this.length()][this.width()];
        for (int l = 0; l < this.length(); ++l) {
            for (int w = 0; w < this.length(); ++w) {
                newMatrix[l][w] = this.v(l, w) * m.v(l, w);
            }
        }
        return new Matrix(newMatrix);
    }

    @Override
    public Matrix dotProduct(Matrices m) {
        if (m.length() != this.width() || m.width() != this.length()) {
            throw new IllegalArgumentException("Matrix length/width mismatch");
        }

        double[][] dotP = new double[m.length()][m.length()];
        double tmp;

        for (int l = 0; l < this.length(); ++l) {
            for (int l2 = 0; l2 < m.length(); ++l2) {
                tmp = 0;
                for (int w = 0; w < this.width(); ++w) {
                    tmp += this.v(l, w) * m.v(w, l2);
                }
                dotP[l][l2] = tmp;
            }
        }
        return new Matrix(dotP);
    }

    @Override
    public double v(int l, int w) {
        return theMatrix[l][w];
    }

    @Override
    public boolean isColumnVector() {
        return this.width() == 1;
    }

    @Override
    public boolean isRowVector() {
        return this.length() == 1;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public int width() {
        return width;
    }

    @Override
    public Matrix Transpose() {
        double[][] t = new double[width][length];
        for (int l = 0; l < length; ++l) {
            for (int w = 0; w < width; ++w) {
                t[w][l] = theMatrix[l][w];
            }
        }
        return new Matrix(t);
    }

    @Override
    public void scale(double number, char oper) {
        for (int l = 0; l < length; ++l) {
            for (int w = 0; w < width; ++w) {
                theMatrix[l][w] = calculate(theMatrix[l][w], oper, number);
            }
        }
    }

    @Override
    public double sum() {
        double total = 0;
        for (int l = 0; l < length; ++l) {
            for (int w = 0; w < width; ++w) {
                total += theMatrix[l][w];
            }
        }
        return total;
    }

    @Override
    public Matrix vectorSum() {
        double[][] vect = new double[this.length][1];
        double total = 0;
        for (int l = 0; l < length; ++l) {
            total = 0;
            for (int w = 0; w < width; ++w) {
                total += theMatrix[l][w];
            }
            vect[l][0] = total;
        }
        return new Matrix(vect);
    }

    @Override
    public Matrix vectorSumRow() {
        double[][] vect = new double[1][this.width];
        double total = 0;
        for (int w = 0; w < width; ++w) {
            total = 0;
            for (int l = 0; l < length; ++l) {
                total += theMatrix[l][w];
            }
            vect[1][w] = total;
        }
        return new Matrix(vect);
    }

    @Override
    public void set(int l, int w, double value) {
        theMatrix[l][w] = value;
    }

    private static double calculate(double n1, char op, double n2) {
        switch (op) {
            case '^':
                return Math.pow(n1, n2);
            case '*':
                return n1 * n2;
            case '/':
                return n1 / n2;
            case '+':
                return n1 + n2;
            case '-':
                return n1 - n2;
            default:
                throw new IllegalArgumentException("invalid operator - valid operaters are ^, *, /, +, -");
        }
    }

    private static void excpt() {
        throw new IllegalArgumentException("Matrix length/width mismatch");
    }

}
