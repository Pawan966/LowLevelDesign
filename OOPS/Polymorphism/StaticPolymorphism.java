package Polymorphism;

class Adder{
    public int add(int a, int b){
        return a + b;
    }
    public int add(int a, int b, int c){
        return a + b + c;
    }
}
public class StaticPolymorphism {
    public static void main(String[] args) {
        Adder adder = new Adder();
        System.out.println(adder.add(1, 2));
        System.out.println(adder.add(1, 2, 3));
    }
}
