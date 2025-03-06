package me.a8kj.kimjungaun.arduino.impl;

import com.fazecast.jSerialComm.SerialPort;

import lombok.Getter;
import me.a8kj.kimjungaun.arduino.ArduinoConnector;

@Getter
public class ArduinoUNOConnector implements ArduinoConnector {

    @Override
    public int getBaudRate() {
        return 9600;
    }

    @Override
    public String getDeviceName() {
        return "Arduino UNO R3";
    }

    @Override
    public String getPort() {
        return "COM4";
    }

    private boolean connected;
    private SerialPort serialPort;

    @Override
    public void connect() {
        serialPort = SerialPort.getCommPort(getPort());
        serialPort.setBaudRate(getBaudRate());

        if (serialPort.openPort()) {
            connected = true;
            System.out.println("Connected to Arduino!");
        } else {
            connected = false;
            System.out.println("Failed to connect to Arduino.");
        }
    }

    @Override
    public void disconnect() {
        if (serialPort != null && serialPort.isOpen()) {
            serialPort.closePort();
            connected = false;
            System.out.println("Disconnected from Arduino.");
        } else {
            System.out.println("No active connection to disconnect.");
        }
    }

}
