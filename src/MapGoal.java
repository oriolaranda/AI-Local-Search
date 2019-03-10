package src;

import aima.search.framework.GoalTest;

public class MapGoal implements GoalTest{
    public boolean isGoalState(Object aState){
        Map mapa = (Map) aState;
        return mapa.isGoal();
    }
}
