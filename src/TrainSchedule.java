import java.util.*;

public class TrainSchedule {
    private Map<String, Train> trains = new HashMap<String, Train>();

    private class Train {
        private String name;
        private Long departureTime; //Количество минут, прошедшее с 1970 года, 1 января, 3 часов ночи
        private String terminal;

        private Train(String name, Long departureTime, String terminal) {
            this.name = name;
            this.departureTime = departureTime;
            this.terminal = terminal;
        }

        ArrayList<String> intermediateStation = new ArrayList<String>();

        void addStation(String nameStation) {
            if (!intermediateStation.contains(nameStation)) {
                intermediateStation.add(nameStation);
            }
        }

        void removeStation(String nameStation) {
            intermediateStation.remove(nameStation);
        }

        ArrayList<String> getIntermediateStation() {
            return intermediateStation;
        }

    }

    //Добавить новый поезд
    public Boolean addTrain(String nameTrain, Long departureTime, String terminal) {
        if (nameTrain != null && terminal != null && departureTime >= 0) {
            trains.put(nameTrain, new Train(nameTrain, departureTime, terminal));
            return true;
        } else {
            return false;
        }
    }

    //Удалить поезд
    public Boolean removeTrain(String nameTrain) {
        if (trains.containsKey(nameTrain)) {
            trains.remove(nameTrain);
            return true;
        } else {
            return false;
        }
    }

    //Добавить промежуточную станцию
    public Boolean addStation(String nameTrain, String nameStation) {
        if (trains.containsKey(nameTrain) && nameStation != null) {
            trains.get(nameTrain).addStation(nameStation);
            return true;
        } else {
            return false;
        }
    }

    //Удалить промежуточную станцию
    public Boolean removeStation(String nameTrain, String nameStation) {
        if (trains.containsKey(nameTrain) && trains.get(nameTrain).getIntermediateStation().contains(nameStation)) {
            trains.get(nameTrain).removeStation(nameStation);
            return true;
        } else return false;
    }

    //Поиск ближайшего по времени поезда
    public String findTrain(String terminal, Long time) {
        Long minTime = Long.MAX_VALUE;
        String result = "";
        Boolean trainNotFound = true;
        for (Train train : trains.values()) {
            if (train.intermediateStation.contains(terminal) || Objects.equals(terminal, train.terminal)) {
                Long deltaTime = train.departureTime - time;
                if (deltaTime >= 0 && deltaTime <= minTime) {
                    result = train.name;
                    minTime = deltaTime;
                    trainNotFound = false;
                }
            }
        }
        if (trainNotFound) {
            throw new IllegalArgumentException();
        } else return result;
    }
}


