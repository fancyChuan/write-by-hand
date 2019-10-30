package cn.fancychuan.prepare.reflect;

public class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void say() {
        System.out.println("I am from Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}');
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
