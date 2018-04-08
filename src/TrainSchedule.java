import java.util.*;

public class TrainSchedule {
    private Map<String, Train> trains = new HashMap<String, Train>();

    private class Train {
        private String name;
        private Long departureTime; //Количество минут, прошедшее с 1970 года, 1 января, 3 часов ночи
        private String terminal;

        /**
         *
         * @param name - название поезда
         * @param departureTime - время отправления
         * @param terminal - конечная станция
         */

        private Train(String name, Long departureTime, String terminal) {
            this.name = name;
            this.departureTime = departureTime;
            this.terminal = terminal;
        }

        ArrayList<String> intermediateStation = new ArrayList<String>();

        /**
         *
         * @param nameStation - название промежуточной станции
         */

        void addStation(String nameStation) {
            if (!intermediateStation.contains(nameStation)) {
                intermediateStation.add(nameStation);
            }
        }

        /**
         *
         * @param nameStation - название промежуточной станции
         */

        void removeStation(String nameStation) {

            intermediateStation.remove(nameStation);
        }

        /**
         *
         * @return возврат промежуточных станций поезда
         */

        List<String> getIntermediateStation() {

            return intermediateStation;
        }

    }

    /**
     *
     * @param nameTrain - название поезда
     * @param departureTime - время отправки
     * @param terminal - конечная станция
     * @return успешность выполнения метода (Добавление нового поезда)
     */

    public Boolean addTrain(String nameTrain, Long departureTime, String terminal) {
        if (nameTrain != null && !trains.containsKey(nameTrain) && terminal != null && departureTime >= 0) {
            trains.put(nameTrain, new Train(nameTrain, departureTime, terminal));
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param nameTrain - название поезда
     * @return успешность выполениняи метода (Удаление поезда)
     */

    public Boolean removeTrain(String nameTrain) {
        if (trains.containsKey(nameTrain)) {
            trains.remove(nameTrain);
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param nameTrain - название поезда
     * @param nameStation - название промежуточной станции
     * @return успешность выполнения метода (Добавление промежуточной станции)
     */

    public Boolean addStation(String nameTrain, String nameStation) {
        if (trains.containsKey(nameTrain) && nameStation != null &&
                !trains.get(nameTrain).getIntermediateStation().contains(nameStation)) {
            trains.get(nameTrain).addStation(nameStation);
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param nameTrain - название поезда
     * @param nameStation - название промежуточной станции
     * @return успешность выполнения метода (Удаление промежуточной станции)
     */

    public Boolean removeStation(String nameTrain, String nameStation) {
        if (trains.containsKey(nameTrain) && trains.get(nameTrain).getIntermediateStation().contains(nameStation)) {
            trains.get(nameTrain).removeStation(nameStation);
            return true;
        } else return false;
    }

    /**
     *
     * @param terminal - станция назначения
     * @return вывод  ближайшего по времени поезда (по станции, куда едем, и текущему времени)
     */

    public String findTrain(String terminal) {
        final Long time = System.currentTimeMillis();
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


