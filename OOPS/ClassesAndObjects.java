
/*
* Why Use Classes and Objects?
   1. Reusability: Write a class once and create multiple objects with different data.
   2. Modularity: Classes help organize code into logical sections, making it easier to debug and maintain.
   3. Abstraction: Focus on the essential details of an entity without worrying about the internal workings.
   4. Scalability: Adding new features is straightforward without affecting existing code.
* */

class Student{
    int studentId;  // instance variable
    int age;

    public Student(int studentId, int age){
        this.studentId = studentId;
        this.age = age;
    }

    public void changeStudentAge(int age){ // instance method
        this.age = age;
    }

    @Override
    public String toString(){
        return "Student ID is: " + this.studentId + " and Student Age is: " + this.age;
    }
}

public class ClassesAndObjects {
    public static void main(String[] args) {
        Student s1 = new Student(1, 20); // reference variable
        Student s2 = new Student(2, 21);

        System.out.println(s1);
        System.out.println(s2);
    }
}
