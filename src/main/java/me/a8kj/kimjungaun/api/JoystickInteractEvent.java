package me.a8kj.kimjungaun.api;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class JoystickInteractEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    @RequiredArgsConstructor
    @Getter
    public enum ButtonState {
        OFF("1"), ON("0");

        private final String val;

        public static ButtonState getStateByValue(String val) {
            return val.equalsIgnoreCase("0") ? ButtonState.ON : ButtonState.OFF;
        }

    }

    private final ButtonState buttonState;
    private final String x, y;

    public boolean isClicked() {
        return buttonState == ButtonState.ON;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public void callEvent() {
        Bukkit.getPluginManager().callEvent(this);
    }
}
