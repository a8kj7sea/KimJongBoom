package me.a8kj.kimjungaun.listener;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.a8kj.kimjungaun.Main;
import me.a8kj.kimjungaun.api.JoystickInteractEvent;

@RequiredArgsConstructor
@Getter
public class JoystickListener implements Listener {

    private final Main main;

    @EventHandler
    public void onClickAtJoyStickButton(JoystickInteractEvent event) {
        if (event.isClicked()) {
            double x = main.getExplodeX();
            double y = main.getExplodeY();
            double z = main.getExplodeZ();
            startExplode(x, y, z);
        }
    }

    private void startExplode(double x, double y, double z) {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    double offsetX = (Math.random() - 0.5) * 1500;
                    double offsetY = Math.random() * 1500;
                    double offsetZ = (Math.random() - 0.5) * 1500;

                    main.getServer().getWorld("lecternwars").spawnParticle(Particle.SONIC_BOOM, x + offsetX,
                            y + offsetY, z + offsetZ, 0, 0, 0, 0, 1.5);
                }
            }
        }.runTaskLater(main, 0);

        new BukkitRunnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1500; i++) {
                    double offsetX = (Math.random() - 0.5) * 2000;
                    double offsetY = Math.random() * 2000;
                    double offsetZ = (Math.random() - 0.5) * 2000;

                    main.getServer().getWorld("lecternwars").spawnParticle(Particle.EXPLOSION, x + offsetX,
                            y + offsetY, z + offsetZ, 0, 0, 0, 0, 2.0);
                }
            }
        }.runTaskLater(main, 5);

        new BukkitRunnable() {
            @Override
            public void run() {
                for (int i = 0; i < 200; i++) {
                    double offsetX = (Math.random() - 0.5) * 2500;
                    double offsetY = Math.random() * 2500;
                    double offsetZ = (Math.random() - 0.5) * 2500;

                    Location tntLocation = new Location(main.getServer().getWorld("lecternwars"), x + offsetX,
                            y + offsetY, z + offsetZ);
                    TNTPrimed tnt = main.getServer().getWorld("lecternwars").spawn(tntLocation, TNTPrimed.class);
                    tnt.setFuseTicks(0);
                }
            }
        }.runTaskLater(main, 10);

        new BukkitRunnable() {
            @Override
            public void run() {
                main.getServer().getWorld("lecternwars").playSound(
                        new Location(main.getServer().getWorld("lecternwars"), x, y, z),
                        Sound.ENTITY_TNT_PRIMED, 1, 2);

                for (int i = 0; i < 2500; i++) {
                    double offsetX = (Math.random() - 0.5) * 3000;
                    double offsetY = Math.random() * 3000;
                    double offsetZ = (Math.random() - 0.5) * 3000;

                    main.getServer().getWorld("lecternwars").spawnParticle(Particle.LAVA, x + offsetX,
                            y + offsetY, z + offsetZ, 0, 0, 0, 0, 3.0);
                }

                main.getServer().getWorld("lecternwars").createExplosion(x, y, z, 20.0F, true, true);
            }
        }.runTaskLater(main, 15);

        new BukkitRunnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3000; i++) {
                    double offsetX = (Math.random() - 0.5) * 3500;
                    double offsetY = Math.random() * 3500;
                    double offsetZ = (Math.random() - 0.5) * 3500;

                    main.getServer().getWorld("lecternwars").spawnParticle(Particle.CLOUD, x + offsetX,
                            y + offsetY, z + offsetZ, 0, 0, 0, 0, 4.0);
                }
            }
        }.runTaskLater(main, 30);
    }
}
