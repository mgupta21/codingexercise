package sensorevents;

import vehicle.AutonomousVehicle;
import vehicle.DrivingModes;
import vehicle.VisitableVehicle;

import org.junit.Assert;
import org.junit.Test;

import static config.SpeedSimulatorConstants.WEATHER_NORMAL_DRIVING_SPEED_CHANGE;
import static config.SpeedSimulatorConstants.WEATHER_SAFE_DRIVING_SPEED_CHANGE;
import static config.SpeedSimulatorConstants.WEATHER_SPORT_DRIVING_SPEED_CHANGE;

/**
 * Created by mgupta on 10/22/17.
 */
public class WeatherSensorEventTest {

    private VisitableVehicle car;

    @Test
    public void weatherEventNormalVehicleTest() throws Exception {
        car = getNormalVehicleWithInitialSpeed(40);
        car.accept(getWeatherEvent());
        Assert.assertEquals(35, car.getSpeed());
    }

    @Test
    public void weatherEventNormalVehicleIgnoreTest() throws Exception {
        car = getNormalVehicleWithInitialSpeed(40);
        car.accept(getWeatherEvent());
        Assert.assertEquals(35, car.getSpeed());
        car.accept(getWeatherEvent());
        Assert.assertEquals(35, car.getSpeed());
    }

    @Test
    public void weatherEventSafeVehicleTest() throws Exception {
        car = getSafeVehicleWithInitialSpeed(40);
        car.accept(getWeatherEvent());
        Assert.assertEquals(35, car.getSpeed());
    }

    @Test
    public void weatherEventSafeVehicleIgnoreTest() throws Exception {
        car = getSafeVehicleWithInitialSpeed(40);
        car.accept(getWeatherEvent());
        Assert.assertEquals(35, car.getSpeed());
        car.accept(getWeatherEvent());
        Assert.assertEquals(35, car.getSpeed());
    }

    @Test
    public void weatherEventSportVehicleTest() throws Exception {
        car = getSportVehicleWithInitialSpeed(40);
        car.accept(getWeatherEvent());
        Assert.assertEquals(35, car.getSpeed());
    }

    @Test
    public void weatherEventSportVehicleIgnoreTest() throws Exception {
        car = getSportVehicleWithInitialSpeed(40);
        car.accept(getWeatherEvent());
        Assert.assertEquals(35, car.getSpeed());
        car.accept(getWeatherEvent());
        Assert.assertEquals(35, car.getSpeed());
    }

    @Test
    public void weatherClearEventNormalVehicleIgnoreTest() throws Exception {
        car = getNormalVehicleWithInitialSpeed(40);
        car.accept(getWeatherClearEvent());
        Assert.assertEquals(40, car.getSpeed());
    }

    @Test
    public void weatherClearEventNormalVehicleTest() throws Exception {
        car = getNormalVehicleWithInitialSpeed(40);
        car.accept(getWeatherEvent());
        Assert.assertEquals(35, car.getSpeed());
        car.accept(getWeatherClearEvent());
        Assert.assertEquals(40, car.getSpeed());
    }

    @Test
    public void weatherClearEventSafeVehicleIgnoreTest() throws Exception {
        car = getSafeVehicleWithInitialSpeed(40);
        car.accept(getWeatherClearEvent());
        Assert.assertEquals(40, car.getSpeed());
    }

    @Test
    public void weatherClearEventSafeVehicleTest() throws Exception {
        car = getSafeVehicleWithInitialSpeed(40);
        car.accept(getWeatherEvent());
        Assert.assertEquals(35, car.getSpeed());
        car.accept(getWeatherClearEvent());
        Assert.assertEquals(40, car.getSpeed());
    }

    @Test
    public void weatherClearEventSportVehicleIgnoreTest() throws Exception {
        car = getSportVehicleWithInitialSpeed(40);
        car.accept(getWeatherClearEvent());
        Assert.assertEquals(40, car.getSpeed());
    }

    @Test
    public void weatherClearEventSportVehicleTest() throws Exception {
        car = getSportVehicleWithInitialSpeed(40);
        car.accept(getWeatherEvent());
        Assert.assertEquals(35, car.getSpeed());
        car.accept(getWeatherClearEvent());
        Assert.assertEquals(40, car.getSpeed());
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

    private SensorEvent getWeatherEvent() {
        return new SensorEvent(SensorEventTypes.WEATHER, SensorEventProfile.ACTIVATE, WEATHER_NORMAL_DRIVING_SPEED_CHANGE, WEATHER_SAFE_DRIVING_SPEED_CHANGE, WEATHER_SPORT_DRIVING_SPEED_CHANGE);
    }

    private SensorEvent getWeatherClearEvent() {
        return new SensorEvent(SensorEventTypes.WEATHER, SensorEventProfile.DEACTIVATE, WEATHER_NORMAL_DRIVING_SPEED_CHANGE, WEATHER_SAFE_DRIVING_SPEED_CHANGE, WEATHER_SPORT_DRIVING_SPEED_CHANGE);
    }

}
