package vehicle;

import sensorevents.SensorEventTypes;
import sensorevents.SensorEventsVisitor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by mgupta on 10/22/17.
 */
public class AutonomousVehicle implements VisitableVehicle {

    private Map<String, Boolean> eventsActivityMap = new HashMap<>();
    private int                  speed;
    private DrivingModes         drivingMode;

    public AutonomousVehicle(DrivingModes drivingMode, int initialSpeed) {
        this.drivingMode = drivingMode;
        this.speed = initialSpeed;
        Stream.of(SensorEventTypes.values()).forEach(e -> eventsActivityMap.put(e.name(), false));
    }

    @Override
    public void accept(SensorEventsVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void setSpeed(int speed) {
        if (speed < 10) {
            this.speed = 10;
            return;
        }
        this.speed = speed;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public DrivingModes getDrivingMode() {
        return drivingMode;
    }

    @Override
    public boolean activateEvent(String name) {
        if (!eventsActivityMap.get(name)) {
            eventsActivityMap.put(name, true);
            return true;
        }
        return false;
    }

    @Override
    public boolean deactivateEvent(String name) {
        if (eventsActivityMap.get(name)) {
            eventsActivityMap.put(name, false);
            return true;
        }
        return false;
    }

    public List<String> getActiveEvents() {
        return eventsActivityMap.keySet().stream().filter(e -> eventsActivityMap.get(e)).collect(toList());
    }
}
