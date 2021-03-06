/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices;

/**
 *
 * @author Joseph
 */
public class Vector extends Matrices {

    final int width = 1;
    final int length;

    final double[] vect;

    public Vector(int length) {
        this.length = length;
        vect = new double[length];
    }

    public Vector(double[] vect) {
        this.vect = vect;
        length = vect.length;
    }

    @Override
    public void addEqual(Matrices m) {
        if (this.length != m.length() || this.width != m.width()) {
            throw new IllegalArgumentException("width / length mismatch");
        }
        for (int i = 0; i < m.length(); ++i) {
            vect[i] += m.v(i, 0);
        }
    }

    @Override
    public void subtractEqual(Matrices m) {
        if (this.length != m.length() || this.width != m.width()) {
            throw new IllegalArgumentException("width / length mismatch");
        }
        for (int i = 0; i < m.length(); ++i) {
            vect[i] -= m.v(i, 0);
        }
    }

    @Override
    public void timesEqual(Matrices m) {
        if (this.length != m.length() || this.width != m.width()) {
            throw new IllegalArgumentException("width / length mismatch");
        }
        for (int i = 0; i < m.length(); ++i) {
            vect[i] *= m.v(i, 0);
        }
    }

    @Override
    public void divideEqual(Matrices m) {
        if (this.length != m.length() || this.width != m.width()) {
            throw new IllegalArgumentException("width / length mismatch");
        }
        for (int i = 0; i < m.length(); ++i) {
            vect[i] /= m.v(i, 0);
        }
    }

    @Override
    public Matrices add(Matrices m) {
        if (this.length != m.length() || this.width != m.width()) {
            throw new IllegalArgumentException("width / length mismatch");
        }
        double[] newV = new double[length];
        for (int i = 0; i < m.length(); ++i) {
            newV[i] = vect[i] + m.v(i, 0);
        }
        return new Vector(newV);
    }

    @Override
    public Matrices subtract(Matrices m) {
        if (this.length != m.length() || this.width != m.width()) {
            throw new IllegalArgumentException("width / length mismatch");
        }
        double[] newV = new double[length];
        for (int i = 0; i < m.length(); ++i) {
            newV[i] = vect[i] - m.v(i, 0);
        }
        return new Vector(newV);
    }

    @Override
    public Matrices divide(Matrices m) {
        if (this.length != m.length() || this.width != m.width()) {
            throw new IllegalArgumentException("width / length mismatch");
        }
        double[] newV = new double[length];
        for (int i = 0; i < m.length(); ++i) {
            newV[i] = vect[i] / m.v(i, 0);
        }
        return new Vector(newV);
    }

    @Override
    public Matrices multiply(Matrices m) {
        if (this.length != m.length() || this.width != m.width()) {
            throw new IllegalArgumentException("width / length mismatch");
        }
        double[] newV = new double[length];
        for (int i = 0; i < m.length(); ++i) {
            newV[i] = vect[i] * m.v(i, 0);
        }
        return new Vector(newV);
    }

    @Override
    public Matrices dotProduct(Matrices m) {
        if (m.length() != this.width()) {
            throw new IllegalArgumentException("Matrix length/width mismatch");
        }

        double[][] dotP = new double[m.width()][m.width()];
        double tmp;

        for (int l = 0; l < this.length; ++l) {
            for (int w = 0; w < m.width(); ++w) {
                tmp = 0;
                for (int l2 = 0; l2 < m.length(); ++l2) {
                    tmp += v(l, l2) * m.v(l2, w);
                }
                dotP[l][w] = tmp;
            }
        }
        return new Matrix(dotP);
    }

    @Override
    public double v(int x, int y) {
        if (y != 0) {
            throw new IndexOutOfBoundsException("vectors do not have any width, y must be 0");
        }
        return vect[x];
    }

    @Override
    public boolean isColumnVector() {
        return true;
    }

    @Override
    public boolean isRowVector() {
        return false;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public int width() {
        return 1;
    }

    @Override
    public Matrix Transpose() {
        double[][] mat = new double[1][length];

        for (int i = 0; i < length; ++i) {
            mat[0][i] = vect[i];
        }
        return new Matrix(mat);
    }

    @Override
    public void scale(double number, char oper) {
        for (int i = 0; i < length; ++i) {
            vect[i] = calculate(vect[i], oper, number);
        }
    }

    @Override
    public double sum() {
        double sum = 0;
        for (int i = 0; i < length; ++i) {
            sum += vect[i];
        }
        return sum;
    }

    @Override
    public Vector vectorSum() {
        return new Vector(vect);
    }

    @Override
    public Vector vectorSumRow() {
        return new Vector(new double[]{sum()});
    }

    @Override
    public void set(int l, int w, double value) {
        if (w != 0) {
            throw new IndexOutOfBoundsException("vectors width is always equal to 0");
        }
        vect[l] = value;
    }

    private void chkDim(Matrices m) {
        if (this.length != m.length() || this.width != m.width()) {
            throw new IllegalArgumentException("width / length mismatch");
        }
    }

    @Override
    public void print() {
        for (int i = 0; i < length; ++i) {
            System.out.println(vect[i]);
        }
    }

}
