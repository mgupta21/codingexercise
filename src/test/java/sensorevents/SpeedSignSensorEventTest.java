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
public class SpeedSignSensorEventTest {

    @Test
    public void normalVehicleSpeedSignEventTest() throws Exception {
        VisitableVehicle car = getNormalVehicle(100);
        car.accept(new SpeedSignEvent(60));
        Assert.assertEquals(60, car.getSpeed());
    }

    @Test
    public void safeVehicleSpeedSignEventTest() throws Exception {
        VisitableVehicle car = getSafeVehicle(100);
        car.accept(new SpeedSignEvent(60));
        Assert.assertEquals(55, car.getSpeed());
    }

    @Test
    public void sportVehicleSpeedSignEventTest() throws Exception {
        VisitableVehicle car = getSportVehicle(100);
        car.accept(new SpeedSignEvent(60));
        Assert.assertEquals(65, car.getSpeed());
    }

    @Test
    public void normalVehicleSpeedSignEventClearTurboEventTest() throws Exception {
        VisitableVehicle car = getNormalVehicle(100);
        car.accept(new SensorEvent(SensorEventTypes.TURBO, SensorEventProfile.ACTIVATE, TURBO_NORMAL_VEHICLE_SPEED_CHANGE, TURBO_SAFE_DRIVING_SPEED_CHANGE, TURBO_SPORT_DRIVING_SPEED_CHANGE));
        Assert.assertEquals(120, car.getSpeed());
        car.accept(new SpeedSignEvent(60));
        Assert.assertEquals(60, car.getSpeed());
        Assert.assertFalse(car.getActiveEvents().contains(SensorEventTypes.TURBO.name()));
    }

    private VisitableVehicle getNormalVehicle(int initialSpeed) {
        return new AutonomousVehicle(DrivingModes.NORMAL, initialSpeed);
    }

    private AutonomousVehicle getSafeVehicle(int initialSpeed) {
        return new AutonomousVehicle(DrivingModes.SAFE, initialSpeed);
    }

    private AutonomousVehicle getSportVehicle(int initialSpeed) {
        return new AutonomousVehicle(DrivingModes.SPORT, initialSpeed);
    }
}
