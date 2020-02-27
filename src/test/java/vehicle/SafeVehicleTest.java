package vehicle;

import sensorevents.SensorEventTypes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mgupta on 10/22/17.
 */
public class SafeVehicleTest {

    private AutonomousVehicle car;

    @Before
    public void setup() throws Exception {
        this.car = new AutonomousVehicle(DrivingModes.SAFE, 100);
    }

    @Test
    public void safeVehicleDrivingModeTest() throws Exception {
        Assert.assertEquals(DrivingModes.SAFE, this.car.getDrivingMode());
    }

    @Test
    public void safeVehicleInitialSpeedTest() throws Exception {
        Assert.assertEquals(100, this.car.getSpeed());
    }

    @Test
    public void safeVehicleMinSpeedTest() throws Exception {
        this.car.setSpeed(5);
        Assert.assertEquals(10, this.car.getSpeed());
    }

    @Test
    public void safeVehicleActiveEventsTest() {
        Assert.assertTrue(this.car.getActiveEvents().isEmpty());
    }

    @Test
    public void safeVehicleActivateEventsTest() {
        this.car.activateEvent(SensorEventTypes.SLIPPERY_ROAD.name());
        Assert.assertEquals(1, this.car.getActiveEvents().size());
        Assert.assertEquals(SensorEventTypes.SLIPPERY_ROAD.name(), this.car.getActiveEvents().get(0));
    }

    @Test
    public void safeVehicleDeactivateEventsTest() {
        this.car.activateEvent(SensorEventTypes.SLIPPERY_ROAD.name());
        this.car.deactivateEvent(SensorEventTypes.SLIPPERY_ROAD.name());
        Assert.assertEquals(0, this.car.getActiveEvents().size());
    }

}
