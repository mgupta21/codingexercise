package vehicle;

import sensorevents.SensorEventTypes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mgupta on 10/22/17.
 */
public class NormalVehicleTest {

    private AutonomousVehicle car;

    @Before
    public void setup() throws Exception {
        this.car = new AutonomousVehicle(DrivingModes.NORMAL, 100);
    }

    @Test
    public void normalVehicleDrivingModeTest() throws Exception {
        Assert.assertEquals(DrivingModes.NORMAL, this.car.getDrivingMode());
    }

    @Test
    public void normalVehicleInitialSpeedTest() throws Exception {
        Assert.assertEquals(100, this.car.getSpeed());
    }

    @Test
    public void normalVehicleMinSpeedTest() throws Exception {
        this.car.setSpeed(5);
        Assert.assertEquals(10, this.car.getSpeed());
    }

    @Test
    public void normalVehicleActiveEventsTest() {
        Assert.assertTrue(this.car.getActiveEvents().isEmpty());
    }

    @Test
    public void normalVehicleActivateEventsTest() {
        this.car.activateEvent(SensorEventTypes.TRAFFIC.name());
        Assert.assertEquals(1, this.car.getActiveEvents().size());
        Assert.assertEquals(SensorEventTypes.TRAFFIC.name(), this.car.getActiveEvents().get(0));
    }

    @Test
    public void normalVehicleDeactivateEventsTest() {
        this.car.activateEvent(SensorEventTypes.TRAFFIC.name());
        this.car.deactivateEvent(SensorEventTypes.TRAFFIC.name());
        Assert.assertEquals(0, this.car.getActiveEvents().size());
    }
}
