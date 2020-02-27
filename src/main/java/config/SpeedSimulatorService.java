package config;

import sensorevents.SensorEvent;
import sensorevents.SensorEventProfile;
import sensorevents.SensorEventTypes;
import sensorevents.SensorEventsVisitor;
import sensorevents.SpeedSignEvent;
import vehicle.AutonomousVehicle;
import vehicle.DrivingModes;
import vehicle.VisitableVehicle;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import static config.SpeedSimulatorConstants.INITIAL_VEHICLE_SPEED;
import static config.SpeedSimulatorConstants.SLIPPERY_ROAD_NORMAL_DRIVING_SPEED_CHANGE;
import static config.SpeedSimulatorConstants.SLIPPERY_ROAD_SAFE_DRIVING_SPEED_CHANGE;
import static config.SpeedSimulatorConstants.SLIPPERY_ROAD_SPORT_DRIVING_SPEED_CHANGE;
import static config.SpeedSimulatorConstants.TRAFFIC_NORMAL_DRIVING_SPEED_CHANGE;
import static config.SpeedSimulatorConstants.TRAFFIC_SAFE_DRIVING_SPEED_CHANGE;
import static config.SpeedSimulatorConstants.TRAFFIC_SPORT_DRIVING_SPEED_CHANGE;
import static config.SpeedSimulatorConstants.TURBO_NORMAL_VEHICLE_SPEED_CHANGE;
import static config.SpeedSimulatorConstants.TURBO_SAFE_DRIVING_SPEED_CHANGE;
import static config.SpeedSimulatorConstants.TURBO_SPORT_DRIVING_SPEED_CHANGE;
import static config.SpeedSimulatorConstants.WEATHER_NORMAL_DRIVING_SPEED_CHANGE;
import static config.SpeedSimulatorConstants.WEATHER_SAFE_DRIVING_SPEED_CHANGE;
import static config.SpeedSimulatorConstants.WEATHER_SPORT_DRIVING_SPEED_CHANGE;

/**
 * Created by mgupta on 10/22/17.
 */
@Configuration
public class SpeedSimulatorService {

    private final Map<Integer, SensorEventsVisitor> speedSigns = new HashMap<>(100);

    @Lazy
    @Bean(name = {"normalVehicle"})
    public VisitableVehicle xyz() {
        return new AutonomousVehicle(DrivingModes.NORMAL, INITIAL_VEHICLE_SPEED);
    }

    @Lazy
    @Bean(name = {"safeVehicle"})
    public VisitableVehicle abx() {
        return new AutonomousVehicle(DrivingModes.SAFE, INITIAL_VEHICLE_SPEED);
    }

    @Lazy
    @Bean(name = {"sportVehicle"})
    public VisitableVehicle getSportVehicle() {
        return new AutonomousVehicle(DrivingModes.SPORT, INITIAL_VEHICLE_SPEED);
    }

    @Lazy
    @Bean(name = {"trafficEvent"})
    public SensorEventsVisitor getTrafficEvent() {
        return new SensorEvent(SensorEventTypes.TRAFFIC, SensorEventProfile.ACTIVATE, TRAFFIC_NORMAL_DRIVING_SPEED_CHANGE, TRAFFIC_SAFE_DRIVING_SPEED_CHANGE, TRAFFIC_SPORT_DRIVING_SPEED_CHANGE);
    }

    @Lazy
    @Bean(name = {"trafficClearEvent"})
    public SensorEventsVisitor getTrafficClearEvent() {
        return new SensorEvent(SensorEventTypes.TRAFFIC, SensorEventProfile.DEACTIVATE, TRAFFIC_NORMAL_DRIVING_SPEED_CHANGE, TRAFFIC_SAFE_DRIVING_SPEED_CHANGE, TRAFFIC_SPORT_DRIVING_SPEED_CHANGE);
    }

    @Lazy
    @Bean(name = {"weatherEvent"})
    public SensorEventsVisitor getWeatherEvent() {
        return new SensorEvent(SensorEventTypes.WEATHER, SensorEventProfile.ACTIVATE, WEATHER_NORMAL_DRIVING_SPEED_CHANGE, WEATHER_SAFE_DRIVING_SPEED_CHANGE, WEATHER_SPORT_DRIVING_SPEED_CHANGE);
    }

    @Lazy
    @Bean(name = {"weatherClearEvent"})
    public SensorEventsVisitor getWeatherClearEvent() {
        return new SensorEvent(SensorEventTypes.WEATHER, SensorEventProfile.DEACTIVATE, WEATHER_NORMAL_DRIVING_SPEED_CHANGE, WEATHER_SAFE_DRIVING_SPEED_CHANGE, WEATHER_SPORT_DRIVING_SPEED_CHANGE);
    }

    @Lazy
    @Bean(name = {"slipperyRoadEvent"})
    public SensorEventsVisitor getSlipperyRoadEvent() {
        return new SensorEvent(SensorEventTypes.SLIPPERY_ROAD, SensorEventProfile.ACTIVATE, SLIPPERY_ROAD_NORMAL_DRIVING_SPEED_CHANGE, SLIPPERY_ROAD_SAFE_DRIVING_SPEED_CHANGE,
            SLIPPERY_ROAD_SPORT_DRIVING_SPEED_CHANGE);
    }

    @Lazy
    @Bean(name = {"slipperyRoadClearEvent"})
    public SensorEventsVisitor getSlipperyRoadClearEvent() {
        return new SensorEvent(SensorEventTypes.SLIPPERY_ROAD, SensorEventProfile.DEACTIVATE, SLIPPERY_ROAD_NORMAL_DRIVING_SPEED_CHANGE, SLIPPERY_ROAD_SAFE_DRIVING_SPEED_CHANGE,
            SLIPPERY_ROAD_SPORT_DRIVING_SPEED_CHANGE);
    }

    @Lazy
    @Bean(name = {"emergencyTurboEvent"})
    public SensorEventsVisitor getEmergencyTurboEvent() {
        return new SensorEvent(SensorEventTypes.TURBO, SensorEventProfile.ACTIVATE, TURBO_NORMAL_VEHICLE_SPEED_CHANGE, TURBO_SAFE_DRIVING_SPEED_CHANGE, TURBO_SPORT_DRIVING_SPEED_CHANGE);
    }

    @Lazy
    @Bean(name = {"speedSignEvent"})
    public Map<Integer, SensorEventsVisitor> getSpeedSignEvents() {
        if (speedSigns.isEmpty()) {
            IntStream.range(10, 101).forEach(i -> speedSigns.put(i, new SpeedSignEvent(i)));
        }
        return speedSigns;
    }

}
