package Lesson_1.Competitors;

import java.util.ArrayList;

public class Team {
    String teamName;
    ArrayList <Competitor> teamArr = new ArrayList();

    public Team(String teamName, Competitor... competitor){
        this.teamName = teamName;
        for (Competitor c: competitor) {
            teamArr.add(c);
        }
    }


    //метод вывода информации обо всех членах команды
    public void infoAboutTeam(){
        System.out.println("Члены команды:");
        for (Competitor c: teamArr) {
                c.infoName();
        }
    }

    //метод для вывода информации о членах команды прошедших дистанцию
    public void infoAboutCompetitors(){
        System.out.println("Участники, прошедшие дистанцию:");
        for (Competitor c: teamArr
             ) {
                if (c.isOnDistance()){
                    c.info();
                }
        }
    }

    public ArrayList<Competitor> getTeamArr(){
        return teamArr;
    }


}
