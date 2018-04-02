import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrainScheduleTest {

    TrainSchedule schedule = new TrainSchedule();

    @Test
    void findTrainTest() {
        schedule.addTrain("train1", 100L, "terminal1");
        schedule.addTrain("train2", 95L, "terminal2");
        schedule.addTrain("train3", 110L, "terminal3");
        schedule.removeTrain("train3");
        schedule.addStation("train1", "intermediateStation1");
        schedule.addStation("train1", "intermediateStation2");
        schedule.addStation("train2", "intermediateStation3");
        schedule.removeStation("train1", "intermediateStation2");
        assertEquals("train1", schedule.findTrain("terminal1", 99L));
        assertEquals("train2", schedule.findTrain("intermediateStation3", 94L));
        assertThrows(IllegalArgumentException.class,
                () -> schedule.findTrain("terminal3", 109L));
        assertThrows(IllegalArgumentException.class,
                () -> schedule.findTrain("intermediateStation2", 101L));
    }

}