

import Competitors.*;
import Obstacles.*;

public class Main {
    public static void main(String[] args) {

        Team team = new Team("Пепсикольные",new Human("Боб"), new Cat("Барсик"), new Dog("Бобик"));
        Course c = new Course(new Cross(80), new Wall(2), new Water(50));
        c.go(team);
        team.infoAboutTeam();
        team.infoAboutCompetitors();


    }
}

