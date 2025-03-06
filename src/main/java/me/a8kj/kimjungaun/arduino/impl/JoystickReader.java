package me.a8kj.kimjungaun.arduino.impl;

import java.io.InputStream;

import com.fazecast.jSerialComm.SerialPort;

import me.a8kj.kimjungaun.arduino.ComponentReader;

public class JoystickReader implements ComponentReader {

    @Override
    public String[] read(SerialPort serialPort) {
        StringBuilder dataBuffer = new StringBuilder();
        String[] joystickData = new String[3];

        try (InputStream inputStream = serialPort.getInputStream()) {
            while (inputStream.available() > 0) {
                char receivedChar = (char) inputStream.read();

                if (receivedChar == '\n') {
                    String processedData = dataBuffer.toString().trim();
                    joystickData = processJoystickData(processedData);
                    dataBuffer.setLength(0);
                } else {
                    dataBuffer.append(receivedChar);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return joystickData;
    }

    private String[] processJoystickData(String data) {
        return data.split(",");
    }
}
