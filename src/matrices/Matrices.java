/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices;

/**
 *
 * @author Joseph //
 */
public abstract class Matrices {

    public abstract void addEqual(Matrices m);

    public abstract void subtractEqual(Matrices m);

    public abstract void timesEqual(Matrices m);

    public abstract void divideEqual(Matrices m);

    public abstract Matrix add(Matrices m);

    public abstract Matrix subtract(Matrices m);

    public abstract Matrix divide(Matrices m);

    public abstract Matrix multiply(Matrices m);

    public abstract Matrix dotProduct(Matrices m);

    public abstract double v(int x, int y);

    public abstract boolean isColumnVector();

    public abstract boolean isRowVector();

    public abstract int length();

    public int numbRows() {
        return length();
    }

    public abstract int width();

    public int numbColumns() {
        return width();
    }

    public abstract Matrix Transpose();

    public Matrix T() {
        return Transpose();
    }

    public abstract void scale(double number, char oper);

    public abstract double sum();

    public abstract Matrix vectorSum();

    public abstract Matrix vectorSumRow();

    public abstract void set(int l, int w, double value);

    public static void main(String[] args) {
        Matrix x = new Matrix(4, 2);
        x.set(0, 0, 1);
        x.set(0, 1, 2);
        x.set(1, 0, 3);
        x.set(1, 1, 4);
        x.set(2, 0, 5);
        x.set(2, 1, 6);
        x.set(3, 0, 7);
        x.set(3, 1, 8);
        System.out.println("printing matrix x");
        x.print();

        Matrix y = new Matrix(2, 4);
        y.set(1, 0, 6);
        y.set(1, 1, 5);
        y.set(0, 2, 4);
        y.set(0, 3, 3);
        y.set(1, 0, 2);
        y.set(1, 3, 1);
        y.set(0, 2, 0);
        y.set(0, 1, -1);
        System.out.println("matrix y");
        y.print();

        Matrix xy = (Matrix) x.dotProduct(y);
        System.out.println("xy (dot product)");
        xy.print();

        System.out.println("matrix y(T)");
        y.T().print();

    }

}
