package config;

import vehicle.DrivingModes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by mgupta on 10/22/17.
 */
public final class SpeedSimulatorConstants {

    public static final int           INITIAL_VEHICLE_SPEED = 20;
    public static final List<String>  VALID_DRIVING_MODES   = Stream.of(DrivingModes.values()).map(e -> e.name()).collect(toList());

    public static final List<Integer> VALID_EVENT_NUMBERS;

    static {
        VALID_EVENT_NUMBERS = new ArrayList<>();
        VALID_EVENT_NUMBERS.addAll(IntStream.range(1, 8).mapToObj(i -> i).collect(toList()));
        VALID_EVENT_NUMBERS.addAll(IntStream.range(10, 101).mapToObj(i -> i).collect(toList()));
    }

    public static final int TURBO_NORMAL_VEHICLE_SPEED_CHANGE         = 20;
    public static final int TURBO_SAFE_DRIVING_SPEED_CHANGE           = 10;
    public static final int TURBO_SPORT_DRIVING_SPEED_CHANGE          = 30;

    public static final int SLIPPERY_ROAD_NORMAL_DRIVING_SPEED_CHANGE = 15;
    public static final int SLIPPERY_ROAD_SAFE_DRIVING_SPEED_CHANGE   = 15;
    public static final int SLIPPERY_ROAD_SPORT_DRIVING_SPEED_CHANGE  = 15;

    public static final int TRAFFIC_NORMAL_DRIVING_SPEED_CHANGE       = 10;
    public static final int TRAFFIC_SAFE_DRIVING_SPEED_CHANGE         = 15;
    public static final int TRAFFIC_SPORT_DRIVING_SPEED_CHANGE        = 5;

    public static final int WEATHER_NORMAL_DRIVING_SPEED_CHANGE       = 5;
    public static final int WEATHER_SAFE_DRIVING_SPEED_CHANGE         = 5;
    public static final int WEATHER_SPORT_DRIVING_SPEED_CHANGE        = 5;

    public static final int SPEED_SIGN_NORMAL_DRIVING_SPEED_CHANGE    = 0;
    public static final int SPEED_SIGN_SAFE_DRIVING_SPEED_CHANGE      = 5;
    public static final int SPEED_SIGN_SPORT_DRIVING_SPEED_CHANGE     = 5;

}
