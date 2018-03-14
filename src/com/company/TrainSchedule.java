package com.company;

import java.util.*;
public class  TrainSchedule {
     private Map<String, Train> trains = new HashMap<String, Train>();

     private class Train {
         private String name;
         private Integer departureTime;
         private String terminal;

         private Train(String name, Integer departureTime, String terminal) {
             this.name = name;
             this.departureTime = departureTime;
             this.terminal = terminal;
         }

         ArrayList<String> intermediateStation = new ArrayList<String>();
         //private Map<String, Train> intermediateStation = new HashMap<String, Train>;

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

        void addTrain(String nameTrain, Integer departureTime, String terminal) {
            trains.put(nameTrain, new Train(nameTrain, departureTime, terminal));
        }

        void removeTrain(String nameTrain) {
            trains.remove(nameTrain);
        }

        void addIntermediateStation(String nameTrain, String nameStation) {
             trains.get(nameTrain).addStation(nameStation);
        }

        void removeIntermediateStation(String nameTrain, String nameStation) {
            trains.get(nameTrain).removeStation(nameStation);
        }
        String findTrain(String terminal, Integer time) {
         Integer minTime = Integer.MAX_VALUE;
         String result = "";
         Boolean trainNotFound = true;
         for()
        }

}