package sensorevents;

import vehicle.AutonomousVehicle;
import vehicle.DrivingModes;

import static config.SpeedSimulatorConstants.SPEED_SIGN_NORMAL_DRIVING_SPEED_CHANGE;
import static config.SpeedSimulatorConstants.SPEED_SIGN_SAFE_DRIVING_SPEED_CHANGE;
import static config.SpeedSimulatorConstants.SPEED_SIGN_SPORT_DRIVING_SPEED_CHANGE;

/**
 * Created by mgupta on 10/22/17.
 */
public class SpeedSignEvent extends SensorEvent {

    private final int sign;

    public SpeedSignEvent(int sign) {
        super(SensorEventTypes.SPEED_SIGN, SensorEventProfile.NONE, SPEED_SIGN_NORMAL_DRIVING_SPEED_CHANGE, SPEED_SIGN_SAFE_DRIVING_SPEED_CHANGE, SPEED_SIGN_SPORT_DRIVING_SPEED_CHANGE);
        this.sign = sign;
    }

    @Override
    public void visit(AutonomousVehicle vehicle) {
        if (vehicle.getActiveEvents().contains(SensorEventTypes.TURBO.name())) {
            vehicle.deactivateEvent(SensorEventTypes.TURBO.name());
        }
        if (vehicle.getDrivingMode().equals(DrivingModes.NORMAL)) {
            vehicle.setSpeed(sign + SPEED_SIGN_NORMAL_DRIVING_SPEED_CHANGE);
        } else if (vehicle.getDrivingMode().equals(DrivingModes.SAFE)) {
            vehicle.setSpeed(sign - SPEED_SIGN_SAFE_DRIVING_SPEED_CHANGE);
        } else {
            vehicle.setSpeed(sign + SPEED_SIGN_SPORT_DRIVING_SPEED_CHANGE);
        }
    }

    public int getSign() {
        return sign;
    }
}
