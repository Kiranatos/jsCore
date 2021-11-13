package net.kiranatos.e01core;

public class SwapVersions {
    public static void main(String[] args) {
        int a = 11, b = 105;
        System.out.printf("a = %d, b = %d \n 1. Сложение и вычитание \n", a, b );
        a = a + b; 
        b = a - b; 
        a = a - b;
        System.out.printf("a = %d, b = %d \n 2. Вычитание и сложение \n", a, b );
        a = a - b; 
        b = a + b; 
        a = -a + b;        
        System.out.printf("a = %d, b = %d \n 3. Умножение и деление \n", a, b );
        a = a * b; 
        b = a / b; // деление НЕ целочисленное 
        a = a / b;
        System.out.printf("a = %d, b = %d \n 4. Умножение и деление (сокращённый вариант) \n", a, b );
        a = a * b / (b = a);
        System.out.printf("a = %d, b = %d \n 5. Битовые операции \n", a, b );
        a = a ^ b;
        b = b ^ a;
        a = a ^ b;
        System.out.printf("a = %d, b = %d \n", a, b );
        
    }    
}
