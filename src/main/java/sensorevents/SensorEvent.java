package sensorevents;

import vehicle.AutonomousVehicle;
import vehicle.DrivingModes;
import vehicle.VisitableVehicle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mgupta on 10/22/17.
 */
public class SensorEvent implements SensorEventsVisitor {

    private final SensorEventProfile eventProfile;
    private final SensorEventTypes   eventType;
    private final int                normalSpeedChange;
    private final int                safeSpeedChange;
    private final int                sportSpeedChange;



    public SensorEvent(SensorEventTypes eventType, SensorEventProfile eventProfile, int normalSpeedChange, int safeSpeedChange, int sportSpeedChange) {
        this.eventType = eventType;
        this.eventProfile = eventProfile;
        this.normalSpeedChange = normalSpeedChange;
        this.safeSpeedChange = safeSpeedChange;
        this.sportSpeedChange = sportSpeedChange;


    }

    public static void main(String[] args) {
        Set<String> strs = new HashSet<>();
        strs.add("hello");
        strs.add("mayank");



    }

    @Override
    public SensorEventProfile getEventProfile() {
        return eventProfile;
    }

    @Override
    public SensorEventTypes getEventType() {
        return eventType;
    }

    @Override
    public void visit(AutonomousVehicle vehicle) {
        if (vehicle.getDrivingMode().equals(DrivingModes.NORMAL)) {
            handleVehicleSpeed(vehicle, this.normalSpeedChange);
        } else if (vehicle.getDrivingMode().equals(DrivingModes.SAFE)) {
            handleVehicleSpeed(vehicle, this.safeSpeedChange);
        } else {
            handleVehicleSpeed(vehicle, this.sportSpeedChange);
        }
    }

    private void handleVehicleSpeed(VisitableVehicle vehicle, int speedVal) {
        if (this.eventType.equals(SensorEventTypes.TURBO)) {
            handleEmergencyTurboEventVehicleSpeed(vehicle, speedVal);
        } else {
            if (this.eventProfile.equals(SensorEventProfile.ACTIVATE)) {
                handleVehicleSpeedAndActivateEvent(vehicle, speedVal);
            } else {
                handleVehicleSpeedAndDeactivateEvent(vehicle, speedVal);
            }
        }
    }

    private void handleEmergencyTurboEventVehicleSpeed(VisitableVehicle vehicle, int speedVal) {
        if (!vehicle.getActiveEvents().contains(this.eventType.name())) {
            vehicle.activateEvent(this.eventType.name());
            vehicle.setSpeed(vehicle.getSpeed() + speedVal);
        }
    }

    private void handleVehicleSpeedAndActivateEvent(VisitableVehicle vehicle, int speedVal) {
        if (vehicle.activateEvent(this.eventType.name())) {
            vehicle.setSpeed(vehicle.getSpeed() - speedVal);
        }
    }

    private void handleVehicleSpeedAndDeactivateEvent(VisitableVehicle vehicle, int speedVal) {
        if (vehicle.deactivateEvent(this.eventType.name())) {
            vehicle.setSpeed(vehicle.getSpeed() + speedVal);
        }
    }
}
