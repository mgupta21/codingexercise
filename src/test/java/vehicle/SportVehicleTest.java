package vehicle;

import sensorevents.SensorEventTypes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mgupta on 10/22/17.
 */
public class SportVehicleTest {

    private AutonomousVehicle car;

    @Before
    public void setup() throws Exception {
        this.car = new AutonomousVehicle(DrivingModes.SPORT, 80);
    }

    @Test
    public void sportVehicleDrivingModeTest() throws Exception {
        Assert.assertEquals(DrivingModes.SPORT, this.car.getDrivingMode());
    }

    @Test
    public void sportVehicleInitialSpeedTest() throws Exception {
        Assert.assertEquals(80, this.car.getSpeed());
    }

    @Test
    public void sportVehicleMinSpeedTest() throws Exception {
        this.car.setSpeed(0);
        Assert.assertEquals(10, this.car.getSpeed());
    }

    @Test
    public void sportVehicleActiveEventsTest() {
        Assert.assertTrue(this.car.getActiveEvents().isEmpty());
    }

    @Test
    public void sportVehicleActivateEventsTest() {
        this.car.activateEvent(SensorEventTypes.WEATHER.name());
        Assert.assertEquals(1, this.car.getActiveEvents().size());
        Assert.assertEquals(SensorEventTypes.WEATHER.name(), this.car.getActiveEvents().get(0));
    }

    @Test
    public void sportVehicleDeactivateEventsTest() {
        this.car.activateEvent(SensorEventTypes.WEATHER.name());
        this.car.deactivateEvent(SensorEventTypes.WEATHER.name());
        Assert.assertEquals(0, this.car.getActiveEvents().size());
    }

}
