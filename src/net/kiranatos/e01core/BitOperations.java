package net.kiranatos.e01core;

public class BitOperations {
    public static void main(String[] args) {
        byte ip = (byte)192;
        System.out.println(ip);
        System.out.println(Integer.toBinaryString(ip & (0xff)));
        System.out.println(Integer.toBinaryString(ip));
        
        int ip2 = 192;
        System.out.println(ip2);
        System.out.println(Integer.toBinaryString(ip2 & (0xff)));
        System.out.println(Integer.toBinaryString(ip2));
        //https://csharpcoderr.com/5839/
    }
}
