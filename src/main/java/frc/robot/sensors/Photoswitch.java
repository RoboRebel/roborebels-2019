package frc.robot.sensors;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.interfaces.TestableSensor;

public class Photoswitch extends DigitalInput implements TestableSensor {
    private int onCount = 0;
    private int offCount = 0;
    private final int port;

    /**
     * Create an instance of a Digital Input class. Creates a digital input given a channel.
     *
     * @param channel the DIO channel for the digital input 0-9 are on-board, 10-25 are on the MXP
     */
    public Photoswitch(int channel) {
        super(channel);
        port = channel;
    }

    @Override
    public void resetTest() {
        onCount = 0;
        offCount = 0;
    }

    @Override
    public void test() {
        if(!this.get())
            onCount++;
        else
            offCount++;
    }

    @Override
    public String getStatus() {
//        this.resetTest();
        return String.format("Photoswitch on port %d had %d on counts and %d off counts", port, onCount, offCount);
    }
}
