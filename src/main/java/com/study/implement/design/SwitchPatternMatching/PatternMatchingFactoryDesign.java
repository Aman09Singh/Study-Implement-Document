package com.study.implement.design.SwitchPatternMatching;

public class PatternMatchingFactoryDesign {

    static interface Drive{
        public void canDrive();
    }

    static class Bike implements Drive{

        @Override
        public void canDrive() {
            System.out.println("Object of Bike, Can drive");
        }
    }

    static class Car implements Drive{

        @Override
        public void canDrive() {
            System.out.println("Object of Car, Can drive");
        }
    }

    static class Bus implements Drive{

        @Override
        public void canDrive() {
            System.out.println("Object of Bus, Can drive");
        }
    }

    static class FactoryLoader{

        public enum VehicleType {
            Bike,Car,Bus
        }

        public static Drive getFactoryObject(VehicleType vehicleType){

            switch(vehicleType){
                case Bike -> {
                    return new Bike();
                }
                case Car -> {
                    return new Car();
                }
                case Bus -> {
                    return new Bus();
                }
                default ->  throw new IllegalArgumentException("Incorrect Object Type");
            }
        }
    }

    public static void main(String[] args){
        Drive drive = FactoryLoader.getFactoryObject(FactoryLoader.VehicleType.Bike);
        drive.canDrive();
    }

}
