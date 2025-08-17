package net.kiranatos.e03generics;

import java.util.ArrayList;

class B1<T> {
    public <T> void print(T ob) {
        T t = (T) ob;
        System.out.println(t + " : " + t.getClass().getSimpleName());        
    }
}

class B2<T> {
    public <N> void print(N ob) {
        T t = (T) ob;
        System.out.println(t + " : " + t.getClass().getSimpleName());        
    }
}

class B3<N> {
    public <T> void print(T ob) {
        T t = (T) ob;
        System.out.println(t + " : " + t.getClass().getSimpleName());        
    }
}

class B4<T> {
    public <T> void print(Object ob) {
        T t = (T) ob;
        System.out.println(t + " : " + t.getClass().getSimpleName());        
    }
}

class B5<T> {
    public void print(Object ob) {
        T t = (T) ob;
        System.out.println(t + " : " + t.getClass().getSimpleName());        
    }
}

class Example {
    public static void main(String[] args) {
        B1<Integer> b1 = new B1<>();
        b1.print("text");
        b1.print(20);
        b1.print(true);
        
        B2<Integer> b2 = new B2<>();
        b2.print("text");
        b2.print(20);
        b2.print(true);
        
        B3<Integer> b3 = new B3<>();
        b3.print("text");
        b3.print(20);
        b3.print(true);
        
        B4<Integer> b4 = new B4<>();
        b4.<Boolean>print("text");
        b4.print(20);
        b4.<Boolean>print(true);
        
        B5<Integer> b5 = new B5<>();
        b5.<Boolean>print("text");
        b5.print(20);
        b5.<Boolean>print(true);
        
        ArrayList<String> list = new ArrayList<>();
        list.add("Java");
        // list.add(20); <- Error
        // list.add(false); <- Error
    }
}