package net.kiranatos.e01core;

/* Информация о классe Object 
Clone:
https://metanit.com/java/tutorial/3.13.php
https://habr.com/ru/post/246993/
https://www.geeksforgeeks.org/clone-method-in-java-2/
*/

public class TestObject {
    public static void main(String[] args) {
        
        
        System.out.println("Для массивов переопределен метод clone(), который производит поэлементное копирование.");
        Item[] ar1 = {new Item (10), new Item (20), new Item (30)};
        Item[] ar2 = ar1.clone ();
        ar2[0].item = 4;
        System.out.println (ar1[0].item +", " + ar1[1].item + ", " + ar1[2].item);        
    }
}

class Item implements Cloneable{
    public int item;
    private int id, year;
    private String name;
    Item (int item) { this.item = item; }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        
        //if obj is null, then objects are not equal. The object on which method was called is definitely not null.
        //if the objects are of different classes, then no sense to compare it
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Item itm = (Item)obj;
        
        if (id != itm.id) return false;
        if (year != itm.year) return false;
        
        return name != null ? name.equals(itm.name) : itm.name == null;
    }

    /*
    А) у двух разных объектов может быть одинаковый hashCode 
    (Пример: разные люди(эелементы) могут жить в одном доме(хеш-код-номер))
    Б) у одинаковых объектов (с точки зрения equals) должен быть одинаковый hashCode.
    В) хеш-коды должны быть выбраны таким образом, чтобы не было большого количества 
    различных объектов с одинаковыми hashCode. (Пример: в одном доме живут все люди).
    С) метод equals переопределяется с методом hashCode(). */
    @Override
    public int hashCode() {
        int result = id;
        // 31 -  простое число не делимое на 2
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + year;
        return result;
    }

    /* Нужно:
    а) Добавить интерфейс Cloneable своему классу
    б) Переопределить метод clone и вызвать в нем базовую реализацию:
    При вызове метода clone(), Java проверяет, был ли у объекта интерфейс Cloneable.
    Если да — клонирует объект методом clone(), если нет — выкидывает исключение 
    CloneNotSupportedException.
    Метод clone() в Object объявлен как protected, так что он доступен для вызова только
    классам из его пакета (java.lang.*) или классам-наследникам.    */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
}

