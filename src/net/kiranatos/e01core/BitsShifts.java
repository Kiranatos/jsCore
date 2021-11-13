package net.kiranatos.e01core;

public class BitsShifts {
    public static void main(String[] args) {        
        int x, y, a, b, k, m, n;
        
        a = 1000;
        System.out.printf("Число в двоичной системе : %d = %s \n", a, Integer.toBinaryString(a));
        System.out.println("При каждом сдвиге влево << выполняется умножение числа на 2");
        System.out.printf("\t Example: a<<3=a*2*2*2\n");
        System.out.printf("\t a = %d(%s);\n\t %d << 3 = %d(%s) \n", 
                a, Integer.toBinaryString(a), a, a<<3, Integer.toBinaryString(a<<3));
        
        System.out.println("При каждом сдвиге вправо выполняется деление на два с отбрасыванием любого остатка");
        System.out.println("\t Example: a>>3=a/2/2/2");        
        System.out.printf("\t a = %d(%s);\n\t %d >> 3 = %d(%s) %n", 
                a, Integer.toBinaryString(a), a, a>>3, Integer.toBinaryString(a>>3));
        
        System.out.println("Отличие >> и >>> при работе с отрицательными числами. Знак в крайнем левом бите");
        System.out.println(">> - биты заполняет 1");
        System.out.println(">>> - биты заполняет 0. Беззнаковый сдвиг вправо >>> не учитывает знак числа");
        a = -1000;
        System.out.printf("\t a = %d(%s);\n\t %d >> 3 = %d\t\t(%s) %n", 
                a, Integer.toBinaryString(a), a, a>>3, Integer.toBinaryString(a>>3));
        System.out.printf("\t a = %d(%s);\n\t %d >>> 3 = %d\t(%s) %n", 
                a, Integer.toBinaryString(a), a, a>>>3, Integer.toBinaryString(a>>>3));
        System.out.println("Для корректного сдвига число позиций, на которое "
                + "нужно произвести сдвиг, \nдолжно быть меньше, чем количество "
                + "битов в результате сдвига. Если число \nтипа int (long), то "
                + "сдвиг не может быть сделан более, чем на 32 (64) бита.");
        
        System.out.println("\n\n\tХитрости с битовыми операциями:");
        System.out.println("Установка 1 на n-ый бит: x|(1<<n)");
        x = 1000;
        System.out.printf("\t x = %d\t\t(%s)%n", x, Integer.toBinaryString(x));
        System.out.printf("\t n = 1, x = %d\t(%s)%n", (x | 1<<1), Integer.toBinaryString(x | 1<<1));
        System.out.printf("\t n = 10, x = %d\t(%s)%n", (x | 1<<10), Integer.toBinaryString(x | 1<<10));
        System.out.printf("\t n = 13, x = %d\t(%s)%n", (x | 1<<13), Integer.toBinaryString(x | 1<<13));
        
        System.out.println("Обнуление n-ого бита: x & ~(1<<n)");
        x = 999;
        System.out.printf("\t x = %d\t(%s)%n", x, Integer.toBinaryString(x));
        System.out.printf("\t n = 0, x = %d\t(%s)%n", (x & ~(1<<0)), Integer.toBinaryString(x & ~(1<<0)));
        System.out.printf("\t n = 1, x = %d\t(%s)%n", (x & ~(1<<1)), Integer.toBinaryString(x & ~(1<<1)));
        System.out.printf("\t n = 9, x = %d\t(%s)%n", (x & ~(1<<9)), Integer.toBinaryString(x & ~(1<<9)));
        
        System.out.println("Обнуление m-ого бита числа n (справа налево):");        
        m = 6;
        n = 100;
        System.out.printf("\t n = %d(%s)%n", n, Integer.toBinaryString(n));        
        System.out.printf("\t m = %d, bit = %d(%s)%n", m, n & ~(1 << (m-1)), Integer.toBinaryString(n & ~(1 << (m-1))));
        
        System.out.println("Переключение n-ого бита: x ^ (1<<n)");
        System.out.printf("\t x = %d\t(%s)%n", x, Integer.toBinaryString(x));
        System.out.printf("\t n = 0, x = %d\t(%s)%n", (x ^ (1<<0)), Integer.toBinaryString(x ^ (1<<0)));
        System.out.printf("\t n = 2, x = %d\t(%s)%n", (x ^ (1<<2)), Integer.toBinaryString(x ^ (1<<2)));
        System.out.printf("\t n = 4, x = %d(%s)%n", (x ^ (1<<4)), Integer.toBinaryString(x ^ (1<<4)));
        System.out.printf("\t n = 9, x = %d\t(%s)%n", (x ^ (1<<9)), Integer.toBinaryString(x ^ (1<<9)));
        System.out.printf("\t n = 15, x = %d(%s)%n", (x ^ (1<<15)), Integer.toBinaryString(x ^ (1<<15)));
                        
        System.out.println("Округление до следующей степени двойки (unsigned int x and only works if x is 32 bit):");
        x = 100; 
        x--;
        x |= x >> 1;
        x |= x >> 2;
        x |= x >> 4;
        x |= x >> 8;
        x |= x >> 16;
        x++;
        System.out.println("\tx = " + x);
        
        // int 32 bit	-2,147,483,648 to 2,147,483,647
        System.out.println("Получение максимального целого:");
        int maxInt1 = ~(1 << 31);         
        int maxInt2 = (1 << 31) - 1;
        int maxInt3 = (1 << -1) - 1;
        System.out.printf("\t%d %d %d%n", maxInt1, maxInt2, maxInt3);
        
        System.out.println("Получение минимального целого:");
        int minInt1 = 1 << 31;
        int minInt2 = 1 << -1;
        System.out.printf("\t%d %d%n", minInt1, minInt2);
        
        //long 64 bit	-9,223,372,036,854,775,808 to 9,223,372,036,854,775,807
        System.out.println("Получение максимального long:");
        long maxLong = ((long)1 << 127) - 1;
        System.out.printf("\t%d%n", maxLong);
        
        System.out.println("Модуль:");
        x = -10;
        System.out.printf("\tversion 1: %d%n", x < 0 ? -x : x);
        System.out.printf("\tversion 2: %d%n", (x ^ (x >> 31)) - (x >> 31));
        
        System.out.println("Максимум двух чисел:");
        k = 12;
        m = 22;
        System.out.printf("\t%d%n", k & ((m-k) >> 31) | m & (~(m-k) >> 31));
        k = 512;
        m = 22;
        System.out.printf("\t%d%n", k & ((m-k) >> 31) | m & (~(m-k) >> 31));
        
        System.out.println("Минимум двух чисел:");
        System.out.printf("\t%d%n", m & ((m-k) >> 31) | k & (~(m-k) >> 31));
        k = 51;
        m = 122;
        System.out.printf("\t%d%n", m & ((m-k) >> 31) | k & (~(m-k) >> 31));

        System.out.println("Вычисление 2^n:");
        System.out.printf("\t 2^%d = %d%n", 3, 2 << (3-1));
        System.out.printf("\t 2^%d = %d%n", 4, 2 << (4-1));
        System.out.printf("\t 2^%d = %d%n", 10, 2 << (10-1));
        
        System.out.println("Среднее арифметическое:");
        y = 10;
        x = 20;
        System.out.printf("\t%d%n", (x + y) >> 1);
        System.out.printf("\t%d%n", ((x ^ y) >> 1) + (x & y));
        
        System.out.println("Получение m-ого бита числа n (справа налево):");
        n = 100;
        System.out.printf("\t n = %d(%s)%n", n, Integer.toBinaryString(n));
        m = 2;
        System.out.printf("\t m = %d, bit = %d%n", m, (n >> (m-1)) & 1);
        m = 6;
        System.out.printf("\t m = %d, bit = %d%n", m, (n >> (m-1)) & 1);                
    }    
}