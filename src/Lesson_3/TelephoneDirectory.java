package Lesson_3;

import java.util.HashMap;
import java.util.Map;

public class TelephoneDirectory {

    public static void main(String[] args) {

        String lastName = "Пупыркин";    //  Введите фамилию
        Map<String, String> td = tellDirect();
        System.out.println("Телефон сотрудника " + lastName + ": " + td.getOrDefault(lastName, "Нет номера"));

    }

    //  В данном методе добавляем в справочник сотрудников
    private static Map<String, String> tellDirect(){
        Map<String, String> td = new HashMap<>();
        td.put("Сухомлин","(999)0959568");
        td.put("Трофимов","(925)1564892");
        td.put("Чугунов","(999)0985634");
        td.put("Павлов","(499)0959563");
        td.put("Ибрагимов","(987)7895621");
        td.put("Пупыркин","(937)4753603");
        td.put("Барашкин","(922)5469821");
        td.put("Цветков","(905)2210800");
        td.put("Синицын","(903)3933627");
        td.put("Иванов","(922)6274097");
        td.put("Пташкин","(922)8209564");
        td.put("Печкин","(912)5540044");

        return td;
    }

}
