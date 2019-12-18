package Lesson_3;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class UniqueWords {

    public static void main(String[] args) {

        String[] words = {"Мама","Папа","Дочь","Сынок","Щенок","Котенок","Попугай"};

        Map<String, Integer> hm = new HashMap<>();
        Random random = new Random();

        for (int i = 0; i < 20; i++) {

            String word = words[random.nextInt(6)];
            Integer current = hm.get(word);
            hm.put(word, current == 0 ? 1 : current+1);
        }

        System.out.println(hm);
    }

}