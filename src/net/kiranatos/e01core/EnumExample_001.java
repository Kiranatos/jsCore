package net.kiranatos.e01core;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
// look info in java.lang.Enum

public class EnumExample_001  {
    public static void main(String[] args) {
        System.out.println("<<< Enum >>> \n"+
                "1) нельзя наследовать: унаследован от Enum и final \n" +
                "2) конструктор private – нельзя создать объекты \n" +
                "3) у каждого объекта есть уникальный номер UP-0, DOWN-1, LEFT-2, RIGHT-3 \n" + 
                "4) все объекты сразу создаются при вызове/инициализации");
        Direction d1 = Direction.LEFT;        
        
        int a = Direction.LEFT.ordinal(); //номер
        System.out.println("Number: " + a);
        
        //масив значений:
        for (Direction d_enum : Direction.values())
            System.out.println(d_enum);
        
        //преобразование
        String s = Direction.LEFT.toString();
        Direction d2 = Direction.valueOf("LEFT");
        
        System.out.print("\n Demonstration: ");
        System.out.println("\n"  + Country.UKRAINE);
        System.out.println(Country.UKRAINE.getCity());
        System.out.println(Country.UKRAINE.name());
        System.out.println(Country.valueOf("SPAIN"));
        System.out.println(Country.values());
        
        System.out.println("\n\n Enum and Functional interfaces: ");
        Figure fig = Figure.SQUARE;
        fig.message("Площа прямоугольника: " + fig.apply(20, 30));
        Figure.TRIANGLE.message("Площа треугольника: " + Figure.TRIANGLE.apply(20, 30));
        
        System.out.println("\n Enum, Лямбда и два функ.интерфейса: ");
        System.out.println(Anime.YAOI.get().apply("Kuutsun", "dere"));
        System.out.println(Anime.YURI.get().apply("Kuutsun", "dere"));  
        
        System.out.println("\n Enum and Abstract method: ");
        System.out.println(Planet.EARTH.getSputnik());
        System.out.println(Planet.MARS.getSputnik());
    }    
}

enum Direction { UP, DOWN, LEFT, RIGHT }
/* Как видит компилятор:
public final class Direction extends Enum implements Comparable<E>, Serializable {
    public static final Direction UP = new Direction();
    public static final Direction DOWN = new Direction();
    public static final Direction LEFT = new Direction();
    public static final Direction RIGHT = new Direction();
    private Direction() { }
}*/

/* При раскомментировании ругается компилятор, нельзя так много противоречивого собирать в одном классе. 
Поэтому этот enum DirectionVariants только для демонстрации возможніх вариантов описания.
По Code Conventions должно писаться большими буквами и с подчёркиванием если два слова: 
enum DirectionVariants { 
    UP(), DOWN(), LEFT, RIGHT, WEST("ozo"), EAST_USA(66);
    private final int a;
    DirectionVariants(String str) { this.a = 99; }
    private DirectionVariants(int size) { this.a = size; }
    public int getSome() { return DirectionVariants.this.ordinal(); }
}*/

enum Country { 
    UKRAINE("Kyiv"), POLAND("Warsaw"), USA("Washington"), SPAIN("Madrid"), FRANCE("Paris");
    private String city;
    private Country(String city) { 
        this.city = city; 
        System.out.print(this.name() + " = " + this.ordinal() + ", "); /* 4) */
    }
    
    public String getCity() { return this.city; }
    public void writeCapital() { 
        switch (this) {
            case UKRAINE: System.out.println("Київ"); break;
            case POLAND: System.out.println("Warsaw"); break;
            default: System.out.println("Earth");
        }
    }
    
    @Override
    public String toString() { return "Можно переопределить .toString, но многие методы final, как например .compareTo (из Comparable)"; }
}

// можно делать потоко-безопасные синглтоны
enum ExampleSingleton { ENUM_SINGLETON; /* fields & methods*/ }

// enum and functional interfaces:
@FunctionalInterface
interface Perimetr{ void message(String str); }

enum Figure implements BinaryOperator<Integer>, Perimetr {
    SQUARE{ // Анонимный класс
        @Override
        public Integer apply(Integer width, Integer height) { return width*height; }
        @Override
        public void message(String str) { System.out.println(str); }
    },
    TRIANGLE{ // Анонимный класс
        @Override
        public Integer apply(Integer base, Integer height) { return (base * height)/2; }
        @Override
        public void message(String str) { System.out.println(str); }
    }
}

// enum and lyambda:
enum Anime implements Supplier<BiFunction>{
    YURI((str1,str2) -> str1 + str2),
    YAOI((str1,str2) -> str2 + str1);
    
    private BiFunction<String, String, String> biFunc;
    
    Anime(BiFunction<String, String, String> biFunc){ // <? extends Number,...
        this.biFunc = biFunc;
    }

    @Override
    public BiFunction<String, String, String> get() {
        return biFunc;
    }
}

// enum, abstract methods and anonymous classes
enum Planet {
    EARTH{
        public String getSputnik() { return "Moon"; }
    }, MARS{
        public String getSputnik() { return "Deimos"; }
    }, JUPITER{
        public String getSputnik() { 
            int a = this.getRadius();
            return "Europa"; 
        }
    }; 
    private int radius = 100;
    public int getRadius() { return radius; }
    public abstract String getSputnik();
}