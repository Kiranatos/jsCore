package net.kiranatos.e28reflection;

import java.lang.reflect.Method;
import java.util.List;

public class Unsorted {

    public static void main(String[] args) throws ReflectiveOperationException {
        Class clazz = Integer.class; //	Получение «объект класса» у класса Integer.
        clazz = int.class;  //	Получение «объект класса» у класса int.
        clazz = "123".getClass(); //	Получение «объект класса» у объекта типа String.
        clazz = new Object().getClass(); //	Получение «объект класса» у объекта типа Object.

        Class s1 = int.class;
        String name = s1.getName(); //	Получить имя класса.

        Class s2 = Class.forName("java.lang.String"); // Получить класс по имени.

        Object o1 = String.valueOf(1);
        Object o2 = 123 + "T";
        System.out.println("Сравнить классы у объектов: " + (o1.getClass() == o2.getClass()));

        System.out.println("\nReflection:");
        //Получаем список «объектов класса» для интерфейсов класса List
        Class[] interfaces = List.class.getInterfaces();
        //Получаем «объект класса» родительского класса для класса String        
        Class parent = String.class.getSuperclass();
        //Получаем список методов, которые есть у класса List
        Method[] methods = List.class.getMethods();
        //Создаем новый объект класса String
        String str1 = String.class.newInstance();

        //Получаем метод length у класса String, вызываем его у строки s
        String str2 = String.class.newInstance();
        Method m = String.class.getMethod("length");
        int length = (int) m.invoke(str2);
    }
}
