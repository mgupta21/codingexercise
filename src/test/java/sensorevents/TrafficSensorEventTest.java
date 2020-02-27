package sensorevents;

import vehicle.AutonomousVehicle;
import vehicle.DrivingModes;
import vehicle.VisitableVehicle;

import org.junit.Assert;
import org.junit.Test;

import static config.SpeedSimulatorConstants.TRAFFIC_NORMAL_DRIVING_SPEED_CHANGE;
import static config.SpeedSimulatorConstants.TRAFFIC_SAFE_DRIVING_SPEED_CHANGE;
import static config.SpeedSimulatorConstants.TRAFFIC_SPORT_DRIVING_SPEED_CHANGE;

/**
 * Created by mgupta on 10/22/17.
 */
public class TrafficSensorEventTest {

    private VisitableVehicle car;

    @Test
    public void trafficEventNormalVehicleTest() throws Exception {
        car = getNormalVehicleWithInitialSpeed(100);
        car.accept(getTrafficEventVisitor());
        Assert.assertEquals(90, car.getSpeed());
    }

    @Test
    public void trafficEventNormalVehicleIgnoreTest() throws Exception {
        car = getNormalVehicleWithInitialSpeed(100);
        car.accept(getTrafficEventVisitor());
        Assert.assertEquals(90, car.getSpeed());
        car.accept(getTrafficEventVisitor());
        Assert.assertEquals(90, car.getSpeed());
    }

    @Test
    public void trafficEventSafeVehicleTest() throws Exception {
        car = getSafeVehicleWithInitialSpeed(100);
        car.accept(getTrafficEventVisitor());
        Assert.assertEquals(85, car.getSpeed());
    }

    @Test
    public void trafficEventSafeVehicleIgnoreTest() throws Exception {
        car = getSafeVehicleWithInitialSpeed(100);
        car.accept(getTrafficEventVisitor());
        Assert.assertEquals(85, car.getSpeed());
        car.accept(getTrafficEventVisitor());
        Assert.assertEquals(85, car.getSpeed());
    }

    @Test
    public void trafficEventSportVehicleTest() throws Exception {
        car = getSportVehicleWithInitialSpeed(100);
        car.accept(getTrafficEventVisitor());
        Assert.assertEquals(95, car.getSpeed());
    }

    @Test
    public void trafficEventSportVehicleIgnoreTest() throws Exception {
        car = getSportVehicleWithInitialSpeed(100);
        car.accept(getTrafficEventVisitor());
        Assert.assertEquals(95, car.getSpeed());
        car.accept(getTrafficEventVisitor());
        Assert.assertEquals(95, car.getSpeed());
    }

    @Test
    public void trafficClearEventNormalVehicleIgnoreTest() throws Exception {
        car = getNormalVehicleWithInitialSpeed(100);
        car.accept(getTrafficClearEventVisitor());
        Assert.assertEquals(100, car.getSpeed());
    }

    @Test
    public void trafficClearEventNormalVehicleTest() throws Exception {
        car = getNormalVehicleWithInitialSpeed(100);
        car.accept(getTrafficEventVisitor());
        Assert.assertEquals(90, car.getSpeed());
        car.accept(getTrafficClearEventVisitor());
        Assert.assertEquals(100, car.getSpeed());
    }

    @Test
    public void trafficClearEventSafeVehicleIgnoreTest() throws Exception {
        car = getSafeVehicleWithInitialSpeed(100);
        car.accept(getTrafficClearEventVisitor());
        Assert.assertEquals(100, car.getSpeed());
    }

    @Test
    public void trafficClearEventSafeVehicleTest() throws Exception {
        car = getSafeVehicleWithInitialSpeed(100);
        car.accept(getTrafficEventVisitor());
        Assert.assertEquals(85, car.getSpeed());
        car.accept(getTrafficClearEventVisitor());
        Assert.assertEquals(100, car.getSpeed());
    }

    @Test
    public void trafficClearEventSportVehicleIgnoreTest() throws Exception {
        car = getSportVehicleWithInitialSpeed(100);
        car.accept(getTrafficClearEventVisitor());
        Assert.assertEquals(100, car.getSpeed());
    }

    @Test
    public void trafficClearEventSportVehicleTest() throws Exception {
        car = getSportVehicleWithInitialSpeed(100);
        car.accept(getTrafficEventVisitor());
        Assert.assertEquals(95, car.getSpeed());
        car.accept(getTrafficClearEventVisitor());
        Assert.assertEquals(100, car.getSpeed());
    }

    private AutonomousVehicle getNormalVehicleWithInitialSpeed(int initialSpeed) {
        return new AutonomousVehicle(DrivingModes.NORMAL, initialSpeed);
    }

    private AutonomousVehicle getSafeVehicleWithInitialSpeed(int initialSpeed) {
        return new AutonomousVehicle(DrivingModes.SAFE, initialSpeed);
    }

    private AutonomousVehicle getSportVehicleWithInitialSpeed(int initialSpeed) {
        return new AutonomousVehicle(DrivingModes.SPORT, initialSpeed);
    }

    private SensorEvent getTrafficEventVisitor() {
        return new SensorEvent(SensorEventTypes.TRAFFIC, SensorEventProfile.ACTIVATE, TRAFFIC_NORMAL_DRIVING_SPEED_CHANGE, TRAFFIC_SAFE_DRIVING_SPEED_CHANGE, TRAFFIC_SPORT_DRIVING_SPEED_CHANGE);
    }

    private SensorEvent getTrafficClearEventVisitor() {
        return new SensorEvent(SensorEventTypes.TRAFFIC, SensorEventProfile.DEACTIVATE, TRAFFIC_NORMAL_DRIVING_SPEED_CHANGE, TRAFFIC_SAFE_DRIVING_SPEED_CHANGE, TRAFFIC_SPORT_DRIVING_SPEED_CHANGE);
    }

}
