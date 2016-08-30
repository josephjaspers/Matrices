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

    public Matrices T() {
        return Transpose();
    }

    public abstract void scale(double number, char oper);

    public abstract double sum();

    public abstract Matrix vectorSum();
    public abstract Matrix vectorSumRow();
    
    public abstract void set(int l, int w, double value);

    public static void main(String[] args) {
    }

}
