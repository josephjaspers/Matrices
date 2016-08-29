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

    public abstract Matrices add(Matrices m);

    public abstract Matrices subtract(Matrices m);

    public abstract Matrices divide(Matrices m);

    public abstract Matrices multiply(Matrices m);

    public abstract double v(int x, int y);

    public abstract int getColLength();

    public abstract int getNumbRows();

    public abstract int length();

    public abstract int width();

    public abstract Matrices Transpose();

    public abstract Matrices T();

    public abstract void scale(double number, char oper);

    public abstract double sum();

    public abstract Vector vectorSum();

    public static void main(String[] args) {
    }

}
