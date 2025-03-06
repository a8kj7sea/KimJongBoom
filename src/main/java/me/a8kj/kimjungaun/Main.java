package me.a8kj.kimjungaun;

import org.bukkit.plugin.java.JavaPlugin;
import lombok.Getter;
import lombok.Setter;
import me.a8kj.kimjungaun.arduino.ArduinoConnector;
import me.a8kj.kimjungaun.arduino.impl.ArduinoUNOConnector;
import me.a8kj.kimjungaun.command.SetupCommand;
import me.a8kj.kimjungaun.listener.JoystickListener;
import me.a8kj.kimjungaun.tasks.JoystickReceiverTask;

public class Main extends JavaPlugin {

    @Getter
    private static Main instance;

    @Setter
    @Getter
    private double explodeX, explodeY, explodeZ;

    @Getter
    private ArduinoConnector connector;

    @Override
    public void onEnable() {
        instance = this;

        this.getCommand("setup").setExecutor(new SetupCommand(this));
        this.getServer().getPluginManager().registerEvents(new JoystickListener(this), this);

        connector = new ArduinoUNOConnector();
        connector.connect();
        if (connector.isConnected()) {
            System.err.println("Arduino connected successfully!");
            new JoystickReceiverTask(connector).runTaskTimer(this, 0, 2);

        } else {
            System.err.println("Failed to connect to Arduino.");
        }
    }

    @Override
    public void onDisable() {
        if (connector != null && connector.isConnected()) {
            connector.getSerialPort().closePort();
            System.err.println("Arduino connection closed.");
        }
    }
}
