package Lesson_2;

import Lesson_2.MyException.MyArrayDataException;
import Lesson_2.MyException.MyArraySizeException;

public class StrArrToIntSum {

    public static int ExMe(String [][] arr) throws MyArraySizeException, MyArrayDataException {
        if (arr.length != 4) throw new MyArraySizeException("Введён неверный массива");

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e){
                    throw new MyArrayDataException("В ячейке " + i +"-"+ j + " лежит не число");
                }
            }
        }

        return sum;
    }
}
