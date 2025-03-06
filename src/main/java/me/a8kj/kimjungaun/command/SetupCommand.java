package me.a8kj.kimjungaun.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.a8kj.kimjungaun.Main;

@RequiredArgsConstructor
@Getter
public class SetupCommand implements CommandExecutor {

    private final Main main;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        main.setExplodeX(player.getLocation().getX());
        main.setExplodeY(player.getLocation().getY());
        main.setExplodeZ(player.getLocation().getZ());
        player.sendMessage("Deal!");
        return false;
    }
}
