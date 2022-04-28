package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("Anna");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(3, 5);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

    Point p = new Point(-5, 3.1, 3, -2.5);
    System.out.println("Расстояние между точкой (" + p.x1 + ", " + p.y1 + ") и (" + p.x2 + ", " + p.y2 + ") = " + p.distance());
  }

  public static void hello(String somebody) {
    System.out.println("Hello " + somebody);
  }

}