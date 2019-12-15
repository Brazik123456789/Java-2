package Lesson_2;
import Lesson_2.MyException.MyArrayDataException;
import Lesson_2.MyException.MyArraySizeException;
import Lesson_2.MyException.MyException;

import java.util.Scanner;

public class MainClass {
    public static void main(String[] args){

        Scanner s = new Scanner(System.in);
        System.out.print("Введите размерность массива: ");
        int n = s.nextInt();
        System.out.println();

        String[][] array = new String[n][n];
        System.out.println("Введите значения массива (их " + (n*n) + "):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = s.next();
            }
        }

        System.out.println();
        System.out.println("Ваш массив:");
        for (String[] a:array) {
            for (String b:a) {
                System.out.print(b + " ");
            }
            System.out.println();
        }
        System.out.println();


        try {
            System.out.println("Сумма чисел равна " + StrArrToIntSum.ExMe(array));
        } catch (MyArraySizeException | MyArrayDataException e){
            System.out.println(e.getMessage());
        }

    }
}
