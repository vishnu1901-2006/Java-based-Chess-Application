/*Create an abstract class Shape with abstract method area(). 
Extend it in Circle and Rectangle classes. */

abstract class Shape {
    abstract int area(int x, int y);
    abstract double area_circle(double r);
    final double PI = 3.14;
}

class Rectangle extends Shape {
    @Override
    int area (int x, int y){
        int calculated_area = x*y; 
        return calculated_area;
    }

    @Override
    double area_circle (double r) {
        // Not applicable for Rectangle, so return 0 or throw an exception
        throw new UnsupportedOperationException("Not applicable for Rectangle");
    }
}

class Circle extends Shape {
    @Override
    double area_circle (double r) {
        double calculated_area = PI*r*r;
        return calculated_area;
    }

    @Override
    int area (int x, int y) {
        // Not applicable for Circle, so return 0 or throw an exception
        throw new UnsupportedOperationException("Not applicable for Circle.");
    }
    
}

public class AbstractDemo {
    public static void main(String[] args) {
        System.out.println("Let's find the area of circle and rectangle:");

        Rectangle rec = new Rectangle();
        System.out.println("Area of the rectangle = " + rec.area(12,15));

        Circle cir = new Circle();
        System.out.println("Area of the circle = " + cir.area_circle(4.9));
    }
}