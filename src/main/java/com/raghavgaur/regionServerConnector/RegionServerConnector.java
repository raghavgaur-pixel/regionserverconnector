package com.raghavgaur.regionServerConnector;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class RegionServerConnector extends JavaPlugin implements Listener {

    private Location corner1;
    private Location corner2;
    private String targetServer;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        FileConfiguration config = getConfig();

        corner1 = new Location(
                Bukkit.getWorld(config.getString("region.world")),
                config.getDouble("region.x1"),
                config.getDouble("region.y1"),
                config.getDouble("region.z1")
        );
        corner2 = new Location(
                Bukkit.getWorld(config.getString("region.world")),
                config.getDouble("region.x2"),
                config.getDouble("region.y2"),
                config.getDouble("region.z2")
        );

        targetServer = config.getString("target-server");

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getPluginManager().registerEvents(this, this);

        getLogger().info("RegionServerConnector enabled!");
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location loc = player.getLocation();

        if (isInsideRegion(loc)) {
            sendToServer(player, targetServer);
        }
    }

    private boolean isInsideRegion(Location loc) {
        double xMin = Math.min(corner1.getX(), corner2.getX());
        double xMax = Math.max(corner1.getX(), corner2.getX());
        double yMin = Math.min(corner1.getY(), corner2.getY());
        double yMax = Math.max(corner1.getY(), corner2.getY());
        double zMin = Math.min(corner1.getZ(), corner2.getZ());
        double zMax = Math.max(corner1.getZ(), corner2.getZ());

        return loc.getWorld().equals(corner1.getWorld()) &&
                loc.getX() >= xMin && loc.getX() <= xMax &&
                loc.getY() >= yMin && loc.getY() <= yMax &&
                loc.getZ() >= zMin && loc.getZ() <= zMax;
    }

    private void sendToServer(Player player, String serverName) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream dataOut = new DataOutputStream(out);

        try {
            dataOut.writeUTF("Connect");
            dataOut.writeUTF(serverName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        player.sendPluginMessage(this, "BungeeCord", out.toByteArray());
    }

}
