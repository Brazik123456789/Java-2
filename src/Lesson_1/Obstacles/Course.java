package Lesson_1.Obstacles;

import Lesson_1.Competitors.Competitor;
import Lesson_1.Competitors.Team;

import java.util.ArrayList;

public class Course {
    Obstacle [] arr;

    //массив препятствий
    public Course(Obstacle... _obstacale){
        for (Obstacle c: _obstacale) {
            this.arr = _obstacale;
        }
    }


    public void go(Team _team){
        ArrayList <Competitor> cmpttrs = _team.getTeamArr();
        for (Competitor c: cmpttrs) {
            for (Obstacle o : arr) {
                o.doIt(c);
                if (!c.isOnDistance()) break;
            }
        }
    }

}