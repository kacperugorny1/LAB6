package org.Balls;

public class Vector {
    public double x;
    public double y;
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Vector() {
        this.x = 0;
        this.y = 0;
    }
    public Vector(Vector v) {
        this.x = v.x;
        this.y = v.y;
    }

    public double scalarMultiplication(Vector v){
        return this.x * v.x + this.y * v.y;
    }
    public Vector subtract(Vector v){
        return new Vector(this.x - v.x, this.y - v.y);
    }
    public Vector add(Vector v){
        return new Vector(this.x + v.x, this.y + v.y);
    }
    public double normSquare(){
        return x * x + y * y;
    }
    public Vector divide(double n){
        return new Vector(x / n, y / n);
    }
    public Vector multiply(double n){
        return new Vector(x * n, y * n);
    }



}
