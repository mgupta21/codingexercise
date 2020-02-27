package simulate;

import config.BeanFactory;
import exception.InvalidEventException;
import vehicle.VisitableVehicle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import static config.SpeedSimulatorConstants.VALID_DRIVING_MODES;

/**
 * Created by mgupta on 10/22/17.
 */
public class SpeedSimulator {

    private BeanFactory beanFactory;
    private InputStream inputStream;
    private PrintStream outputStream;

    public SpeedSimulator(InputStream inputStream, PrintStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.beanFactory = new BeanFactory();
    }

    public static void main(String[] args) {
        if (args.length == 0 || !isModeValid(args[0])) {
            System.err.println(String.format("Missing or Invalid mode. Accepted modes are : %s", VALID_DRIVING_MODES.toString()));
            System.exit(-1);
        }
        SpeedSimulator speedSimulator = new SpeedSimulator(System.in, System.out);
        speedSimulator.start(args[0]);
        System.exit(0);
    }

    private static boolean isModeValid(String mode) {
        return VALID_DRIVING_MODES.contains(mode.toUpperCase());
    }

    public void start(String drivingMode) {
        VisitableVehicle vehicle = beanFactory.getVehicle(drivingMode);
        outputStream.println("Welcome. Driving mode is " + drivingMode.toUpperCase());
        outputStream.println(vehicle.getSpeed());

        while (true) {
            outputStream.print("Enter Event : ");
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            try {
                final String line = br.readLine();
                if (line == null) {
                    return;
                }
                int eventNumber = Integer.parseInt(line);
                vehicle.accept(beanFactory.getSensorEvent(eventNumber));
                outputStream.println(vehicle.getSpeed());
            } catch (InvalidEventException ex) {
                outputStream.println(ex.getMessage());
            } catch (NumberFormatException ex) {
                outputStream.println("Invalid");
            } catch (IOException e) {
            }
        }

    }
}
