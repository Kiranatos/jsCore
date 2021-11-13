package net.kiranatos.e01core;

public class PostfixAndPrefixIncrementAndDecrement {
    public static void main(String[] args) {   
        int a = 100;

        int x = 1;
        x = x++; // 1
        x = ++x; // 2

        int b1 = 2, b2 = 2;
        int c1 = 3, c2 = 3;
        int k1 = 5 + b1++ + ++c1 + c1++ + ++c1 + ++b1 + b1 + b1++;
        //       5 + 2 +       4 + 4    +  6     + 4 +  4 + 4
        //         + 3 +         + 5    +        +   +   +  5
        int k2 = 5 + b2++ + ++c2 + c2++ + ++c2 ;
        //       5 + 2 +       4 + 4    +  5   + 3 + 3 + 3
        System.out.println("k1=" + k1);
        System.out.println("k2=" + k2);
        System.out.println("b=" + b1);
        System.out.println("c=" + c1);
        System.out.println("____________________");
        a = 2;
        b1 = a++ + (--a * ++a);
        System.out.println("b1 = " + b1);
        /*
        var a = 2, b = 0;
b = (a++ + (--a * ++a));
alert(b);

(0) a = 2; | b = (a++ + (--a * ++a));
(1.0) a = 3; | b = (2 + (--a * ++a));
(1.1) a = 2; | b = (2 + (2 * ++a));
(1.2) a = 3; | b = (2 + (2 * 3));
(2) b = 8;

        */
        System.out.println("____________________");
        a=3;
        System.out.printf("%d %d %d\n",(--a),(a--),(--a));//output is 2 2 0 
        System.out.printf("a = %d\n",a);//here the final value of 'a'
        //end of case 1
        a=3;
        //case 2 
        System.out.printf("%d\n",(++a)+(a--)-(--a));//output is 6 value of a is 2
        System.out.printf("a = %d\n",a);//here the final value of 'a'
        //end of case 2
        a=3;
        //case 3 
        System.out.printf("%d\n",(++a)*(a--)*(--a));//output is 32  value of a is 2
        System.out.printf("a = %d\n",a);//here the final value of 'a'
        //end of case 3
        //case 4 
        int i=3,j;
        i= ++i * i++ * ++i;
        System.out.printf("i = %d\n",i);//output is 96
        i=3;
        j= ++i * i++ * ++i;
        System.out.printf("%d\n",j);//output is 96
        //end of case 4
        System.out.println("____________________");
        
        a = 3;
        b1 = 4;
        b2 = 2;        
        System.out.println("a<<2 = " + (a<<2));
        System.out.println("a<<2+1 = " + (a<<2+1));
        System.out.println("a<<2+1+b2 = " + (a<<2+1+b2)); //3*2*2*2*2*2
	b2 += a<<2+1;
        System.out.println("b2+=a<<2+1 = " + b2);
        System.out.println("*******");
        int w = 1;
//        w = ++w + w++ * w;
        w = ++w - - w++;
//        w = ++w + ++w;
//        w = w++ + (--w * ++w);
        System.out.println(" w = " + w);
        
//        //Чему равно w ?
//        int w = 1;
//        w = w++ + (--w * ++w);
//        System.out.println(w);
        
        
    }    
}
