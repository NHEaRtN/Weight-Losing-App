package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GoalSetterTest {
    private GoalSetter gs1;
    private GoalSetter gs2;

    @BeforeEach
    void runBefore() {
        gs1 = new GoalSetter(100,90,30);
        gs2 = new GoalSetter(86,70,90);
    }

    @Test
    void testKgToLost() {
        assertEquals(10,gs1.kgPlanningToLose());
        assertEquals(16,gs2.kgPlanningToLose());
    }

    @Test
    void testSetters()  {
       GoalSetter gs3 = new GoalSetter(0,0,0);
       gs3.setGoalWeight(100);
       gs3.setCurrentWeight(120);
       assertEquals(20,gs3.kgPlanningToLose());
       gs3.setPlannedDaysReachGoal(20);
       assertEquals(20,gs3.getPlannedDaysReachGoal());
    }

    @Test
    void testDeficitPerDay() {
        assertEquals(10*7000/30,gs1.getDeficitPerDay());
        assertEquals(16*7000/ gs2.getPlannedDaysReachGoal(),gs2.getDeficitPerDay());
    }
}


