package me.a8kj.kimjungaun.arduino;

import com.fazecast.jSerialComm.SerialPort;

public interface ComponentReader {

    String[] read(SerialPort serialPort);
}
