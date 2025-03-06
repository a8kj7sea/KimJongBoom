package me.a8kj.kimjungaun.arduino;

import com.fazecast.jSerialComm.SerialPort;

public interface ArduinoConnector {

    int getBaudRate();

    String getDeviceName();

    void connect();

    SerialPort getSerialPort();

    boolean isConnected();

    void disconnect();

    String getPort();

}
