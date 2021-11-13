package net.kiranatos.e03generics;

import java.util.Arrays;

public class Demo01Arrays {    
    private static int[] h01, h02; // два массива: h01 и h02
    private int h03[], h04; // один массив: h03 и int-переменная h04

    public static void main(String[] args) {
        /**
         * int[][] a = new int[Y][X];
         * [0][0] , [0][1] , [0][2] , [0][3] , [0][4] ,
         * [1][0] , [1][1] , [1][2] , [1][3] , [1][4] ,
         * [2][0] , [2][1] , [2][2] , [2][3] , [2][4] ,
         */
        int[][] arrayA = new int[3][5]; // прямоугольный массив
        System.out.printf("Y = %d%n", arrayA.length);
        System.out.printf("X = %d%n", arrayA[0].length);

        int[][] arrayB = new int[3][]; // массив переменной длины (тут - треугольный)
        arrayB[0] = new int[1];
        arrayB[1] = new int[2];
        arrayB[2] = new int[3];
        
        int arrayC[] = new int[] {1,2,3,};
        arrayC = new int[]{0,1,2,3}; // а вот так не сработает: c = {0,1,2,3};

        int number = 10;
        int[] array1D = {0,8,1,2,3, number, 0, (int)Math.PI};
        int[][] array2D = { {0,1,5,10},{2, 3, 8, 0, 5, 55, 16},{0,1} };
        int[][][] array3D = {
            {   {2,3,1,0},
                {2,3,1,0},
                {2,3,1,0}},

            {   {2,3,1,0},
                {2,3,1,0},
                {2,3,1,0}},

            {   {2,3,1,0},
                {2,3,1,0},
                {2,3,1,0}}};
        System.out.println("============array1D==========");
        System.out.println("link: " + array1D);
        System.out.println(Arrays.toString(array1D)); //Работает на глубину одного измерения (для одномерных масивов)
        System.out.println("============array2D==========");
        System.out.println("link: " + array2D);
        System.out.println(Arrays.toString(array2D));
        System.out.println(Arrays.deepToString(array2D));
        System.out.println("============array3D==========");
        System.out.println("link: " + array3D);
        System.out.println(Arrays.toString(array3D));
        System.out.println(Arrays.deepToString(array3D));

        System.out.println("\nCopying :");
        int[] array1DCopy = Arrays.copyOf(array1D, array1D.length*2);
        System.out.println(Arrays.toString(array1DCopy));

        System.out.println("\nSorting:");
        Arrays.sort(array1D); // Метод void sort(int[] myArray, int fromIndex, int toIndex) сортирует массив целых чисел или его подмассив по возрастанию.
        System.out.println(Arrays.toString(array1D));

        System.out.println("\nSearch (Array must be sorted!):");
        /* int binarySearch(int[] myArray, int fromIndex, int toIndex, int key).
        Этот метод ищет элемент key в уже отсортированном массиве myArray или подмассиве,
        начиная с fromIndex и до toIndex. Если элемент найден, метод возвращает его индекс, если нет - (-fromIndex)-1. */
        int key = 8;
        int keyPosition = Arrays.binarySearch(array1D, key);
        System.out.printf("\tKey %d was found in position %d%n", key, keyPosition);
        System.out.printf("\tUnexisted key have position %d%n", Arrays.binarySearch(array1D, 111));
        System.out.printf("\tKey have position %d in unsorted array%n", Arrays.binarySearch(array1DCopy, key));
        System.out.printf("\tKey have position %d inside range%n", Arrays.binarySearch(array1D, 1, 8, key));
        System.out.printf("\tKey have position %d outside range%n", Arrays.binarySearch(array1D, 7, 8, key));
        System.out.printf("\tKey have position %d outside range%n", Arrays.binarySearch(array1D, 1, 4, key));

        System.out.println("\n\nComparison of array: " + Arrays.equals(array1D, array1DCopy));

        System.out.println("\nInsert part of one array in another:");
        h01 = new int[]{10,20,30,40,50,60,70,80,90};
        h02 = new int[]{-1,-2,-3,-4,-5,-6,-7,-8,-9};
        System.arraycopy(h02, 2, h01, 5, 3); // (source[], sourcePosition, destination[], destPosition, amountOfElem)
        System.out.println(Arrays.toString(h01)); //[10, 20, 30, 40, 50, -3, -4, -5, 90]
    }
}