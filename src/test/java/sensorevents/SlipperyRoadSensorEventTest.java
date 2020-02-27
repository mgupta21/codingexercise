package sensorevents;

import vehicle.AutonomousVehicle;
import vehicle.DrivingModes;
import vehicle.VisitableVehicle;

import org.junit.Assert;
import org.junit.Test;

import static config.SpeedSimulatorConstants.SLIPPERY_ROAD_NORMAL_DRIVING_SPEED_CHANGE;
import static config.SpeedSimulatorConstants.SLIPPERY_ROAD_SAFE_DRIVING_SPEED_CHANGE;
import static config.SpeedSimulatorConstants.SLIPPERY_ROAD_SPORT_DRIVING_SPEED_CHANGE;

/**
 * Created by mgupta on 10/22/17.
 */
public class SlipperyRoadSensorEventTest {

    private VisitableVehicle car;

    @Test
    public void slipperyRoadEventNormalVehicleTest() throws Exception {
        car = getNormalVehicleWithInitialSpeed(60);
        car.accept(getSlipperyRoadEvent());
        Assert.assertEquals(45, car.getSpeed());
    }

    @Test
    public void slipperyRoadEventNormalVehicleIgnoreTest() throws Exception {
        car = getNormalVehicleWithInitialSpeed(60);
        car.accept(getSlipperyRoadEvent());
        Assert.assertEquals(45, car.getSpeed());
        car.accept(getSlipperyRoadEvent());
        Assert.assertEquals(45, car.getSpeed());
    }

    @Test
    public void slipperyRoadEventSafeVehicleTest() throws Exception {
        car = getSafeVehicleWithInitialSpeed(60);
        car.accept(getSlipperyRoadEvent());
        Assert.assertEquals(45, car.getSpeed());
    }

    @Test
    public void slipperyRoadEventSafeVehicleIgnoreTest() throws Exception {
        car = getSafeVehicleWithInitialSpeed(60);
        car.accept(getSlipperyRoadEvent());
        Assert.assertEquals(45, car.getSpeed());
        car.accept(getSlipperyRoadEvent());
        Assert.assertEquals(45, car.getSpeed());
    }

    @Test
    public void slipperyRoadEventSportVehicleTest() throws Exception {
        car = getSportVehicleWithInitialSpeed(60);
        car.accept(getSlipperyRoadEvent());
        Assert.assertEquals(45, car.getSpeed());
    }

    @Test
    public void slipperyRoadEventSportVehicleIgnoreTest() throws Exception {
        car = getSportVehicleWithInitialSpeed(60);
        car.accept(getSlipperyRoadEvent());
        Assert.assertEquals(45, car.getSpeed());
        car.accept(getSlipperyRoadEvent());
        Assert.assertEquals(45, car.getSpeed());
    }

    @Test
    public void slipperyRoadClearEventNormalVehicleIgnoreTest() throws Exception {
        car = getNormalVehicleWithInitialSpeed(60);
        car.accept(getSlipperyRoadClearEvent());
        Assert.assertEquals(60, car.getSpeed());
    }

    @Test
    public void slipperyRoadClearEventNormalVehicleTest() throws Exception {
        car = getNormalVehicleWithInitialSpeed(60);
        car.accept(getSlipperyRoadEvent());
        Assert.assertEquals(45, car.getSpeed());
        car.accept(getSlipperyRoadClearEvent());
        Assert.assertEquals(60, car.getSpeed());
    }

    @Test
    public void slipperyRoadClearEventSafeVehicleIgnoreTest() throws Exception {
        car = getSafeVehicleWithInitialSpeed(60);
        car.accept(getSlipperyRoadClearEvent());
        Assert.assertEquals(60, car.getSpeed());
    }

    @Test
    public void slipperyRoadClearEventSafeVehicleTest() throws Exception {
        car = getSafeVehicleWithInitialSpeed(60);
        car.accept(getSlipperyRoadEvent());
        Assert.assertEquals(45, car.getSpeed());
        car.accept(getSlipperyRoadClearEvent());
        Assert.assertEquals(60, car.getSpeed());
    }

    @Test
    public void slipperyRoadClearEventSportVehicleIgnoreTest() throws Exception {
        car = getSportVehicleWithInitialSpeed(60);
        car.accept(getSlipperyRoadClearEvent());
        Assert.assertEquals(60, car.getSpeed());
    }

    @Test
    public void slipperyRoadClearEventSportVehicleTest() throws Exception {
        car = getSportVehicleWithInitialSpeed(60);
        car.accept(getSlipperyRoadEvent());
        Assert.assertEquals(45, car.getSpeed());
        car.accept(getSlipperyRoadClearEvent());
        Assert.assertEquals(60, car.getSpeed());
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

    private SensorEvent getSlipperyRoadEvent() {
        return new SensorEvent(SensorEventTypes.SLIPPERY_ROAD, SensorEventProfile.ACTIVATE, SLIPPERY_ROAD_NORMAL_DRIVING_SPEED_CHANGE, SLIPPERY_ROAD_SAFE_DRIVING_SPEED_CHANGE,
            SLIPPERY_ROAD_SPORT_DRIVING_SPEED_CHANGE);
    }

    private SensorEvent getSlipperyRoadClearEvent() {
        return new SensorEvent(SensorEventTypes.SLIPPERY_ROAD, SensorEventProfile.DEACTIVATE, SLIPPERY_ROAD_NORMAL_DRIVING_SPEED_CHANGE, SLIPPERY_ROAD_SAFE_DRIVING_SPEED_CHANGE,
            SLIPPERY_ROAD_SPORT_DRIVING_SPEED_CHANGE);
    }

}