
/*
* 1. A class can have multiple constructors through overloading, but they must differ in parameter lists.
  2. Constructors can call other constructors in the same class using this().
  3. Constructors can call parent class constructors using super() in java.
  4. Always use constructors to ensure objects are in a consistent and valid state.
  5. Utilize copy constructors carefully to avoid shallow copying when deep copying is required.
  6. Leverage private constructors for Singleton patterns or utility classes.
* */
class Animal{
    public Animal(){
        System.out.println("Animal is created");
    }
}

class Dog extends  Animal{
    public  Dog(){
        // implicit super -> constructor chaining
        System.out.println("Dog is created");
    }
}
public class Constructor {
    public static void main(String[] args) {
        Dog d = new Dog();
    }
}

/*
* 1. Can a constructor be final, static, or abstract? Why or why not?
     No, constructors cannot be final, static, or abstract because:
    • final: A constructor cannot be inherited, and final is used to prevent overriding — which doesn’t apply to constructors.
    • static: Constructors belong to objects, not the class itself.
    • abstract: A constructor must be concrete as it initializes an object.

  2. What happens if a constructor is synchronized?
     A synchronized constructor is allowed but makes no sense,
     as object-level synchronization is not applicable before the object is fully created.

  3. Can a constructor have a return statement?
     No, constructors cannot return a value, but they can have a return statement to exit early (without a value).
* */
