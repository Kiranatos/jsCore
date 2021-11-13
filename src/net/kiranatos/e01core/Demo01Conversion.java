package net.kiranatos.e01core;

public class Demo01Conversion {
    
    public static void main(String[] args) {        
        convertByte((byte)100);
        convertChar('c');
        convertShort((short)100);
    }
    
    static void convertByte(byte test) {
        byte b = test;
        char c = (char)test;
        short s = test;
        int i = test;
        long l = test;
        float f = test;
        double d = test;
        
        System.out.println("byte: " + b);
        System.out.println("char: " + c);
        System.out.println("short: " + s);
        System.out.println("int: " + i);
        System.out.println("long: " + l);
        System.out.println("float: " + f);
        System.out.println("double: " + d);        
    }    
    
    static void convertChar(char test) {
        byte b = (byte)test;
        char c = test;
        short s = (short)test;
        int i = test;
        long l = test;
        float f = test;
        double d = test;
        
        System.out.println("byte: " + b);
        System.out.println("char: " + c);
        System.out.println("short: " + s);
        System.out.println("int: " + i);
        System.out.println("long: " + l);
        System.out.println("float: " + f);
        System.out.println("double: " + d);        
    }
    
    static void convertShort(short test) {
        System.out.println("\tto Short:");
        byte b = (byte)test;
        char c = (char)test;
        short s = test;
        int i = test;
        long l = test;
        float f = test;
        double d = test;
        
        System.out.println("byte: " + b);
        System.out.println("char: " + c);
        System.out.println("short: " + s);
        System.out.println("int: " + i);
        System.out.println("long: " + l);
        System.out.println("float: " + f);
        System.out.println("double: " + d);        
    }
    
    static void convertInt(int test) {
        byte b = (byte)test;
        char c = (char)test;
        short s = (short)test;
        int i = test;
        long l = test;
        float f = test;
        double d = test;
        
        System.out.println("byte: " + b);
        System.out.println("char: " + c);
        System.out.println("short: " + s);
        System.out.println("int: " + i);
        System.out.println("long: " + l);
        System.out.println("float: " + f);
        System.out.println("double: " + d);        
    }
    
    static void convertLong(long test) {
        byte b = (byte)test;
        char c = (char)test;
        short s = (short)test;
        int i = (int)test;
        long l = test;
        float f = test;
        double d = test;
        
        System.out.println("byte: " + b);
        System.out.println("char: " + c);
        System.out.println("short: " + s);
        System.out.println("int: " + i);
        System.out.println("long: " + l);
        System.out.println("float: " + f);
        System.out.println("double: " + d);        
    }
    
    static void convertFloat(float test) {
        byte b = (byte)test;
        char c = (char)test;
        short s = (short)test;
        int i = (int)test;
        long l = (long)test;
        float f = test;
        double d = test;
        
        System.out.println("byte: " + b);
        System.out.println("char: " + c);
        System.out.println("short: " + s);
        System.out.println("int: " + i);
        System.out.println("long: " + l);
        System.out.println("float: " + f);
        System.out.println("double: " + d);        
    }
    
    static void convertLong(double test) {
        byte b = (byte)test;
        char c = (char)test;
        short s = (short)test;
        int i = (int)test;
        long l = (long)test;
        float f = (float)test;
        double d = test;
        
        System.out.println("byte: " + b);
        System.out.println("char: " + c);
        System.out.println("short: " + s);
        System.out.println("int: " + i);
        System.out.println("long: " + l);
        System.out.println("float: " + f);
        System.out.println("double: " + d);        
    }
}
