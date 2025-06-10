package com.company;

import java.util.ArrayList;
import java.util.List;

// Command interface
interface DeliveryCommand {
    void execute();
}

// Receiver class: Vehicle
class Vehicle {
    private String name;

    public Vehicle(String name) {
        this.name = name;
    }

    public void powerOn() {
        System.out.println(name + " is powered on.");
    }

    public void transport(String destination, String cargo) {
        System.out.println(name + " is transporting " + cargo + " to " + destination);
    }
}

// Receiver class: Operator
class Operator {
    private String type;

    public Operator(String type) {
        this.type = type;
    }

    public void controlVehicle() {
        System.out.println(type + " is controlling the vehicle...");
    }
}

// Concrete Command
class DeliverCargoCommand implements DeliveryCommand {
    private Vehicle vehicle;
    private Operator operator;
    private String destination;
    private String cargo;

    public DeliverCargoCommand(Vehicle vehicle, Operator operator, String destination, String cargo) {
        this.vehicle = vehicle;
        this.operator = operator;
        this.destination = destination;
        this.cargo = cargo;
    }

    @Override
    public void execute() {
        operator.controlVehicle();
        vehicle.powerOn();
        vehicle.transport(destination, cargo);
    }
}

// Invoker
class TransportController {
    private List<DeliveryCommand> commandQueue = new ArrayList<>();

    public void addCommand(DeliveryCommand command) {
        commandQueue.add(command);
    }

    public void runDeliveries() {
        for (DeliveryCommand command : commandQueue) {
            command.execute();
            System.out.println("----");
        }
    }
}

// Main simulation
public class TransportSimulation {
    public static void main(String[] args) {
        Vehicle truck = new Vehicle("Truck");
        Operator human = new Operator("Human");

        Vehicle drone = new Vehicle("Drone");
        Operator robot = new Operator("Robot");

        DeliveryCommand delivery1 = new DeliverCargoCommand(truck, human, "City A", "Groceries");
        DeliveryCommand delivery2 = new DeliverCargoCommand(drone, robot, "City B", "Medical Supplies");

        TransportController controller = new TransportController();
        controller.addCommand(delivery1);
        controller.addCommand(delivery2);

        controller.runDeliveries();
    }
}
