package config;

import sensorevents.SensorEventProfile;
import sensorevents.SensorEventTypes;
import sensorevents.SensorEventsVisitor;
import sensorevents.SpeedSignEvent;
import exception.InvalidEventException;
import vehicle.DrivingModes;
import vehicle.VisitableVehicle;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mgupta on 10/22/17.
 */
public class BeanFactoryTest {

    private BeanFactory factory;

    @Before
    public void setup() throws Exception {
        factory = new BeanFactory();
    }

    @Test
    public void trafficEventBeanTest() throws Exception {
        final SensorEventsVisitor sensorEvent = factory.getSensorEvent(1);
        Assert.assertNotNull(sensorEvent);
        Assert.assertEquals(SensorEventTypes.TRAFFIC, sensorEvent.getEventType());
        Assert.assertEquals(SensorEventProfile.ACTIVATE, sensorEvent.getEventProfile());
    }

    @Test
    public void trafficClearEventBeanTest() throws Exception {
        final SensorEventsVisitor sensorEvent = factory.getSensorEvent(2);
        Assert.assertNotNull(sensorEvent);
        Assert.assertEquals(SensorEventTypes.TRAFFIC, sensorEvent.getEventType());
        Assert.assertEquals(SensorEventProfile.DEACTIVATE, sensorEvent.getEventProfile());
    }

    @Test
    public void weatherEventBeanTest() throws Exception {
        final SensorEventsVisitor sensorEvent = factory.getSensorEvent(3);
        Assert.assertNotNull(sensorEvent);
        Assert.assertEquals(SensorEventTypes.WEATHER, sensorEvent.getEventType());
        Assert.assertEquals(SensorEventProfile.ACTIVATE, sensorEvent.getEventProfile());
    }

    @Test
    public void weatherClearEventBeanTest() throws Exception {
        final SensorEventsVisitor sensorEvent = factory.getSensorEvent(4);
        Assert.assertNotNull(sensorEvent);
        Assert.assertEquals(SensorEventTypes.WEATHER, sensorEvent.getEventType());
        Assert.assertEquals(SensorEventProfile.DEACTIVATE, sensorEvent.getEventProfile());
    }

    @Test
    public void slipperyRoadEventBeanTest() throws Exception {
        final SensorEventsVisitor sensorEvent = factory.getSensorEvent(5);
        Assert.assertNotNull(sensorEvent);
        Assert.assertEquals(SensorEventTypes.SLIPPERY_ROAD, sensorEvent.getEventType());
        Assert.assertEquals(SensorEventProfile.ACTIVATE, sensorEvent.getEventProfile());
    }

    @Test
    public void slipperyRoadClearEventBeanTest() throws Exception {
        final SensorEventsVisitor sensorEvent = factory.getSensorEvent(6);
        Assert.assertNotNull(sensorEvent);
        Assert.assertEquals(SensorEventTypes.SLIPPERY_ROAD, sensorEvent.getEventType());
        Assert.assertEquals(SensorEventProfile.DEACTIVATE, sensorEvent.getEventProfile());
    }

    @Test
    public void emergencyTurboEventBeanTest() throws Exception {
        final SensorEventsVisitor sensorEvent = factory.getSensorEvent(7);
        Assert.assertNotNull(sensorEvent);
        Assert.assertEquals(SensorEventTypes.TURBO, sensorEvent.getEventType());
        Assert.assertEquals(SensorEventProfile.ACTIVATE, sensorEvent.getEventProfile());
    }

    @Test
    public void speedSignEventTest() throws Exception {
        final SensorEventsVisitor sensorEvent = factory.getSensorEvent(60);
        Assert.assertNotNull(sensorEvent);
        Assert.assertTrue(sensorEvent instanceof SpeedSignEvent);
        final SpeedSignEvent speedSign = (SpeedSignEvent) sensorEvent;
        Assert.assertEquals(60, speedSign.getSign());
    }

    @Test(expected = InvalidEventException.class)
    public void invalidEventTest() throws Exception {
        factory.getSensorEvent(8);
    }

    @Test(expected = InvalidEventException.class)
    public void invalidEventTest2() throws Exception {
        factory.getSensorEvent(101);
    }

    @Test(expected = InvalidEventException.class)
    public void invalidEventTest3() throws Exception {
        factory.getSensorEvent(-1);
    }

    @Test
    public void normalVehicleBeanTest() throws Exception {
        final VisitableVehicle normalVehicle = factory.getVehicle("normal");
        Assert.assertNotNull(normalVehicle);
        Assert.assertEquals(DrivingModes.NORMAL, normalVehicle.getDrivingMode());
        Assert.assertEquals(20, normalVehicle.getSpeed());
    }

    @Test
    public void safeVehicleBeanTest() throws Exception {
        final VisitableVehicle safeVehicle = factory.getVehicle("safe");
        Assert.assertNotNull(safeVehicle);
        Assert.assertEquals(DrivingModes.SAFE, safeVehicle.getDrivingMode());
        Assert.assertEquals(20, safeVehicle.getSpeed());
    }

    @Test
    public void sportVehicleBeanTest() throws Exception {
        final VisitableVehicle sportVehicle = factory.getVehicle("sport");
        Assert.assertNotNull(sportVehicle);
        Assert.assertEquals(DrivingModes.SPORT, sportVehicle.getDrivingMode());
        Assert.assertEquals(20, sportVehicle.getSpeed());
    }
}
