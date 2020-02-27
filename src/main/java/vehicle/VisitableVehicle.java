package vehicle;

import sensorevents.SensorEventsVisitor;

import java.util.List;

/**
 * Created by mgupta on 10/22/17.
 */
public interface VisitableVehicle {

    void accept(SensorEventsVisitor visitor);

    int getSpeed();

    void setSpeed(int speed);

    List<String> getActiveEvents();

    DrivingModes getDrivingMode();

    boolean activateEvent(String eventName);

    boolean deactivateEvent(String eventName);
}
