package sensorevents;

import vehicle.AutonomousVehicle;
import vehicle.DrivingModes;
import vehicle.VisitableVehicle;

import org.junit.Assert;
import org.junit.Test;

import static config.SpeedSimulatorConstants.TURBO_NORMAL_VEHICLE_SPEED_CHANGE;
import static config.SpeedSimulatorConstants.TURBO_SAFE_DRIVING_SPEED_CHANGE;
import static config.SpeedSimulatorConstants.TURBO_SPORT_DRIVING_SPEED_CHANGE;

/**
 * Created by mgupta on 10/22/17.
 */
public class EmergencyTurboSensorEventTest {

    @Test
    public void normalVehicleEmergencyTurboEventTest() throws Exception {
        VisitableVehicle car = getNormalVehicle(100);
        car.accept(getEmergencyTurboEvent());
        Assert.assertEquals(120, car.getSpeed());
    }

    @Test
    public void safeVehicleEmergencyTurboEventTest() throws Exception {
        VisitableVehicle car = getSafeVehicle(100);
        car.accept(getEmergencyTurboEvent());
        Assert.assertEquals(110, car.getSpeed());
    }

    @Test
    public void sportVehicleEmergencyTurboEventTest() throws Exception {
        VisitableVehicle car = getSportVehicle(100);
        car.accept(getEmergencyTurboEvent());
        Assert.assertEquals(130, car.getSpeed());
    }

    @Test
    public void normalVehicleDoubleEmergencyTurboTest() throws Exception {
        VisitableVehicle car = getNormalVehicle(100);
        car.accept(getEmergencyTurboEvent());
        car.accept(getEmergencyTurboEvent());
        Assert.assertEquals(120, car.getSpeed());
    }

    @Test
    public void safeVehicleDoubleEmergencyTurboTest() throws Exception {
        VisitableVehicle car = getSafeVehicle(100);
        car.accept(getEmergencyTurboEvent());
        car.accept(getEmergencyTurboEvent());
        Assert.assertEquals(110, car.getSpeed());
    }

    @Test
    public void sportVehicleDoubleEmergencyTurboTest() throws Exception {
        VisitableVehicle car = getSportVehicle(100);
        car.accept(getEmergencyTurboEvent());
        car.accept(getEmergencyTurboEvent());
        Assert.assertEquals(130, car.getSpeed());
    }

    private VisitableVehicle getNormalVehicle(int initialSpeed) {
        return new AutonomousVehicle(DrivingModes.NORMAL, initialSpeed);
    }

    private VisitableVehicle getSportVehicle(int initialSpeed) {
        return new AutonomousVehicle(DrivingModes.SPORT, initialSpeed);
    }

    private VisitableVehicle getSafeVehicle(int initialSpeed) {
        return new AutonomousVehicle(DrivingModes.SAFE, initialSpeed);
    }

    private SensorEvent getEmergencyTurboEvent() {
        return new SensorEvent(SensorEventTypes.TURBO, SensorEventProfile.ACTIVATE, TURBO_NORMAL_VEHICLE_SPEED_CHANGE, TURBO_SAFE_DRIVING_SPEED_CHANGE, TURBO_SPORT_DRIVING_SPEED_CHANGE);
    }

}
