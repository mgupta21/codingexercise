package config;

import sensorevents.SensorEventsVisitor;
import exception.InvalidEventException;
import vehicle.VisitableVehicle;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static config.SpeedSimulatorConstants.VALID_EVENT_NUMBERS;

/**
 * Created by mgupta on 10/22/17.
 */
public class BeanFactory {

    private final AnnotationConfigApplicationContext actx;
    public static Map<Integer, String>               eventIdToBeanNamesMap = new HashMap();

    static {
        eventIdToBeanNamesMap.put(1, "trafficEvent");
        eventIdToBeanNamesMap.put(2, "trafficClearEvent");
        eventIdToBeanNamesMap.put(3, "weatherEvent");
        eventIdToBeanNamesMap.put(4, "weatherClearEvent");
        eventIdToBeanNamesMap.put(5, "slipperyRoadEvent");
        eventIdToBeanNamesMap.put(6, "slipperyRoadClearEvent");
        eventIdToBeanNamesMap.put(7, "emergencyTurboEvent");
        IntStream.range(9, 101).forEach(i -> eventIdToBeanNamesMap.put(i, "speedSignEvent"));
    }

    public BeanFactory() {
        this.actx = new AnnotationConfigApplicationContext(SpeedSimulatorService.class);
    }

    private String lookUpEventBeanName(int eventId) {
        return eventIdToBeanNamesMap.get(eventId);
    }

    public SensorEventsVisitor getSensorEvent(int eventNumber) {
        if (!VALID_EVENT_NUMBERS.contains(eventNumber)) {
            throw new InvalidEventException("Invalid");
        }
        if (eventNumber > 9) {
            final Map<Integer, SensorEventsVisitor> signEvents = (Map<Integer, SensorEventsVisitor>) actx.getBean(lookUpEventBeanName(eventNumber));
            return signEvents.get(eventNumber);
        }
        return (SensorEventsVisitor) this.actx.getBean(lookUpEventBeanName(eventNumber));
    }

    public VisitableVehicle getVehicle(String drivingMode) {
        return (VisitableVehicle) this.actx.getBean(drivingMode.toLowerCase() + "Vehicle");
    }

}
