package net.kiranatos;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {        
        int i = 0;
        while(i++ > 10) { }
        
        String str1 = "one";
        String str2 = new String("one");
        System.out.println(str1==str2);
        double d1  = 201f;
        double d2  = 2f;
        System.out.println(d1 % d2);
        A a = new A("");
        int[] r = new int[]{1,2,3};
        
        new Test().method(r[0]);
        
        System.out.println(r[0]);
    }
    
    public void method(int a) {
        a = 10;
        System.out.println(a);
    }

    }

    /*static String path = "M:\\VK\\";
    public static void main(String[] args) {
        for (String s : OzoHelper.getListOfFiles(path)) {
            System.out.println(s.substring(6));
        }
    }*/

class A {
    public A(String d) {
    }    
}

class B extends A{
    public B(int d) {
        super("");
    }
}