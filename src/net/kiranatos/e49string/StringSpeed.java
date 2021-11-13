package net.kiranatos.e49string;

public class StringSpeed {
    static int cycle = 1_000_000;
    
    public static void main(String[] args) {
        StringSpeed ss = new StringSpeed();
        System.out.println(ss.testStringSimpleAdd());         // 426447640730 ns String str1+str2
        System.out.println(ss.testStringConcat());            // 26645920 ns String str1.concat(str2)
        System.out.println(ss.testStringBuffer());            // 21850679 ns StringBuffer sb.append(i)
        System.out.println(ss.testStringBufferWithChar());    // 11153545 ns StringBuffer sb.append((char)i)
        System.out.println(ss.testStringBuilder());           // 20217887 ns StringBuilder sb.append(i)
        System.out.println(ss.testStringBuilderWithChar());   // 12884103 ns StringBuilder sb.append((char)i)
    }    
    
    public String testStringSimpleAdd() {
        String s = "string";
        long start = System.nanoTime();
        for (int i = 0; i < cycle; i++) {
            s = s + i;            
        }        
        return (System.nanoTime() - start) + " ns String str1+str2";
    }
    
    public String testStringConcat() {
        String s = "string";
        long start = System.nanoTime();
        for (int i = 0; i < cycle; i++) {
            s.concat("str");
        }        
        return (System.nanoTime() - start) + " ns String str1.concat(str2)";
    }
    
    public String testStringBuffer() {        
        long start = System.nanoTime();
        StringBuffer sb = new StringBuffer("String");
        for (int i = 0; i < cycle; i++) {
            sb.append(i);            
        }
        
        return (System.nanoTime() - start) + " ns StringBuffer sb.append(i)";
    }
    
    public String testStringBufferWithChar() {
        long start = System.nanoTime();
        StringBuffer sb = new StringBuffer("String");
        for (int i = 0; i < cycle; i++) {
            sb.append((char)i);            
        }
        
        return (System.nanoTime() - start) + " ns StringBuffer sb.append((char)i)";
    }
    
    public String testStringBuilder() {    
        long start = System.nanoTime();
        StringBuilder sb = new StringBuilder("String");
        for (int i = 0; i < cycle; i++) {
            sb.append(i);            
        }
        
        return (System.nanoTime() - start) + " ns StringBuilder sb.append(i)";
    }
    
    public String testStringBuilderWithChar() {
        long start = System.nanoTime();
        StringBuilder sb = new StringBuilder("String");
        for (int i = 0; i < cycle; i++) {
            sb.append((char)i);            
        }
        
        return (System.nanoTime() - start) + " ns StringBuilder sb.append((char)i)";
    }
}
