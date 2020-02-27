package simulate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mgupta on 10/22/17.
 */
public class SpeedSimulatorTest {

    private SpeedSimulator        speedSimulator;
    private ByteArrayOutputStream outputStream;

    @Before
    public void setup() throws Exception {
        this.outputStream = new ByteArrayOutputStream();
    }

    private SpeedSimulator getSpeedSimulator(String input) {
        return new SpeedSimulator(new ByteArrayInputStream(input.getBytes()), new PrintStream(outputStream, true));
    }

    @Test
    public void trafficEventSpeedSimulatorTest() throws Exception {
        this.speedSimulator = getSpeedSimulator("1\r\n");
        this.speedSimulator.start("Normal");
        Assert.assertTrue(outputStream.toString().endsWith("10\nEnter Event : "));
    }

    @Test
    public void invalidEventSpeedSimulatorTest() throws Exception {
        this.speedSimulator = getSpeedSimulator("9\r\n");
        this.speedSimulator.start("Normal");
        Assert.assertTrue(outputStream.toString().endsWith("Invalid\nEnter Event : "));
    }
}
