package me.a8kj.kimjungaun.tasks;

import org.bukkit.scheduler.BukkitRunnable;

import me.a8kj.kimjungaun.api.JoystickInteractEvent;
import me.a8kj.kimjungaun.api.JoystickInteractEvent.ButtonState;
import me.a8kj.kimjungaun.arduino.ArduinoConnector;
import me.a8kj.kimjungaun.arduino.impl.JoystickReader;

public class JoystickReceiverTask extends BukkitRunnable {
    private final ArduinoConnector connector;

    public JoystickReceiverTask(ArduinoConnector connector) {
        this.connector = connector;
    }

    @Override
    public void run() {
        if (connector.isConnected()) {
            JoystickReader reader = new JoystickReader();

            String[] joystickData = reader.read(connector.getSerialPort());

            if (joystickData != null && joystickData.length == 3) {

                String x = joystickData[0];
                String y = joystickData[1];
                String buttonState = joystickData[2];

                new JoystickInteractEvent(ButtonState.getStateByValue(buttonState), x, y).callEvent();
            } else {
                System.err.println("Invalid joystick data received (expected 3 values): "
                        + (joystickData == null ? "null" : joystickData.length + " elements"));
            }
        }
    }
}
