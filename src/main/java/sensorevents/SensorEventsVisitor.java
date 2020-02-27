package sensorevents;

import vehicle.AutonomousVehicle;

/**
 * Created by mgupta on 10/22/17.
 */
public interface SensorEventsVisitor {

    void visit(AutonomousVehicle vehicle);

    SensorEventProfile getEventProfile();

    SensorEventTypes getEventType();

}
