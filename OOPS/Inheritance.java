

/*
* The diamond problem occurs in multiple inheritance when:
     A class inherits from two classes
     Both parent classes inherit from the same base class
     And both override the same method

   In case of classes, java does not allow multiple inheritance, so the diamond problem is not a concern.
   But when we use interfaces with default methods, java allows multiple inheritance, so the diamond problem can occur.
   To avoid this you need to override the method and provide your own implementation.
   In general, you do NOT need to override default methods, you only override them when there is ambiguity or you want different behavior.
* */

interface A {
    default void show() {
        System.out.println("A");
    }
}

interface B extends A {
    default void show() {
        System.out.println("B");
    }
}

interface C extends A {
    default void show() {
        System.out.println("C");
    }
}

class D implements B, C {

    @Override
    public void show() {
        B.super.show();
    }
}
public class Inheritance {
    public static void main(String[] args) {
        D d = new D();
        d.show();
    }
}
